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
		
		

		System.out.println("HP, MP, STR, VIT, AGL, INTEL, SNS");
		
		for(int i=0;i<5;i++) 
		{ 
		boolean pointsAdded = false;
		 while (!pointsAdded) {
			 System.out.print("Which stat would you like to increase? You have " + (5-i)+" points remaining: ");
			    String input = scanner.next().toLowerCase().trim();
		     
			    if (input.equals("reset")) {
	                resetStats(stats); 
	                saveStats(stats); 
	                pointsAdded = true;
	                i = 5;
	            } else if (stats.containsKey(input.toLowerCase())) {
	                stats.put(input.toLowerCase(), stats.get(input.toLowerCase()) + 1); 
	                System.out.println("You increased your " + input.toUpperCase() + " by 1!");
	                pointsAdded = true;
	            } 
		 else {
	                System.out.println("Invalid stat. Try again.");
	            }
	        }  
		
		saveStats(stats);
		
		System.out.println("Final stats: ");
		List<String> customOrder = List.of("hp", "mp", "str", "vit", "agl", "intel", "sns");

		
		for (String stat : customOrder) {
			String fullName = getFullStatName(stat);
			System.out.print(fullName +": " + stats.get(stat)+" \n");
		}
		}
		scanner.close();
		
	}
	
	public static String getFullStatName(String shortName) {
		switch(shortName) {
		case "hp": return "HP";
        case "mp": return "MP";
        case "str": return "Strength";
        case "vit": return "Vitality";
        case "agl": return "Agility";
        case "intel": return "Intelligence";
        case "sns": return "Sense";
        default: return shortName.toUpperCase();
		}
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
	
	


