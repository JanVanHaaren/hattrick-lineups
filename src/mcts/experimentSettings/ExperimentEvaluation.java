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



	public void createRandomEvaluation(String directory, double totalRuns) throws IOException
	{
		
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
		
		
		for(String teamName : getDepthMap().keySet())
		{
			double[][] depths = getDepthMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_RANDOM_depth.txt"));
			for(int i = 0; i < depths.length; i++)
			{
				for(int j = 0; j < depths[0].length - 1; j++)
				{
					bw.write(depths[i][j] + ",");
				}
				bw.write(Double.toString(depths[i][depths[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
		
		for(String teamName : getDepthMap().keySet())
		{
			double[][] simulationResults = getSimulationResultMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_RANDOM_sr.txt"));
			for(int i = 0; i < simulationResults.length; i++)
			{
				for(int j = 0; j < simulationResults[0].length - 1; j++)
				{
					bw.write(simulationResults[i][j] + ",");
				}
				bw.write(Double.toString(simulationResults[i][simulationResults[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
	}
	
	public void createRouletteEvaluation(String directory, double totalRuns) throws IOException
	{
		
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
		
		
		for(String teamName : getDepthMap().keySet())
		{
			double[][] depths = getDepthMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_ROULETTE_depth.txt"));
			for(int i = 0; i < depths.length; i++)
			{
				for(int j = 0; j < depths[0].length - 1; j++)
				{
					bw.write(depths[i][j] + ",");
				}
				bw.write(Double.toString(depths[i][depths[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
		
		for(String teamName : getDepthMap().keySet())
		{
			double[][] simulationResults = getSimulationResultMap().get(teamName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + directory + "_" + teamName + "_ROULETTE_sr.txt"));
			for(int i = 0; i < simulationResults.length; i++)
			{
				for(int j = 0; j < simulationResults[0].length - 1; j++)
				{
					bw.write(simulationResults[i][j] + ",");
				}
				bw.write(Double.toString(simulationResults[i][simulationResults[0].length - 1]));
				bw.newLine();
			}
			bw.close();
		}
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
		new ExperimentEvaluation().createRandomEvaluation("StUCT_Numeric", 40);
		new ExperimentEvaluation().createRouletteEvaluation("StUCT_Numeric", 40);
	}

}
