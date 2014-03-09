package api.wekaclassifier;

import java.util.ArrayList;

import weka.classifiers.Classifier;
import api.LocalPaths;

public abstract class CVWekaEvaluationFileCreator {
	private ArrayList<CVWekaClassifier> classifiers;
	private String fileName;
	private String trainFileName;
	
	public static void main(String[] args) {
		try {
			System.out.println("EVAL 1");
			CVWekaEvaluationFileCreator wefc1 = new CVWekaNominalEvaluationFileCreator("Naive");
			wefc1.createEvaluationFile();
//			System.out.println("EVAL 2");
//			CVWekaEvaluationFileCreator wefc2 = new CVWekaNominalEvaluationFileCreator("Naive_WinLoss");
//			wefc2.createEvaluationFile();
//			System.out.println("EVAL 3");
//			CVWekaEvaluationFileCreator wefc3 = new CVWekaNumericEvaluationFileCreator("Naive_GoalDiff");
//			wefc3.createEvaluationFile();
			System.out.println("EVAL 4");
			CVWekaEvaluationFileCreator wefc4 = new CVWekaNominalEvaluationFileCreator("HatStats");
			wefc4.createEvaluationFile();
//			System.out.println("EVAL 5");
//			CVWekaEvaluationFileCreator wefc5 = new CVWekaNominalEvaluationFileCreator("HatStats_WinLoss");
//			wefc5.createEvaluationFile();
//			System.out.println("EVAL 6");
//			CVWekaEvaluationFileCreator wefc6 = new CVWekaNumericEvaluationFileCreator("HatStats_GoalDiff");
//			wefc6.createEvaluationFile();
			System.out.println("EVAL 7");
			CVWekaEvaluationFileCreator wefc7 = new CVWekaNominalEvaluationFileCreator("VnukStats");
			wefc7.createEvaluationFile();
//			System.out.println("EVAL 8");
//			CVWekaEvaluationFileCreator wefc8 = new CVWekaNominalEvaluationFileCreator("VnukStats_WinLoss");
//			wefc8.createEvaluationFile();
//			System.out.println("EVAL 9");
//			CVWekaEvaluationFileCreator wefc9 = new CVWekaNumericEvaluationFileCreator("VnukStats_GoalDiff");
//			wefc9.createEvaluationFile();
			System.out.println("EVAL 10");
			CVWekaEvaluationFileCreator wefc10 = new CVWekaNominalEvaluationFileCreator("OriginalVnukStats");
			wefc10.createEvaluationFile();
//			System.out.println("EVAL 11");
//			CVWekaEvaluationFileCreator wefc11 = new CVWekaNominalEvaluationFileCreator("OriginalVnukStats_WinLoss");
//			wefc11.createEvaluationFile();
//			System.out.println("EVAL 12");
//			CVWekaEvaluationFileCreator wefc12 = new CVWekaNumericEvaluationFileCreator("OriginalVnukStats_GoalDiff");
//			wefc12.createEvaluationFile();
			System.out.println("EVAL 13");
			CVWekaEvaluationFileCreator wefc13 = new CVWekaNominalEvaluationFileCreator("NaiveWithTactic");
			wefc13.createEvaluationFile();
//			System.out.println("EVAL 14");
//			CVWekaEvaluationFileCreator wefc14 = new CVWekaNominalEvaluationFileCreator("NaiveWithTactic_WinLoss");
//			wefc14.createEvaluationFile();
//			System.out.println("EVAL 15");
//			CVWekaEvaluationFileCreator wefc15 = new CVWekaNumericEvaluationFileCreator("NaiveWithTactic_GoalDiff");
//			wefc15.createEvaluationFile();
			System.out.println("EVAL 16");
			CVWekaEvaluationFileCreator wefc16 = new CVWekaNominalEvaluationFileCreator("RatingDifferences");
			wefc16.createEvaluationFile();
//			System.out.println("EVAL 17");
//			CVWekaEvaluationFileCreator wefc17 = new CVWekaNominalEvaluationFileCreator("RatingDifferences_WinLoss");
//			wefc17.createEvaluationFile();
//			System.out.println("EVAL 18");
//			CVWekaEvaluationFileCreator wefc18 = new CVWekaNumericEvaluationFileCreator("RatingDifferences_GoalDiff");
//			wefc18.createEvaluationFile();
			System.out.println("EVAL 19");
			CVWekaEvaluationFileCreator wefc19 = new CVWekaNominalEvaluationFileCreator("RatingProportions");
			wefc19.createEvaluationFile();
//			System.out.println("EVAL 20");
//			CVWekaEvaluationFileCreator wefc20 = new CVWekaNominalEvaluationFileCreator("RatingProportions_WinLoss");
//			wefc20.createEvaluationFile();
//			System.out.println("EVAL 21");
//			CVWekaEvaluationFileCreator wefc21 = new CVWekaNumericEvaluationFileCreator("RatingProportions_GoalDiff");
//			wefc21.createEvaluationFile();
//			System.out.println("EVAL 22");
//			CVWekaEvaluationFileCreator wefc22 = new CVWekaNominalEvaluationFileCreator("RatingProportionsVnukStats_WinLoss");
//			wefc22.createEvaluationFile();
//			System.out.println("EVAL 23");
//			CVWekaEvaluationFileCreator wefc23 = new CVWekaNumericEvaluationFileCreator("RatingProportionsVnukStats_GoalDiff");
//			wefc23.createEvaluationFile();
//			System.out.println("EVAL 24");
//			CVWekaEvaluationFileCreator wefc24 = new CVWekaNominalEvaluationFileCreator("ratingProportions_VnukStats_formation_winLoss");
//			wefc24.createEvaluationFile();
//			System.out.println("EVAL 25");
//			CVWekaEvaluationFileCreator wefc25 = new CVWekaNominalEvaluationFileCreator("ratingProportions_VnukStats_intFormation_winLoss");
//			wefc25.createEvaluationFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CVWekaEvaluationFileCreator(String fileName) throws Exception {
		this.fileName = fileName;
		this.trainFileName = LocalPaths.WEKA_5000 + fileName + ".arff";
		this.classifiers = new ArrayList<CVWekaClassifier>();	
	}

	protected String getFileName()
	{
		return this.fileName;
	}
	
	protected ArrayList<CVWekaClassifier> getClassifiers() {
		return classifiers;
	}
	
	protected void addClassifier(Classifier classifier) throws Exception
	{
		this.classifiers.add(new CVWekaClassifier(classifier, this.trainFileName));
	}
	
	public abstract void createEvaluationFile() throws Exception;
}
