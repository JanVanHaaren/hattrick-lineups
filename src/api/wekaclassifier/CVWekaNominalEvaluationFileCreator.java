package api.wekaclassifier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SMO;
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
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import api.LocalPaths;
import api.util.Utils;

public class CVWekaNominalEvaluationFileCreator extends CVWekaEvaluationFileCreator {

	public CVWekaNominalEvaluationFileCreator(String fileName) throws Exception {
		super(fileName);
		addClassifier(new BayesNet());
		addClassifier(new NaiveBayes());
		addClassifier(new Logistic());
		addClassifier(new SMO());
		addClassifier(new AdaBoostM1());
		addClassifier(new AttributeSelectedClassifier());
		addClassifier(new Bagging());
		addClassifier(new ClassificationViaRegression());
		addClassifier(new FilteredClassifier());
		addClassifier(new LogitBoost());
		addClassifier(new MultiClassClassifier());
		addClassifier(new MultiClassClassifierUpdateable());
		addClassifier(new RandomCommittee());
		addClassifier(new RandomSubSpace());
		addClassifier(new DecisionTable());
		addClassifier(new JRip());
		addClassifier(new OneR());
		addClassifier(new PART());
		addClassifier(new DecisionStump());
		addClassifier(new J48());
		addClassifier(new RandomForest());
		addClassifier(new RandomTree());
		addClassifier(new REPTree());
	}
	
	public void createEvaluationFile() throws Exception
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_5000 + getFileName() + "_evaluation.txt"));
			
			writer.write("EVALUATION FILE FOR: " + getFileName());
			writer.newLine();
			writer.newLine();
			for(CVWekaClassifier classifier : this.getClassifiers())
			{
				writer.write(classifier.getClassifierName() + " (" + classifier.getDataSet().numInstances() + " instances)");
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
				writer.write("\tConfusion Matrix =    " + classifier.getConfusionMatrix()[0][0] + "\t" + classifier.getConfusionMatrix()[0][1] + "\t" + classifier.getConfusionMatrix()[0][2]);
				writer.newLine();
				writer.write("\t                      " + classifier.getConfusionMatrix()[1][0] + "\t" + classifier.getConfusionMatrix()[1][1]+ "\t" + classifier.getConfusionMatrix()[1][2]);
				writer.newLine();
				writer.write("\t                      " + classifier.getConfusionMatrix()[2][0] + "\t" + classifier.getConfusionMatrix()[2][1]+ "\t" + classifier.getConfusionMatrix()[2][2]);
				writer.newLine();
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
