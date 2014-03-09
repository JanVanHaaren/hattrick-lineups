package api.wekaclassifier;

import java.util.ArrayList;

import weka.classifiers.Classifier;
import api.LocalPaths;

public abstract class WekaEvaluationFileCreator {
	private ArrayList<WekaClassifier> classifiers;
	private String fileName;
	private String trainFileName;
	private String testFileName;
	
	public static void main(String[] args) {
		try {
//			System.out.println("EVAL 1");
//			WekaEvaluationFileCreator wefc = new WekaEvaluationFileCreator("advancedHatStats2", false);
//			wefc.createEvaluationFile2();
			System.out.println("EVAL 2");
			WekaEvaluationFileCreator wefc2 = new WekaNumericEvaluationFileCreator("advancedVnukStats2_blub");
			wefc2.createEvaluationFile();
//			System.out.println("EVAL 3");
//			WekaEvaluationFileCreator wefc3 = new WekaEvaluationFileCreator("advancedRatingProportions_VnukStats2", false);
//			wefc3.createEvaluationFile2();
//			System.out.println("EVAL 4");
//			WekaEvaluationFileCreator wefc4 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportions_VnukStats");
//			wefc4.createEvaluationFile();
//			System.out.println("EVAL 5");
//			WekaEvaluationFileCreator wefc5 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportions_VnukStats2", false);
//			wefc5.createEvaluationFile2();
//			System.out.println("EVAL 6");
//			WekaEvaluationFileCreator wefc6 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportionsChar_VnukStats");
//			wefc6.createEvaluationFile();
//			System.out.println("EVAL 7");
//			WekaEvaluationFileCreator wefc7 = new WekaEvaluationFileCreator("advancedDiscreteRatingProportionsChar_VnukStats2", false);
//			wefc7.createEvaluationFile2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WekaEvaluationFileCreator(String fileName) throws Exception {
		this.fileName = fileName;
		this.trainFileName = LocalPaths.WEKA_LOCATION + fileName + "_train.arff";
		this.testFileName = LocalPaths.WEKA_LOCATION + fileName + "_test.arff";
		this.classifiers = new ArrayList<WekaClassifier>();	
	}

	protected String getFileName()
	{
		return this.fileName;
	}
	
	protected ArrayList<WekaClassifier> getClassifiers() {
		return classifiers;
	}
	
	protected void addClassifier(Classifier classifier) throws Exception
	{
		this.classifiers.add(new WekaClassifier(classifier, this.trainFileName,this.testFileName));
	}
	
	public abstract void createEvaluationFile() throws Exception;
}
