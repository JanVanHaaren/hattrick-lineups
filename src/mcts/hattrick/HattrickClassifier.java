package mcts.hattrick;

import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.M5P;
import weka.core.Instance;
import api.LocalPaths;
import api.wekaclassifier.WekaClassifier;

public class HattrickClassifier {

	private static String FILE_NAME1 = "advancedRatingProportions_VnukStats";
	private static HattrickClassifier NOMINAL_INSTANCE = new HattrickClassifier(new WekaClassifier(new SimpleLogistic(), LocalPaths.WEKA_LOCATION + FILE_NAME1 + "_train.arff", LocalPaths.WEKA_LOCATION + FILE_NAME1 + "_test.arff"));
	private static String FILE_NAME2 = "advancedRatingProportions_VnukStats2";
	private static HattrickClassifier NUMERIC_INSTANCE = new HattrickClassifier(new WekaClassifier(new M5P(), LocalPaths.WEKA_LOCATION + FILE_NAME2 + "_train.arff", LocalPaths.WEKA_LOCATION + FILE_NAME2 + "_test.arff"));
	
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
		//TODO
	}
	
	private WekaClassifier getClassifier() {
		return classifier;
	}

	public double getPredictionResult(Instance instance) throws Exception
	{
		return getClassifier().getPrediction(instance);
	}
	
	
	
	
}
