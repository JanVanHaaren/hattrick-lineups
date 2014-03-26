package mcts.hattrick;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.M5P;
import weka.core.Instance;
import api.LocalPaths;
import api.wekaclassifier.WekaClassifier;

public class HattrickClassifier {

	private static String FILE_NAME1 = "advancedRatingProportions_VnukStats";
	private static String FILE_NAME2 = "advancedRatingProportions_VnukStats2";
	
	private static HattrickClassifier NOMINAL_INSTANCE = new HattrickClassifier(new WekaClassifier(LocalPaths.MODEL_LOCATION + "nominal.model", LocalPaths.WEKA_LOCATION + FILE_NAME1 + "_train.arff", LocalPaths.WEKA_LOCATION + FILE_NAME1 + "_test.arff"));
	private static HattrickClassifier NUMERIC_INSTANCE = new HattrickClassifier(new WekaClassifier(LocalPaths.MODEL_LOCATION + "numeric.model", LocalPaths.WEKA_LOCATION + FILE_NAME2 + "_train.arff", LocalPaths.WEKA_LOCATION + FILE_NAME2 + "_test.arff"));
	
	
	
	private WekaClassifier classifier;
	
	public static HattrickClassifier getNumericInstance()
	{
		return NUMERIC_INSTANCE;
	}
	
	public static HattrickClassifier getNominalInstance()
	{
		return NOMINAL_INSTANCE;
	}
	
	private HattrickClassifier(WekaClassifier classifier){
		this.classifier = classifier;
	}
	
	private WekaClassifier getClassifier() {
		return classifier;
	}

	public double getPredictionResult(Instance instance) throws Exception
	{
		return getClassifier().getPrediction(instance);
	}
	
	public static void main(String[] args) {
		String FILE_NAME1 = "advancedRatingProportions_VnukStats";
		HattrickClassifier NOMINAL_INSTANCE = new HattrickClassifier(new WekaClassifier(new SimpleLogistic(), LocalPaths.WEKA_LOCATION + FILE_NAME1 + "_train.arff", LocalPaths.WEKA_LOCATION + FILE_NAME1 + "_test.arff"));
		String FILE_NAME2 = "advancedRatingProportions_VnukStats2";
		HattrickClassifier NUMERIC_INSTANCE = new HattrickClassifier(new WekaClassifier(new M5P(), LocalPaths.WEKA_LOCATION + FILE_NAME2 + "_train.arff", LocalPaths.WEKA_LOCATION + FILE_NAME2 + "_test.arff"));
		 try {
			 ObjectOutputStream oos = new ObjectOutputStream(
	                 new FileOutputStream(LocalPaths.WEKA_LOCATION + "nominal.model"));
			 oos.writeObject(NOMINAL_INSTANCE.getClassifier().getClassifier());
			 oos.flush();
			 oos.close();
			 
			 ObjectOutputStream oos2 = new ObjectOutputStream(
	                 new FileOutputStream(LocalPaths.WEKA_LOCATION + "numeric.model"));
			 oos2.writeObject(NUMERIC_INSTANCE.getClassifier().getClassifier());
			 oos2.flush();
			 oos2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
