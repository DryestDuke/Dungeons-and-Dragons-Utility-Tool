import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
	
	static ArrayList<Creature> creatures;
	static ArrayList<NPC> npcs;
	static ArrayList<String> races;
	static ArrayList<ArrayList<String>> names;
	static ArrayList<String> emotions;
	static ArrayList<String> worths;
	static ArrayList<String> traits;
	static ArrayList<String> ideals;
	static ArrayList<String> skills;
	static ArrayList<String> trades;
	
	public Model() {
		creatures = Model.loadCreatures();
		npcs = Model.loadNPCs();
		races = Model.loadFile("Races_List - Weighted.txt");
		names = Model.loadNames();
		emotions = Model.loadFile("Emotions.txt");
		worths = Model.loadFile("Worth_List - Weighted.txt");
		traits = Model.loadFiles(new String[]{"Traits_List - Evil.txt", "Traits_List - Neutral.txt", "Traits_List - Good.txt"});
		ideals = Model.loadFile("Ideals_List.txt");
		skills = Model.loadFiles(new String[] {"Skills_List - BEA.txt", "Skills_List - CHA.txt", "Skills_List - CON.txt", "Skills_List - DEX.txt", "Skills_List - INT.txt", "Skills_List - STR.txt", "Skills_List - WIS.txt"});
		trades = Model.loadFile("Jobs_List.txt");
	}


	/**
	 * For each filename in filenames, it calls loadFile and appends the array lists together.
	 * @param filenames a String[] of file names.
	 * @return an ArrayList of all lines in all files indexed by all entries in filenames.
	 */
	private static ArrayList<String> loadFiles(String[] filenames) {
		ArrayList<String> output = new ArrayList<String>();
		
		for(String filename : filenames) {
			ArrayList<String> lines = loadFile(filename);
			for(String line : lines) {
				output.add(line);
			}
		}
		
		return output;
	}


	/**
	 * Returns two nested ArrayList<String>'s, for all names files.
	 * @return An ArrayList of ArrayList<String>'s. Each ArrayList within starts with the Race.
	 * for example: "Dragonborn, Surnames" or "Generic, Either" (& etc).
	 */
	private static ArrayList<ArrayList<String>> loadNames() {
		String[] filenames = {"Names_List - Dragonborn.txt", "Names_List - Dwarf.txt", "Names_List - Elf.txt", "Names_List - Generic.txt", "Names_List - Orcish.txt", "Names_List - Tiefling.txt"};
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		
		for(String fn : filenames) {
			ArrayList<String> temp = loadFile(fn);
			temp.add(0, fn.substring(fn.indexOf(" - "), fn.indexOf(".txt")));
			output.add(temp);
		}
		
		return output;
	}

	/**
	 * For some given filename, it loads every line in that file into an ArrayList<String> that it returns.
	 * @param filename The name of the file.
	 * @return An ArrayList<String> of all lines in the file.
	 */
	private static ArrayList<String> loadFile(String filename) {
		try{
			ArrayList<String> output = new ArrayList<String>();
			
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				output.add(scanner.nextLine());
			}
			
			scanner.close();
			
			return output;
		}catch (Exception e) {
			System.err.println("Failed reading file: " + filename + ".");
			return null;
		}
	}
	
	/**
	 * This loads all creature files and instantiates each of them as a Creature object, returning a list of all of them.
	 * @return all creatures.
	 */
	public static ArrayList<Creature> loadCreatures(){
		ArrayList<Creature> output = new ArrayList<Creature>();
		String[] filenames = new String[] {"Arctic", "Coastal", "Desert", "Forest", "Grassland", "Hill", "Mountain", "Swamp", "Underdark", "Underwater", "Urban"};
		
		for(String fn : filenames) {
			ArrayList<String> lines = loadFile("Creatures_List - " + fn + ".txt");
			for(String line : lines) {
				//each line represents a different monster entry
					//name, type, xp, bookPageNumber
				String[] splitLine = line.split(",");
				String book = "";
				int pageNumber = -1;
				if(splitLine[3].contains("mm")) {
					book = "Monster Manual";
					pageNumber = Integer.parseInt(splitLine[3].substring(2, splitLine[3].length()));
				}else if(splitLine[3].contains("VGtM")) {
					book = "Volo's Guide to Monsters";
					pageNumber = Integer.parseInt(splitLine[3].substring(splitLine[3].indexOf('-'), splitLine[3].length()));
				}else if(splitLine[3].contains("ToB")) {
					book = "Tome of Beasts";
					pageNumber = Integer.parseInt(splitLine[3].substring(splitLine[3].indexOf('-'), splitLine[3].length()));
				}
				Creature creature = new Creature(fn, splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]), book, pageNumber);
				output.add(creature);
			}
		}
		return output;
	}
	
	/**
	 * This loads all NPC files and instantiates each of them as a NPC object, returning a list of all of them.
	 * @return all saved NPCs.
	 */
	public static ArrayList<NPC> loadNPCs(){
		return null;
	}
	
	/**
	 * This method makes a roll, returning the result of the number of dice + the number of sides.
	 * @param numberDice
	 * @param numberSides
	 * @return a Roll.
	 */
	public static Roll makeRoll(int numberDice, int numberSides) {
		return null;
	}
	
	/**
	 * This method makes a roll, returning the result within the result set given by the roll.
	 * @param some roll - two numbers split by some string, usually just the character 'd' however.
	 * @return a Roll.
	 */
	public static Roll roll(String roll) {
		return null;
	}
	
	/**
	 * Calculates & returns the XP budget for some number of characters with some average level.
	 * @param numberPlayers the number of the players.
	 * @param averageLevel the average levels of all players in the party.
	 * @return the xp budget for such a party.
	 */
	public static int calculateXP(int numberPlayers, int averageLevel) {
		return 0;
	}
	
	/**
	 * Passes through the creatures object in this object, and compiles a list of all creatures whose attributes match those given in attributes.
	 * @param attributes - the values must be in order of the criteria given for creatures. The order is Environment, Name, Type, XP, Book, Page Number. 
	 * @return a list of all creatures whose attributes fit the values given in attributes.
	 */
	public ArrayList<Creature> searchCreatures(ArrayList<String> attributes) {
		return null;
	}
	
	
	/**
	 * For all creatures in this.creatures, it chooses a number of them based off of the xpBudget given.
	 * It tries to find a number of bosses (xp > 50% * xpBudget) = numberBosses, and a number of minions
	 * (xp < 50% * xpBudget) = numberMinions. numberBosses + numberMinions must equal numberCreatures.
	 * If the value of creatures != null, then the list of creatures used will be the list provided.
	 * @param numberCreatures The number of creatures to be in the mob.
	 * @param numberBosses The number of bosses (a boss is a creature whose XP value > 50% * xpBudget).
	 * @param numberMinions The number of minions (a minion is a creature whose XP value < 50% * xpBudget).
	 * @param xpBudget The xpBudget to which the mob adheres.
	 * @param creatures The list of creatures from which this method generates an encounter. This == null if you wish to use the list already in model.
	 * @return the list of all creatures in the encounter (not including the PCs, obviously).
	 */
	public ArrayList<Creature> generateEncounter(int numberCreatures, int numberBosses, int numberMinions, int xpBudget, ArrayList<Creature> creatures) {
		//ensure numberBosses + numberMinions !>< numberCreaturessqaa
		return null;
	}

	public static String getRace() {
		for(String race : races) {
			
		}
		return null;
	}

	public static String getName(String race_) {

		return null;
	}
	
	public static String generateAge() {

		return null;
	}

	public static String generateGender() {

		return null;
	}

	public static String generateSexuality() {

		return null;
	}

	public static String getEmotion() {

		return null;
	}

	public static String generateStats() {

		return null;
	}

	public static String getMoral() {

		return null;
	}

	public static String getWorth() {

		return null;
	}

	public static String getTrait() {

		return null;
	}

	public static String getSkill(String stats_) {

		return null;
	}

	public static String getTrade() {

		return null;
	}	
}