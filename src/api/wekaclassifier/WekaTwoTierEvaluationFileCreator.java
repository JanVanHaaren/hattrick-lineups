package api.wekaclassifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.M5P;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Add;
import api.LocalPaths;
import api.util.Utils;

public class WekaTwoTierEvaluationFileCreator {

	private ArrayList<WekaClassifier> primaryClassifiers;
	private ArrayList<WekaClassifier> secondaryClassifiers;
	private String fileName;
	private String trainFileName;
	private String trainFileName2;
	private String testFileName;
	private String testFileName2;
	
	public static void main(String[] args) {
		try {
			new WekaTwoTierEvaluationFileCreator("advancedRatingProportions_Vnukstats2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WekaTwoTierEvaluationFileCreator(String fileName) throws Exception {
		this.fileName = fileName;
		this.trainFileName = LocalPaths.WEKA_LOCATION + fileName + "_train.arff";
		this.trainFileName2 = LocalPaths.WEKA_LOCATION + fileName + "_train2.arff";
		this.testFileName = LocalPaths.WEKA_LOCATION + fileName + "_test.arff";
		this.testFileName2 = LocalPaths.WEKA_LOCATION + fileName + "_test2.arff";
		this.primaryClassifiers = new ArrayList<WekaClassifier>();	
		this.secondaryClassifiers = new ArrayList<WekaClassifier>();	
		
		//Selection of some of the best nominal classifiers, based on evaluation time, accuracy and confusion matrices
		
		addPrimaryClassifier(new M5P());
		
		// load unlabeled data
		Instances trainingSet = primaryClassifiers.get(0).getTrainingSet();
		Instances testSet = primaryClassifiers.get(0).getTestSet();
		 
		Add filter = new Add();;
		Instances enhancedTrainingData = new Instances(trainingSet);
		enhancedTrainingData.setClassIndex(trainingSet.numAttributes() - 1);
        filter.setAttributeIndex("last");
        filter.setNominalLabels("win,loss");
        filter.setAttributeName("resultNominal");
        filter.setInputFormat(enhancedTrainingData);
        enhancedTrainingData = Filter.useFilter(enhancedTrainingData, filter);
        
        Add filter2 = new Add();;
        Instances enhancedTestData = new Instances(testSet);
        enhancedTestData.setClassIndex(trainingSet.numAttributes() - 1);
        filter2.setAttributeIndex("last");
        filter2.setNominalLabels("win,loss");
        filter2.setAttributeName("resultNominal");
        filter2.setInputFormat(enhancedTestData);
        enhancedTestData = Filter.useFilter(enhancedTestData, filter2);
		 
		// label instances
		for (int i = 0; i < trainingSet.numInstances(); i++) {
			enhancedTrainingData.instance(i).setValue(enhancedTrainingData.numAttributes() - 1, enhancedTrainingData.instance(i).value(enhancedTrainingData.instance(i).numAttributes() - 2) > 0D ? "win" : "loss");
		}
		for (int i = 0; i < testSet.numInstances(); i++) {
			enhancedTestData.instance(i).setValue(enhancedTestData.numAttributes() - 1, enhancedTestData.instance(i).value(enhancedTestData.instance(i).numAttributes() - 2) > 0D ? "win" : "loss");
		}
		
		for(WekaClassifier pc : primaryClassifiers){
			this.secondaryClassifiers = new ArrayList<WekaClassifier>();
			
			for (int i = 0; i < trainingSet.numInstances(); i++) {
				double clsLabel = pc.getPrediction(trainingSet.instance(i));
				enhancedTrainingData.instance(i).setClassValue(clsLabel);
			}
			
			for (int i = 0; i < testSet.numInstances(); i++) {
				double clsLabel = pc.getPrediction(testSet.instance(i));
				enhancedTestData.instance(i).setClassValue(clsLabel);
			}
			
			// save labeled data
			BufferedWriter tempTrainWriter = new BufferedWriter(
		                           new FileWriter(this.trainFileName2));
			tempTrainWriter.write(enhancedTrainingData.toString());
			tempTrainWriter.newLine();
			tempTrainWriter.flush();
			tempTrainWriter.close();
			
			BufferedWriter tempTestWriter = new BufferedWriter(
					new FileWriter(this.testFileName2));
			tempTestWriter.write(enhancedTestData.toString());
			tempTestWriter.newLine();
			tempTestWriter.flush();
			tempTestWriter.close();
			
			//Best classifiers etc...
			addSecondaryClassifier(new SimpleLogistic());
			new File(this.testFileName2).delete();
			new File(this.trainFileName2).delete();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + this.fileName + "-" +  pc.getClassifierName() + "_evaluation.txt"));
			
			writer.write("EVALUATION FILE FOR: " + this.fileName);
			writer.newLine();
			writer.newLine();
			for(WekaClassifier classifier : this.secondaryClassifiers)
			{
				writer.write(classifier.getClassifierName() + " (Time for evaluation = " + classifier.getTimeForEvaluation() + "ms for " + classifier.getTestSet().numInstances() + " instances)");
				writer.newLine();
				writer.write("\tAccuracy =            " + Utils.get5PrecisionDouble(classifier.getPctCorrect()));
				writer.newLine();
				writer.write("\tPrecision(Win-Loss) = " + Utils.get5PrecisionDouble(classifier.getPrecision(ResultClass.WIN))
							+ "\t" + Utils.get5PrecisionDouble(classifier.getPrecision(ResultClass.LOSS)));
				writer.newLine();
				writer.write("\tRecall(Win-Loss) =    " + Utils.get5PrecisionDouble(classifier.getRecall(ResultClass.WIN))
							+ "\t" + Utils.get5PrecisionDouble(classifier.getRecall(ResultClass.LOSS)));
				writer.newLine();
				writer.write("\tFPR(Win-Loss) =       " + Utils.get5PrecisionDouble(classifier.getFalsePositiveRate(ResultClass.WIN))
							+ "\t" + Utils.get5PrecisionDouble(classifier.getFalsePositiveRate(ResultClass.LOSS)));
				writer.newLine();
				writer.write("\tRMSE =                " + Utils.get5PrecisionDouble(classifier.getRMSE()));
				writer.newLine();
				writer.write("\tAUROC(Win-Loss) =     " + Utils.get5PrecisionDouble(classifier.getAreaUnderROC(ResultClass.WIN))
							+ "\t" + Utils.get5PrecisionDouble(classifier.getAreaUnderROC(ResultClass.LOSS)));
				writer.newLine();
				writer.write("\tConfusion Matrix =    " + classifier.getConfusionMatrix()[0][0] + "\t" + classifier.getConfusionMatrix()[0][1]);
				writer.newLine();
				writer.write("\t                      " + classifier.getConfusionMatrix()[1][0] + "\t" + classifier.getConfusionMatrix()[1][1]);
				writer.newLine();
				writer.newLine();
			}
			writer.flush();
			writer.close();
		}
	}
	
	private void addPrimaryClassifier(Classifier classifier) throws Exception
	{
		this.primaryClassifiers.add(new WekaClassifier(classifier, this.trainFileName, this.testFileName));
	}
	
	private void addSecondaryClassifier(Classifier classifier) throws Exception
	{
		this.secondaryClassifiers.add(new WekaClassifier(classifier, this.trainFileName2, this.testFileName2));
	}
}
