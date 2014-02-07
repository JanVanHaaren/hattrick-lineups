package api.wekaclassifier;

import java.io.File;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class WekaClassifier {
	
	private Instances trainingSet;
	private Instances testSet;
	
	private Classifier classifier;
	
	private Evaluation evaluation;
	
	private long timeForEvaluation; //7759 instances buiten bij playershizz
		
	public WekaClassifier(Classifier classifier, String filepathTraining, String filepathTest) throws Exception{
		this.classifier = classifier;
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File(filepathTraining));
		Instances trainingSet = loader.getDataSet();
		trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
		this.trainingSet = trainingSet;
		
		loader = new ArffLoader();
		loader.setFile(new File(filepathTest));
		Instances testSet = loader.getDataSet();
		testSet.setClassIndex(testSet.numAttributes() - 1);
		this.testSet = testSet;
		
		this.classifier.buildClassifier(trainingSet);
		this.evaluation = new Evaluation(getTrainingSet());
		
		long currTime = System.currentTimeMillis();
		this.evaluation.evaluateModel(getClassifier(), getTestSet());
		this.timeForEvaluation = (System.currentTimeMillis() - currTime);
		System.out.println("evaluated " + this.getClassifierName());
	}
	
	private Classifier getClassifier() {
		return classifier;
	}
	
	private Evaluation getEvaluation() {
		return evaluation;
	}
	
	public long getTimeForEvaluation() {
		return timeForEvaluation;
	}
	
	public String getClassifierName(){
		return getClassifier().getClass().getSimpleName();
	}

	public Instances getTrainingSet() {
		return trainingSet;
	}
	
	public Instances getTestSet() {
		return testSet;
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
}
