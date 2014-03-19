package api.wekaclassifier;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class CVWekaClassifier {
	
	private Instances dataSet;
	private Classifier classifier;
	private Evaluation evaluation;
	
		
	public CVWekaClassifier(Classifier classifier, String filepath){
		this.classifier = classifier;
		
		try{
			ArffLoader loader = new ArffLoader();
			loader.setFile(new File(filepath));
			Instances dataSet = loader.getDataSet();
			dataSet.setClassIndex(dataSet.numAttributes() - 1);
			this.dataSet = dataSet;
			
			this.evaluation = new Evaluation(getDataSet());
			
			this.evaluation.crossValidateModel(getClassifier(), getDataSet(), 10, new Random(1));
			System.out.println("evaluated " + this.getClassifierName());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Classifier getClassifier() {
		return classifier;
	}
	
	private Evaluation getEvaluation() {
		return evaluation;
	}
		
	public String getClassifierName(){
		return getClassifier().getClass().getSimpleName();
	}

	public Instances getDataSet() {
		return dataSet;
	}

	public double getPctCorrect() throws Exception{
		return getEvaluation().pctCorrect();
	}
	
	public double getPctIncorrect() throws Exception{
		return getEvaluation().pctIncorrect();
	}
	
	public double getPrecision(ResultClass resultClass)
	{
		return getEvaluation().precision(resultClass.getIndex());
	}
	
	public double getRecall(ResultClass resultClass)
	{
		return getEvaluation().recall(resultClass.getIndex());
	}
	
	public double[][] getConfusionMatrix() throws Exception{
		return getEvaluation().confusionMatrix();
	}
	
	public double getFalsePositiveRate(ResultClass resultClass)
	{
		return getEvaluation().falsePositiveRate(resultClass.getIndex());
	}
	
	public double getAreaUnderROC(ResultClass resultClass) throws Exception
	{
		return getEvaluation().areaUnderROC(resultClass.getIndex());
	}
	
	public double getRMSE()
	{
		return getEvaluation().rootMeanSquaredError();
	}
	
	public double getMAE()
	{
		return getEvaluation().meanAbsoluteError();
	}
	
	public double getCorrelationCoefficient() throws Exception
	{
		return getEvaluation().correlationCoefficient();
	}
}
