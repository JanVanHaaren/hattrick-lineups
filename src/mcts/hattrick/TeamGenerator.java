package mcts.hattrick;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import api.LocalPaths;
import api.entity.playerdetails.Player;

public class TeamGenerator {
	
	public static void main(String[] args) {
		createTeamFile(11, 20, LocalPaths.TEAM_FILES + "team20_11.txt");
		createTeamFile(11, 30, LocalPaths.TEAM_FILES + "team30_11.txt");
		createTeamFile(11, 40, LocalPaths.TEAM_FILES + "team40_11.txt");
		createTeamFile(11, 50, LocalPaths.TEAM_FILES + "team50_11.txt");
		createTeamFile(11, 60, LocalPaths.TEAM_FILES + "team60_11.txt");
		createTeamFile(11, 70, LocalPaths.TEAM_FILES + "team70_11.txt");
	}
	
	public static Player generatePlayer(double totalSkill)
	{
		Random random = new Random();
		int modifierMean = (int)(totalSkill/4.5);
		int restSkill = (int)totalSkill + (int)(random.nextInt((int)totalSkill/3)*Math.pow(-1, random.nextInt(2)));
		System.out.println(totalSkill + " " + restSkill);
		ArrayList<Integer> skills = new ArrayList<Integer>();
		for(int i = 5; i > 0; i--)
		{
			int skill = Math.max(0, Math.min(20, 1 + random.nextInt(Math.min((int)(totalSkill/2.8), restSkill - i))));
			restSkill -= skill;
			skills.add(skill);
		}
		skills.add(Math.min(20,restSkill));
		Collections.shuffle(skills);
		
		int keeper = skills.get(0);
		int defender = skills.get(1);
		int playmaker = skills.get(2);
		int passing = skills.get(3);
		int winger = skills.get(4);
		int scorer = skills.get(5);
		
		int experience = (int) Math.min(20,Math.max(0,Math.round(new Random().nextGaussian()*(totalSkill/40)) + modifierMean));
		int form = (int) Math.min(20,Math.max(0,Math.round(new Random().nextGaussian()*(totalSkill/40)) + modifierMean));
		int stamina = (int) Math.min(20,Math.max(0,Math.round(new Random().nextGaussian()*(totalSkill/40)) + modifierMean));
		
		return new Player(-1, false, null, experience, form, stamina, 20, keeper, defender, playmaker, passing, winger, scorer);
	}
	
	public static ArrayList<Player> generateTeam(int size, int averageTotalSkill)
	{
		ArrayList<Player> result = new ArrayList<Player>();
		for(int i = 0; i < size; i++)
			result.add(generatePlayer(averageTotalSkill));
		return result;
	}
	
	public static ArrayList<Player> readTeamFromFile(String filePath)
	{
		String sCurrentLine;
		ArrayList<Player> team = new ArrayList<Player>();
		 
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] att = sCurrentLine.split(",");
				team.add(new Player(-1, false, null, Integer.parseInt(att[0]), Integer.parseInt(att[1]), Integer.parseInt(att[2]), 20, Integer.parseInt(att[3]), Integer.parseInt(att[4]), Integer.parseInt(att[5]), Integer.parseInt(att[6]), Integer.parseInt(att[7]), Integer.parseInt(att[8])));
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return team;
	}
	
	public static ArrayList<TeamRatings> readTeamRatingsFromFile(String filePath)
	{
		String sCurrentLine;
		ArrayList<TeamRatings> teamRatingsList = new ArrayList<TeamRatings>();
		 
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] att = sCurrentLine.split(",");
				teamRatingsList.add(new TeamRatings(Integer.parseInt(att[0]), Integer.parseInt(att[1]), Integer.parseInt(att[2]), Integer.parseInt(att[3]), Integer.parseInt(att[4]), Integer.parseInt(att[5]), Integer.parseInt(att[6])));
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return teamRatingsList;
	}
	
	public static void createTeamFile(int size, int averageTotalSkill, String filePath)
	{
		ArrayList<Player> team = generateTeam(size, averageTotalSkill);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
			for(Player player : team)
			{
				bw.write(player.getExperience().getValue() + ","
						+ player.getPlayerForm().getValue() + ","
						+ player.getPlayerSkills().getStaminaSkill().getValue() + ","
						+ player.getPlayerSkills().getKeeperSkill().getValue() + ","
						+ player.getPlayerSkills().getDefenderSkill().getValue() + ","
						+ player.getPlayerSkills().getPlaymakerSkill().getValue() + ","
						+ player.getPlayerSkills().getPassingSkill().getValue() + ","
						+ player.getPlayerSkills().getWingerSkill().getValue() + ","
						+ player.getPlayerSkills().getScorerSkill().getValue());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
