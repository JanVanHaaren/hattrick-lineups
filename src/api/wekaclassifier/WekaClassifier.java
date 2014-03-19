package api.wekaclassifier;

import java.io.File;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class WekaClassifier {
	
	private Instances trainingSet;
	private Instances testSet;
	
	private Classifier classifier;
	private boolean isNumeric;
	
	private Evaluation evaluation;
	
	private long timeForEvaluation; //7759 instances buiten bij playershizz
		
	public WekaClassifier(Classifier classifier, String filepathTraining, String filepathTest){
		this.classifier = classifier;
		
		try{
			ArffLoader loader = new ArffLoader();
			loader.setFile(new File(filepathTraining));
			Instances trainingSet = loader.getDataSet();
			trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
			this.isNumeric = trainingSet.attribute(trainingSet.numAttributes() - 1).isNumeric();
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
	
	private boolean isNumeric() {
		return isNumeric;
	}
	
	public double getPrediction(Instance instance) throws Exception{
		instance.setDataset(getTrainingSet());
		if(isNumeric())
			return getClassifier().classifyInstance(instance);
		String result = getTrainingSet().classAttribute().value((int) (getClassifier().classifyInstance(instance)));
		if(result.equals("win"))
			return 1D;
		return 0D;
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
	
	public double getMAE()
	{
		return getEvaluation().meanAbsoluteError();
	}
	
	public double getCorrelationCoefficient() throws Exception
	{
		return getEvaluation().correlationCoefficient();
	}
}
