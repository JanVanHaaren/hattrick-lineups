package api.wekaclassifier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.functions.VotedPerceptron;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.ClassificationViaRegression;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.LogitBoost;
import weka.classifiers.meta.MultiClassClassifier;
import weka.classifiers.meta.MultiClassClassifierUpdateable;
import weka.classifiers.meta.RandomCommittee;
import weka.classifiers.meta.RandomSubSpace;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import api.LocalPaths;
import api.util.Utils;

public class WekaEvaluationFileCreator {
	private ArrayList<WekaClassifier> classifiers;
	private String fileName;
	private String trainFileName;
	private String testFileName;
	
	public static void main(String[] args) {
		try {
			System.out.println("EVAL 1");
			WekaEvaluationFileCreator wefc = new WekaEvaluationFileCreator("advancedHatStats2");
			wefc.createEvaluationFile();
			System.out.println("EVAL 2");
			WekaEvaluationFileCreator wefc2 = new WekaEvaluationFileCreator("advancedVnukStats2");
			wefc2.createEvaluationFile();
			System.out.println("EVAL 3");
			WekaEvaluationFileCreator wefc3 = new WekaEvaluationFileCreator("advancedRatingProportions_VnukStats2");
			wefc3.createEvaluationFile();
			System.out.println("EVAL 4");
			WekaEvaluationFileCreator wefc4 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportions_VnukStats");
			wefc4.createEvaluationFile();
			System.out.println("EVAL 5");
			WekaEvaluationFileCreator wefc5 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportions_VnukStats2");
			wefc5.createEvaluationFile();
			System.out.println("EVAL 6");
			WekaEvaluationFileCreator wefc6 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportionsChar_VnukStats");
			wefc6.createEvaluationFile();
			System.out.println("EVAL 7");
			WekaEvaluationFileCreator wefc7 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportionsChar_VnukStats2");
			wefc7.createEvaluationFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WekaEvaluationFileCreator(String fileName) throws Exception {
		this.fileName = fileName;
		this.trainFileName = LocalPaths.WEKA_LOCATION + fileName + "_train.arff";
		this.testFileName = LocalPaths.WEKA_LOCATION + fileName + "_test.arff";
		this.classifiers = new ArrayList<WekaClassifier>();
		this.classifiers.add(new WekaClassifier(new BayesNet(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new NaiveBayes(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new Logistic(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new MultilayerPerceptron(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new SGD(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new SimpleLogistic(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new SMO(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new VotedPerceptron(),this.trainFileName,this.testFileName));
//		this.classifiers.add(new WekaClassifier(new IBk(),this.trainFileName,this.testFileName));
//		this.classifiers.add(new WekaClassifier(new KStar(),this.trainFileName,this.testFileName));
//		this.classifiers.add(new WekaClassifier(new LWL(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new AdaBoostM1(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new AttributeSelectedClassifier(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new Bagging(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new ClassificationViaRegression(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new FilteredClassifier(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new LogitBoost(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new MultiClassClassifier(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new MultiClassClassifierUpdateable(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new RandomCommittee(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new RandomSubSpace(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new DecisionTable(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new JRip(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new OneR(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new PART(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new DecisionStump(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new J48(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new LMT(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new RandomForest(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new RandomTree(),this.trainFileName,this.testFileName));
		this.classifiers.add(new WekaClassifier(new REPTree(),this.trainFileName,this.testFileName));
	}

	private ArrayList<WekaClassifier> getClassifiers() {
		return classifiers;
	}
	
	public void createEvaluationFile() throws Exception
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + this.fileName + "_evaluation.txt"));
			
			writer.write("EVALUATION FILE FOR: " + fileName);
			writer.newLine();
			writer.newLine();
			for(WekaClassifier classifier : this.getClassifiers())
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
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
