import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Model {
	
	ArrayList<Creature> creatures;
	ArrayList<NPC> npcs;
	ArrayList<String> races;
	ArrayList<ArrayList<String>> names;
	ArrayList<String> emotions;
	ArrayList<String> worths;
	ArrayList<String> traits;
	ArrayList<String> ideals;
	ArrayList<String> skills;
	ArrayList<String> trades;
	
	static Random random = new Random();
	
	public Model() {
		creatures = loadCreatures();
		npcs = loadNPCs();
		races = loadFile("files\\Races\\Races_List - Weighted.txt");
		names = loadNames();
		emotions = loadFile("files\\Emotions.txt");
		worths = loadFile("files\\Worth_List - Weighted.txt");
		traits = loadFiles(new String[]{"files\\Traits\\Traits_List - Evil.txt", "files\\Traits\\Traits_List - Neutral.txt", "files\\Traits\\Traits_List - Good.txt"});
		ideals = loadFile("files\\Ideals_List.txt");
		skills = loadFiles(new String[] {"files\\Skills\\Skills_List - BEA.txt", "files\\Skills\\Skills_List - CHA.txt", "files\\Skills\\Skills_List - CON.txt", "files\\Skills\\Skills_List - DEX.txt", "files\\Skills\\Skills_List - INT.txt", "files\\Skills\\Skills_List - STR.txt", "files\\Skills\\Skills_List - WIS.txt"});
		trades = loadFile("files\\Jobs_List.txt");
	}


	/**
	 * For each filename in filenames, it calls loadFile and appends the array lists together.
	 * @param filenames a String[] of file names.
	 * @return an ArrayList of all lines in all files indexed by all entries in filenames.
	 */
	private ArrayList<String> loadFiles(String[] filenames) {
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
	private ArrayList<ArrayList<String>> loadNames() {
		String[] filenames = {"Names_List - Dragon.txt", "Names_List - Dwarf.txt", "Names_List - Elf.txt", "Names_List - Generic.txt", "Names_List - Orc.txt", "Names_List - Tiefling.txt"};
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		
		for(String fn : filenames) {
			ArrayList<String> temp = loadFile("files\\Names\\" + fn);
			temp.add(0, fn.substring(fn.indexOf(" - ")+3, fn.indexOf(".txt")));
			output.add(temp);
		}
		
		return output;
	}

	/**
	 * For some given filename, it loads every line in that file into an ArrayList<String> that it returns.
	 * @param filename The name of the file.
	 * @return An ArrayList<String> of all lines in the file.
	 */
	private ArrayList<String> loadFile(String filename) {
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
	public ArrayList<Creature> loadCreatures(){
		ArrayList<Creature> output = new ArrayList<Creature>();
		String[] filenames = new String[] {"Arctic", "Coastal", "Desert", "Forest", "Grassland", "Hill", "Mountain", "Swamp", "Underdark", "Underwater", "Urban"};
		
		for(String fn : filenames) {
			ArrayList<String> lines = loadFile("files\\Creatures\\Creatures_List - " + fn + ".txt");
			for(String line : lines) {
				//each line represents a different monster entry
					//name, type, xp, bookPageNumber
				String[] splitLine = line.split(",");
				String book = "";
				int pageNumber = -1;
				if(splitLine[3].contains("mm")) {
					book = "Monster Manual";
					pageNumber = Integer.parseInt(splitLine[3].substring(3, splitLine[3].length()));
				}else if(splitLine[3].contains("VGtM")) {
					book = "Volo's Guide to Monsters";
					pageNumber = Integer.parseInt(splitLine[3].substring(splitLine[3].indexOf('-'), splitLine[3].length()));
				}else if(splitLine[3].contains("ToB")) {
					book = "Tome of Beasts";
					pageNumber = Integer.parseInt(splitLine[3].substring(splitLine[3].indexOf('-'), splitLine[3].length()));
				}
				Creature creature = new Creature(fn, splitLine[0], splitLine[1], Integer.parseInt(splitLine[2].trim()), book, pageNumber);
				output.add(creature);
			}
		}
		return output;
	}
	
	/**
	 * This loads all NPC files and instantiates each of them as a NPC object, returning a list of all of them.
	 * @return all saved NPCs.
	 */
	public ArrayList<NPC> loadNPCs(){
		return null;
	}
	
	/**
	 * This method makes a roll, returning the result of the number of dice + the number of sides.
	 * @param numberDice
	 * @param numberSides
	 * @return a Roll.
	 */
	public Roll makeRoll(int numberDice, int numberSides) {
		return null;
	}
	
	/**
	 * This method makes a roll, returning the result within the result set given by the roll.
	 * @param some roll - two numbers split by some string, usually just the character 'd' however.
	 * @return a Roll.
	 */
	public Roll roll(String roll) {
		return null;
	}
	
	/**
	 * Calculates & returns the XP budget for some number of characters with some average level.
	 * @param numberPlayers the number of the players.
	 * @param averageLevel the average levels of all players in the party.
	 * @return the xp budget for such a party.
	 */
	public int calculateXP(int numberPlayers, int averageLevel) {
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
	
	private String getRandomElement(ArrayList<String> list) {
		return getRandomElement(list, 0);
	}
	
	/**
	 * For some ArrayList<String>, return a random String in the list.
	 * @param list This method returns a random String in the list. Please at least have one element in the list.
	 * @param startingIndex The index to start at. Set to 0 if you don't have a reason not to.
	 * @return This method returns a random String in the list provided.
	 */
	private String getRandomElement(ArrayList<String> list, int startingIndex) {
		int max = list.size()-1;
		int min = startingIndex;
		return list.get(random.nextInt((max - min) + 1) + min);
	}
	
	/**
	 * For all races in races, this method returns one of them at random.
	 * @return a random race from races.
	 */
	public String getRace() {
		return getRandomElement(races);
	}
	
	/**
	 * For some race given, it searches through the race lists looking for one that signifies it is of the same race.
	 * If it is, it randomly returns a name from that list. If no appropriate list is found, it just returns a name 
	 * from the list of names that works for any race (i.e.; the generic list)!
	 * @param race_ The race to check against.
	 * @return
	 */
	public String getName(String race_) {
		ArrayList<String> generic = null;
		for(ArrayList<String> namesList : names) {
			if(race_.contains(namesList.get(0)) || namesList.get(0).contains(race_)) {
				return getRandomElement(namesList, 1);
			}
			if(namesList.get(0).contains("Generic")) {
				generic = namesList;
			}
		}
		if(generic == null) {
			generic = loadFile("\\files\\Names\\Names_List - Generic.txt");
		}
		return getRandomElement(generic, 1);
	}
	
	public String generateAge() {
		String[] ages = new String[] {"Younger", "Middle-Aged", "Middle-Aged", "Older"};
		return ages[random.nextInt(ages.length)];
	}

	public String generateGender() {
		String[] genders = new String[] {"Male", "Female"};
		return genders[random.nextInt(genders.length)];
	}

	public String generateSexuality() {
		String[] sexualities = new String[] {"Straight", "Bisexual", "Bisexual", "Bisexual", "Gay"};
		return sexualities[random.nextInt(sexualities.length)];
	}

	public String getEmotion() {
		return getRandomElement(emotions);
	}

	private int generateModifier() {
		Integer[] weightedOptions = new Integer[] {1, 1, 1, 2, 2, 3};
		return weightedOptions[random.nextInt(weightedOptions.length)];
	}
	
	public String generateStats() {
		Integer[] weightedOptions = new Integer[] {0, 0, 1, 1, 1, 2};
		
		int numberBadAt = weightedOptions[random.nextInt(weightedOptions.length)];
		int numberGoodAt = weightedOptions[random.nextInt(weightedOptions.length)];
		
		ArrayList<String> abilityScores = new ArrayList<String>();
		abilityScores.add("STR");
		abilityScores.add("DEX");
		abilityScores.add("CON");
		abilityScores.add("INT");
		abilityScores.add("WIS");
		abilityScores.add("CHA");
		abilityScores.add("BEA");
		
		for(int c=0;c<numberGoodAt;c++) {
			String x = getRandomElement(abilityScores);
			x += " +" + generateModifier();
		}
		
		for(int c=0;c<numberBadAt;c++) {
			String x = getRandomElement(abilityScores);
			x += " -" + generateModifier();
		}
		
		String output = "";
		for(String as : abilityScores) {
			output += as;
			if(!as.equals("BEA")) {
				output += " | ";
			}
		}
		
		return output;
	}

	public String generateMoral() {
		String[] lawchaos_ = new String[] {"Lawful", "Lawful", "Lawful", "Neutral", "Neutral", "Chaotic"};
		String[] goodevil_ = new String[] {"Good", "Good", "Neutral", "Neutral", "Neutral", "Evil"};
		
		String lawchaos = lawchaos_[random.nextInt(lawchaos_.length)];
		String goodevil = goodevil_[random.nextInt(goodevil_.length)];
		
		if(lawchaos.equals("Neutral") && goodevil.equals("Neutral")) {
			return "True Neutral";
		}else {
			return lawchaos + " " + goodevil;
		}
	}

	public String getWorth() {
		return getRandomElement(worths);
	}

	public String getTrait() {
		return getRandomElement(traits);
	}
	
	public String getIdeal() {
		return getRandomElement(ideals);
	}

	public String getSkill() {
		return getRandomElement(skills);
	}

	public String getTrade() {
		return getRandomElement(trades);
	}	
}