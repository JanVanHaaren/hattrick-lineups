package api.wekaclassifier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.meta.AdditiveRegression;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.RandomSubSpace;
import weka.classifiers.meta.RegressionByDiscretization;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.M5Rules;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.REPTree;
import api.LocalPaths;
import api.util.Utils;

public class CVWekaNumericEvaluationFileCreator extends CVWekaEvaluationFileCreator{

	public CVWekaNumericEvaluationFileCreator(String fileName) throws Exception {
		super(fileName);
		addClassifier(new LinearRegression());
		addClassifier(new AdditiveRegression());
		addClassifier(new Bagging());
		addClassifier(new RandomSubSpace());
		addClassifier(new RegressionByDiscretization());
		addClassifier(new DecisionTable());
		addClassifier(new DecisionStump());
		addClassifier(new M5Rules());
		addClassifier(new M5P());
		addClassifier(new REPTree());
	}

	@Override
	public void createEvaluationFile() throws Exception {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + getFileName() + "_evaluation.txt"));
			
			writer.write("EVALUATION FILE FOR: " + getFileName());
			writer.newLine();
			writer.newLine();
			for(CVWekaClassifier classifier : this.getClassifiers())
			{
				writer.write(classifier.getClassifierName() + " (" + classifier.getDataSet().numInstances() + " instances)");
				writer.newLine();
				writer.write("\tRMSE =                " + Utils.get5PrecisionDouble(classifier.getRMSE()));
				writer.newLine();
				writer.write("\tMAE =                 " + Utils.get5PrecisionDouble(classifier.getMAE()));
				writer.newLine();
				writer.write("\tCorrelation =         " + Utils.get5PrecisionDouble(classifier.getCorrelationCoefficient()));
				writer.newLine();
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
