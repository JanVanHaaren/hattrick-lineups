package mcts.experimentSettings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import api.LocalPaths;

public class ExperimentEvaluation {
	
	Map<String, double[][]> simulationResultMap;
	Map<String, double[][]> depthMap;
	
	public ExperimentEvaluation() {
		super();
		ArrayList<String> teamNames = new ArrayList<String>();
		teamNames.add("team20_11");
		teamNames.add("team30_11");
		teamNames.add("team40_11");
		teamNames.add("team50_11");
		teamNames.add("team60_11");
		teamNames.add("team70_11");
		this.simulationResultMap = new HashMap<String, double[][]>();			
		this.depthMap = new HashMap<String, double[][]>();
	}

	

	private Map<String, double[][]> getSimulationResultMap() {
		return simulationResultMap;
	}



	private Map<String, double[][]> getDepthMap() {
		return depthMap;
	}



	public void createRandomEvaluation(String directory, double totalRuns, boolean numeric) throws IOException
	{
		System.out.println(directory);
		String experimentDirectory = LocalPaths.EXPERIMENTS + directory;
		
		for(File evalFile : Arrays.asList(new File(experimentDirectory).listFiles()))
		{
			String fileName = evalFile.getName();
			if(!fileName.contains("RANDOM"))
				continue;
			String[] split = fileName.split("-")[0].split("_");
			String teamName = split[split.length - 2] + "_" + split[split.length - 1];
			
			double[][] depths = getDepth(evalFile);
			double[][] sResults = getSimulationResult(evalFile);
			
			addToDepthMap(teamName, depths, totalRuns);
			addToSRMap(teamName, sResults, totalRuns);
		}
		
		double teamAmounts = getDepthMap().keySet().size();
		
		double[] averagedDepth = new double[50];
		
		for(String teamName : getDepthMap().keySet())
		{
			double[][] depths = getDepthMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_RANDOM_depth.txt"));
			for(int i = 0; i < depths.length; i++)
			{
				for(int j = 0; j < depths[0].length - 1; j++)
				{
					averagedDepth[j] += depths[i][j]/(teamAmounts*15);
					bw.write(depths[i][j] + ",");
				}
				averagedDepth[49] += depths[i][49]/(teamAmounts*15);
				bw.write(Double.toString(depths[i][depths[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
		
		double[] averagedSR = new double[50];
		for(String teamName : getDepthMap().keySet())
		{
			System.out.println(teamName);
			double[][] simulationResults = getSimulationResultMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_RANDOM_sr.txt"));
			for(int i = 0; i < simulationResults.length; i++)
			{
				for(int j = 0; j < simulationResults[0].length - 1; j++)
				{
					double increase = 0;
					if(numeric)
					{
						double baseLine = BaseLineGreedyTest.getNumericBaseLine(teamName, i);
						increase = (simulationResults[i][j] - baseLine) / Math.abs(baseLine);
					}
					else
					{
						double baseLine = BaseLineGreedyTest.getNominalBaseLine(teamName, i);
						increase = simulationResults[i][j] - baseLine;
					}
					averagedSR[j] += increase/(teamAmounts*15);
					bw.write(simulationResults[i][j] + ",");
				}
				double baseLine = numeric ? BaseLineGreedyTest.getNumericBaseLine(teamName, i) : BaseLineGreedyTest.getNominalBaseLine(teamName, i);
				double increase = (simulationResults[i][49] - baseLine) / Math.abs(baseLine);
				averagedSR[49] += increase/(teamAmounts*15);
				bw.write(Double.toString(simulationResults[i][simulationResults[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_RANDOM_averageSR.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_RANDOM_averageDepth.txt"));
		
		for(int i = 0; i < 49; i++)
		{
			bw.write(averagedSR[i] + ",");
			bw2.write(averagedDepth[i] + ",");
		}
		bw.write(averagedSR[49] + "");
		bw2.write(averagedDepth[49] + "");
		bw.close();
		bw2.close();
	}
	
	public void createRouletteEvaluation(String directory, double totalRuns, boolean numeric) throws IOException
	{
		System.out.println(directory);
		String experimentDirectory = LocalPaths.EXPERIMENTS + directory;
		
		for(File evalFile : Arrays.asList(new File(experimentDirectory).listFiles()))
		{
			String fileName = evalFile.getName();
			if(!fileName.contains("ROULETTE"))
				continue;
			String[] split = fileName.split("-")[0].split("_");
			String teamName = split[split.length - 2] + "_" + split[split.length - 1];
			
			double[][] depths = getDepth(evalFile);
			double[][] sResults = getSimulationResult(evalFile);
			
			addToDepthMap(teamName, depths, totalRuns);
			addToSRMap(teamName, sResults, totalRuns);
		}
		
		double teamAmounts = getDepthMap().keySet().size();
		
		double[] averagedDepth = new double[50];
		
		for(String teamName : getDepthMap().keySet())
		{
			double[][] depths = getDepthMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_ROULETTE_depth.txt"));
			for(int i = 0; i < depths.length; i++)
			{
				for(int j = 0; j < depths[0].length - 1; j++)
				{
					averagedDepth[j] += depths[i][j]/(teamAmounts*15);
					bw.write(depths[i][j] + ",");
				}
				averagedDepth[49] += depths[i][49]/(teamAmounts*15);
				bw.write(Double.toString(depths[i][depths[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
		
		double[] averagedSR = new double[50];
		for(String teamName : getDepthMap().keySet())
		{
			System.out.println(teamName);
			double[][] simulationResults = getSimulationResultMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_ROULETTE_sr.txt"));
			for(int i = 0; i < simulationResults.length; i++)
			{
				for(int j = 0; j < simulationResults[0].length - 1; j++)
				{
					double increase = 0;
					if(numeric)
					{
						double baseLine = BaseLineGreedyTest.getNumericBaseLine(teamName, i);
						increase = (simulationResults[i][j] - baseLine) / Math.abs(baseLine);
					}
					else
					{
						double baseLine = BaseLineGreedyTest.getNominalBaseLine(teamName, i);
						increase = simulationResults[i][j] - baseLine;
					}
					averagedSR[j] += increase/(teamAmounts*15);
					bw.write(simulationResults[i][j] + ",");
				}
				double baseLine = numeric ? BaseLineGreedyTest.getNumericBaseLine(teamName, i) : BaseLineGreedyTest.getNominalBaseLine(teamName, i);
				double increase = (simulationResults[i][49] - baseLine) / Math.abs(baseLine);
				averagedSR[49] += increase/(teamAmounts*15);
				bw.write(Double.toString(simulationResults[i][simulationResults[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_ROULETTE_averageSR.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_ROULETTE_averageDepth.txt"));
		
		for(int i = 0; i < 49; i++)
		{
			bw.write(averagedSR[i] + ",");
			bw2.write(averagedDepth[i] + ",");
		}
		bw.write(averagedSR[49] + "");
		bw2.write(averagedDepth[49] + "");
		bw.close();
		bw2.close();
	}
	
	public double[][] getDepth(File file) throws NumberFormatException, IOException
	{
		double[][] result = new double[15][50];
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int currentOpponent = 0;
		int currentIterations = 0;
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String[] split = sCurrentLine.split("\\s+");
			
			if(sCurrentLine.contains("OpponentRatings Number ="))
			{
				currentOpponent = Integer.parseInt(split[split.length - 1]);
			}
			else if(sCurrentLine.contains("Max iterations = "))
			{
				currentIterations = Integer.parseInt(split[split.length - 1])/1000 - 1;
			}
			else if(split.length > 0)
			{
				result[currentOpponent][currentIterations] = Integer.parseInt(split[1]);
			}
		}
		br.close();
		return result;
	}
	
	public double[][] getSimulationResult(File file) throws NumberFormatException, IOException
	{
		double[][] result = new double[15][50];
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int currentOpponent = 0;
		int currentIterations = 0;
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String[] split = sCurrentLine.split("\\s+");
			
			if(sCurrentLine.contains("OpponentRatings Number ="))
			{
				currentOpponent = Integer.parseInt(split[split.length - 1]);
			}
			else if(sCurrentLine.contains("Max iterations = "))
			{
				currentIterations = Integer.parseInt(split[split.length - 1])/1000 - 1;
			}
			else if(split.length > 0)
			{
				result[currentOpponent][currentIterations] = Double.parseDouble(split[3]);
			}
		}
		br.close();
		
		return result;
	}
	
	private void addToDepthMap(String teamName, double[][] depths, double runs)
	{
		double[][] current = getDepthMap().get(teamName);
		if(current == null)
		{
			current = new double[15][50];
			for(int i = 0; i < current.length; i++)
			{
				for(int j = 0; j < current[0].length; j++)
				{
					current[i][j] = 0;
				}
			}
		}
		for(int i = 0; i < current.length; i++)
		{
			for(int j = 0; j < current[0].length; j++)
			{
				current[i][j] += depths[i][j]/runs;
			}
		}
		
		getDepthMap().put(teamName, current);
	}
	
	private void addToSRMap(String teamName, double[][] simulationResults, double runs)
	{
		double[][] current = getSimulationResultMap().get(teamName);
		if(current == null)
		{
			current = new double[15][50];
			for(int i = 0; i < current.length; i++)
			{
				for(int j = 0; j < current[0].length; j++)
				{
					current[i][j] = 0;
				}
			}
		}
		for(int i = 0; i < current.length; i++)
		{
			for(int j = 0; j < current[0].length; j++)
			{
				current[i][j] += simulationResults[i][j]/runs;
			}
		}
		
		getSimulationResultMap().put(teamName, current);
	}
	
	public static void main(String[] args) throws IOException {
//		new ExperimentEvaluation().createRandomEvaluation("StUCT_Numeric", 40, true);
//		new ExperimentEvaluation().createRouletteEvaluation("StUCT_Numeric", 40, true);
		new ExperimentEvaluation().createRandomEvaluation("StOMCnum", 31, true);
		new ExperimentEvaluation().createRouletteEvaluation("StOMCnum", 31, true);
//		new ExperimentEvaluation().createRandomEvaluation("SPOMC_Numeric", 48, true);
//		new ExperimentEvaluation().createRouletteEvaluation("SPOMC_Numeric", 48, true);
		new ExperimentEvaluation().createRandomEvaluation("PBBMnum", 36, true);
		new ExperimentEvaluation().createRouletteEvaluation("PBBMnum", 36, true);
//		new ExperimentEvaluation().createRandomEvaluation("UCB1Tuned_Numeric", 34, true);
//		new ExperimentEvaluation().createRouletteEvaluation("UCB1Tuned_Numeric", 34, true);
		new ExperimentEvaluation().createRandomEvaluation("StUCTnom", 30, false);
		new ExperimentEvaluation().createRouletteEvaluation("StUCTnom", 30, false);
		new ExperimentEvaluation().createRandomEvaluation("StOMCnom", 12, false);
		new ExperimentEvaluation().createRouletteEvaluation("StOMCnom", 12, false);
		new ExperimentEvaluation().createRandomEvaluation("SPOMCnom", 36, false);
		new ExperimentEvaluation().createRouletteEvaluation("SPOMCnom", 36, false);
		new ExperimentEvaluation().createRandomEvaluation("PBBMnom", 35, false);
		new ExperimentEvaluation().createRouletteEvaluation("PBBMnom", 35, false);
		new ExperimentEvaluation().createRandomEvaluation("UCB1Tunednom", 29, false);
		new ExperimentEvaluation().createRouletteEvaluation("UCB1Tunednom", 29, false);
	}

}
