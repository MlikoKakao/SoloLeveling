package solo;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.util.*;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.io.PrintWriter;
import java.io.IOException;


public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Map<String,Integer> stats = loadStats();
		
		boolean pointsAdded = false;
		System.out.println("What stat do you want to increase?");
		System.out.println("HP, MP, STR, VIT, AGL, INTEL, SNS");
		
		
		 while (!pointsAdded) {
			    String input = scanner.next().toLowerCase().trim();
		     
			    if (input.equals("reset")) {
	                resetStats(stats); 
	                saveStats(stats); 
	                pointsAdded = true; 
	            } else if (stats.containsKey(input.toLowerCase())) {
	                stats.put(input.toLowerCase(), stats.get(input.toLowerCase()) + 1); 
	                System.out.println("You increased your " + input.toUpperCase() + " by 1!");
	                pointsAdded = true;
	            } else {
	                System.out.println("Invalid stat. Try again.");
	            }
	        }  
		
		saveStats(stats);
		scanner.close();
		System.out.println(stats);
		
	
		
	}
	public static Map<String, Integer> loadStats() {
		
		Map<String, Integer> stats = new HashMap<>();
		String[] keys = {"hp","mp","str","vit","agl","intel","sns"};
		for (String key : keys) stats.put(key, 0);
		
		File file = new File("stats.txt");
		if (file.exists()) {
			try (Scanner fileScanner = new Scanner(file)) {
				while (fileScanner.hasNextLine()) {
					String [] parts = fileScanner.nextLine().split(":");
					if (parts.length == 2) stats.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
				}
			} catch (Exception e) {
				System.out.println("Error loading stats: " + e.getMessage());
			}
		}
		return stats;
	}
	public static void saveStats(Map<String, Integer> stats) {
		try (PrintWriter writer = new PrintWriter("stats.txt")){
			for (Map.Entry<String, Integer> entry: stats.entrySet()) {
				writer.println(entry.getKey()+": "+entry.getValue());
			}
		} catch (Exception e) {
			System.out.println("Error saving stats: " + e.getMessage());
		}
	}
	public static void resetStats(Map<String, Integer> stats) {
		for (String key : stats.keySet()) {
			stats.put(key, 0);
		}
		System.out.println("Stats have been reset to 0");
		}
	}
	
	


