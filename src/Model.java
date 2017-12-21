import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Model {
	
	ArrayList<Creature> creatures;
	ArrayList<NPC> npcs;
	ArrayList<NPC> savedNPCs;
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
		savedNPCs = loadNPCs();
		npcs = new ArrayList<NPC>(savedNPCs);
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
					book = "MM";
					pageNumber = Integer.parseInt(splitLine[3].substring(3, splitLine[3].length()));
				}else if(splitLine[3].contains("VGtM")) {
					book = "VGtM";
					pageNumber = Integer.parseInt(splitLine[3].substring(splitLine[3].indexOf('-')+1, splitLine[3].length()));
				}else if(splitLine[3].contains("ToB")) {
					book = "ToB";
					pageNumber = Integer.parseInt(splitLine[3].substring(splitLine[3].indexOf('-')+1, splitLine[3].length()));
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
		//going through the file, isolate each NPC block and then call parseNPC on it, adding the result of that to NPCs
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		
		ArrayList<String> lines = loadFile("files\\npcs.npc");
		
		ArrayList<String> npc = new ArrayList<String>();
		
		boolean parsingNPC = false;
		for(String line : lines) {
			if(line.equals("<NPC>")) {
				parsingNPC = true;
				continue;
			}else if(line.equals("</NPC>")){
				parsingNPC = false;
				npcs.add(Model.parseNPC(npc));
				npc.clear();
			}
			
			if(parsingNPC) {
				npc.add(line);
			}
		}
		
		return npcs;
	}
	
	/**
	 * This is a helper method that parses the ArrayList<String> (where every entry is a line between <NPC> and </NPC> in "npcs.npc"), used by model.loadNPCs().
	 * @param npc This is an ArrayList<String>, wherein each String correlates to a line from NPC.generateNPC().toString()
	 * @return an NPC object with the values as in npc.
	 */
	private static NPC parseNPC(ArrayList<String> npc) {
		String header = "";
		int serialNumber = -1;
		String name = "";
		String race = "";
		String age = "";
		String gender = "";
		String sexuality = "";
		String emotion = "";
		String stats = "";
		String moral = "";
		String worth = "";
		String trait = "";
		String ideal = "";
		String skill = "";
		String trade = "";
		
		for(String line : npc) {
			if(line.contains("Header:")) {
				header = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("SN:")) {
				serialNumber = Integer.parseInt(line.substring(line.indexOf(":")+2, line.length()));
			}else if(line.contains("Name:")) {
				name = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Race:")) {
				race = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Age:")) {
				age = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Gender:")) {
				gender = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Sexuality:")) {
				sexuality = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Stats:")) {
				stats = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Moral:")) {
				moral = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Ideal:")) {
				ideal = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Trait:")) {
				trait = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Emotion:")) {
				emotion = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Trade:")) {
				trade = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Skill:")) {
				skill = line.substring(line.indexOf(":")+2, line.length());
			}else if(line.contains("Worth:")) {
				worth = line.substring(line.indexOf(":")+2, line.length());
			}
		}
		
		return new NPC(header, serialNumber, name, race, age, gender, sexuality, 
				emotion, stats, moral, worth, trait, ideal, skill, 
				trade);
	}

	/**
	 * This method makes a roll, returning the result of the number of dice + the number of sides.
	 * @param numberDice
	 * @param numberSides
	 * @param disadvantage 
	 * @param advantage 
	 * @param dropLower 
	 * @param dropUpper 
	 * @param modifier 
	 * @return a Roll.
	 */
	public Roll makeRoll(int numberDice, int numberSides, int addition, int subtraction, int dropUpper, int dropLower, int advantage, int disadvantage) {
		int max = numberSides;
		int min = 1;
		
		int total = 0;
		ArrayList<Integer> rolls = new ArrayList<Integer>(numberDice);
		
		while(numberDice != 0) {
			int roll = random.nextInt(max - min + 1) + min;
			
			for(int c=0;c<advantage;c++) {
				int otherRoll = random.nextInt(max - min + 1) + min;
				if(otherRoll > roll) {
					roll = otherRoll;
				}
			}
			
			for(int c=0;c<disadvantage;c++) {
				int otherRoll = random.nextInt(max - min + 1) + min;
				if(otherRoll < roll) {
					roll = otherRoll;
				}
			}
			
			roll += addition;
			
			roll -= subtraction;
			
			rolls.add(roll);
			numberDice--;
		}
		
		Collections.sort(rolls);
		
		//now that the rolls are sorted from lowest to highest, remove the first dropLower # of rolls and the last dropUpper # of rolls.
		for(int c=0;c<dropLower;c++) {
			rolls.remove(0);
		}
		for(int c=0;c<dropUpper;c++) {
			rolls.remove(rolls.size()-1);
		}
		
		for(int roll : rolls) {
			total += roll;
		}
		
		return new Roll(rolls, total);
	}
	
	/**
	 * This method makes a roll, returning the result within the result set given by the roll.
	 * @param some roll - two numbers split by the character 'd'. 
	 * The first number is the number of die & the second number is the number of sides on all of the die.
	 * You can also add other fields to the roll - for example, you can add a modifier, specify that the lowest or highest rolls should be dropped, 
	 * or apply as much advantage or disadvantage as you'd like. It uses a regular expression to validate String roll, so you can check
	 * the regex if you want to know exactly what is allowed and what is not.
	 * @return a Roll, or null if the roll expression was not correct.
	 */
	public Roll roll(String roll) {
		//format of roll is some amount of digits, followed by some amount of non-digits, followed by some amount of digits.
		String regex = "([0-9]+)(d)([0-9]+)(((\\+)([0-9]+))*)(((-)([0-9]+))*)(((DU)([0-9]+))*)(((DL)([0-9]+))*)(((adv)([0-9]+))*)(((dis)([0-9]+))*)";
		if(!roll.matches(regex)) {
			System.err.println("\"" + roll + "\" does not match the regex.");
			return null;
		}
		else {
			boolean withAddition = roll.contains("+");
			boolean withSubtraction = roll.contains("-");
			boolean withDropUpper = roll.contains("DU");
			boolean withDropLower = roll.contains("DL");
			boolean withAdvantage = roll.contains("adv");
			boolean withDisadvantage = roll.contains("dis");
				
			int addition = 0;
			int subtraction = 0;
			int dropUpper = 0;
			int dropLower = 0;
			int advantage = 0;
			int disadvantage = 0;
			
			if(withAddition) {
				addition = Integer.parseInt(roll.substring(roll.indexOf("+")+1, firstIndexOfNonDigit(roll, roll.indexOf("+")+1)));
			}
			
			if(withSubtraction) {
				subtraction = Integer.parseInt(roll.substring(roll.indexOf("-")+1, firstIndexOfNonDigit(roll, roll.indexOf("-")+1)));
			}
			
			if(withDropUpper) {
				dropUpper = Integer.parseInt(roll.substring(roll.indexOf("DU")+2, firstIndexOfNonDigit(roll, roll.indexOf("DU")+2)));
			}
			
			if(withDropLower) {
				dropLower = Integer.parseInt(roll.substring(roll.indexOf("DL")+2, firstIndexOfNonDigit(roll, roll.indexOf("DL")+2)));
			}
			
			if(withAdvantage) {
				advantage = Integer.parseInt(roll.substring(roll.indexOf("adv")+3, firstIndexOfNonDigit(roll, roll.indexOf("adv")+3)));
			}
			
			if(withDisadvantage) {
				disadvantage = Integer.parseInt(roll.substring(roll.indexOf("dis")+3, firstIndexOfNonDigit(roll, roll.indexOf("dis")+3)));
			}
			
			int numberDice = Integer.parseInt(roll.substring(0, roll.indexOf("d")));
			int numberSides = Integer.parseInt(roll.substring(roll.indexOf("d")+1, firstIndexOfNonDigit(roll, roll.indexOf("d")+1)));
			
			return makeRoll(numberDice, numberSides, addition, subtraction, dropUpper, dropLower, advantage, disadvantage);
		}
	}
	
	/**
	 * For some string, beginning at beginningIndex, it returns the index of the first non-digit character.
	 * @param string The string to be evaluated.
	 * @param beginningIndex The index to begin searching at. It searches to the "right" (in increasing order).
	 * @return the index of the first non-digit character in the given string starting at the beginningIndex.
	 */
	private int firstIndexOfNonDigit(String string, int beginningIndex) {
		string = string.substring(beginningIndex, string.length());
		
		int output = 0;

		for(char c : string.toCharArray()) {
			if(!Character.isDigit(c)) {
				break;
			}
			output++;
		}
		
		return output+beginningIndex;
	}

	/**
	 * Calculates & returns the XP budget for some number of characters with some average level.
	 * @param numberPlayers the number of the players.
	 * @param averageLevel the average levels of all players in the party.
	 * @param difficulty the difficulty of the encounter. Can be 1 (Easy), 2 (Medium), 3 (Hard), or 4 (Deadly).
	 * @return the xp budget for such a party.
	 */
	public int calculateXP(int numberPlayers, int averageLevel, int difficulty) {
		int xpBudget = 0;
		switch(averageLevel) {
		case(1):
			switch(difficulty) {
			case(1):
				xpBudget = 25;
				break;
			case(2):
				xpBudget = 50;
				break;
			case(3):
				xpBudget = 75;
				break;
			case(4):
				xpBudget = 100;
				break;
			}
		break;
		case(2):
			switch(difficulty) {
			case(1):
				xpBudget = 50;
				break;
			case(2):
				xpBudget = 100;
				break;
			case(3):
				xpBudget = 150;
				break;
			case(4):
				xpBudget = 200;
				break;
			}
		break;
		case(3):
			switch(difficulty) {
			case(1):
				xpBudget = 75;
				break;
			case(2):
				xpBudget = 150;
				break;
			case(3):
				xpBudget = 225;
				break;
			case(4):
				xpBudget = 400;
				break;
			}
		break;
		case(4):
			switch(difficulty) {
			case(1):
				xpBudget = 125;
				break;
			case(2):
				xpBudget = 250;
				break;
			case(3):
				xpBudget = 375;
				break;
			case(4):
				xpBudget = 500;
				break;
			}
		break;
		case(5):
			switch(difficulty) {
			case(1):
				xpBudget = 250;
				break;
			case(2):
				xpBudget = 500;
				break;
			case(3):
				xpBudget = 750;
				break;
			case(4):
				xpBudget = 1100;
				break;
			}
		break;
		case(6):
			switch(difficulty) {
			case(1):
				xpBudget = 300;
				break;
			case(2):
				xpBudget = 600;
				break;
			case(3):
				xpBudget = 900;
				break;
			case(4):
				xpBudget = 1400;
				break;
			}
		break;
		case(7):
			switch(difficulty) {
			case(1):
				xpBudget = 350;
				break;
			case(2):
				xpBudget = 750;
				break;
			case(3):
				xpBudget = 1100;
				break;
			case(4):
				xpBudget = 1700;
				break;
			}
		break;
		case(8):
			switch(difficulty) {
			case(1):
				xpBudget = 450;
				break;
			case(2):
				xpBudget = 900;
				break;
			case(3):
				xpBudget = 1400;
				break;
			case(4):
				xpBudget = 2100;
				break;
			}
		break;
		case(9):
			switch(difficulty) {
			case(1):
				xpBudget = 550;
				break;
			case(2):
				xpBudget = 1100;
				break;
			case(3):
				xpBudget = 1600;
				break;
			case(4):
				xpBudget = 2400;
				break;
			}
		break;
		case(10):
			switch(difficulty) {
			case(1):
				xpBudget = 600;
				break;
			case(2):
				xpBudget = 1200;
				break;
			case(3):
				xpBudget = 1900;
				break;
			case(4):
				xpBudget = 2800;
				break;
			}
		break;
		case(11):
			switch(difficulty) {
			case(1):
				xpBudget = 800;
				break;
			case(2):
				xpBudget = 1600;
				break;
			case(3):
				xpBudget = 2400;
				break;
			case(4):
				xpBudget = 3600;
				break;
			}
		break;
		case(12):
			switch(difficulty) {
			case(1):
				xpBudget = 1000;
				break;
			case(2):
				xpBudget = 2000;
				break;
			case(3):
				xpBudget = 3000;
				break;
			case(4):
				xpBudget = 4500;
				break;
			}
		break;
		case(13):
			switch(difficulty) {
			case(1):
				xpBudget = 1100;
				break;
			case(2):
				xpBudget = 2200;
				break;
			case(3):
				xpBudget = 3400;
				break;
			case(4):
				xpBudget = 5100;
				break;
			}
		break;
		case(14):
			switch(difficulty) {
			case(1):
				xpBudget = 1250;
				break;
			case(2):
				xpBudget = 2500;
				break;
			case(3):
				xpBudget = 3800;
				break;
			case(4):
				xpBudget = 5700;
				break;
			}
		break;
		case(15):
			switch(difficulty) {
			case(1):
				xpBudget = 1400;
				break;
			case(2):
				xpBudget = 2800;
				break;
			case(3):
				xpBudget = 4300;
				break;
			case(4):
				xpBudget = 6400;
				break;
			}
		break;
		case(16):
			switch(difficulty) {
			case(1):
				xpBudget = 1600;
				break;
			case(2):
				xpBudget = 3200;
				break;
			case(3):
				xpBudget = 4800;
				break;
			case(4):
				xpBudget = 7200;
				break;
			}
		break;
		case(17):
			switch(difficulty) {
			case(1):
				xpBudget = 2000;
				break;
			case(2):
				xpBudget = 3900;
				break;
			case(3):
				xpBudget = 5900;
				break;
			case(4):
				xpBudget = 8800;
				break;
			}
		break;
		case(18):
			switch(difficulty) {
			case(1):
				xpBudget = 2100;
				break;
			case(2):
				xpBudget = 4200;
				break;
			case(3):
				xpBudget = 6300;
				break;
			case(4):
				xpBudget = 9500;
				break;
			}
		break;
		case(19):
			switch(difficulty) {
			case(1):
				xpBudget = 2400;
				break;
			case(2):
				xpBudget = 4900;
				break;
			case(3):
				xpBudget = 7300;
				break;
			case(4):
				xpBudget = 10900;
				break;
			}
		break;
		case(20):
			switch(difficulty) {
			case(1):
				xpBudget = 2800;
				break;
			case(2):
				xpBudget = 5700;
				break;
			case(3):
				xpBudget = 8500;
				break;
			case(4):
				xpBudget = 12700;
				break;
			}
		break;
		}
		return xpBudget*numberPlayers;
	}
	
	/**
	 * Passes through the creatures object in this object, and compiles a list of all creatures whose attributes match those given in attributes.
	 * @param attributes - the values must be in order of the criteria given for creatures. The order is Environment, Name, Type, XP, Book, Page Number.
	 * However, you cannot use page number to select/sort creatures.
	 * For the names, it checks the name being searched for (from attributes) is contained by the name of any creature we look at.
	 * @param sortBy - this is the attribute by which the creatures will be sorted. The values are any of the attributes, including "Book + PageNumber". If it set as null, we do not sort.
	 * @return a list of all creatures whose attributes fit the values given in attributes.
	 */
	public ArrayList<Creature> searchCreatures(ArrayList<String> attributes, String sortBy) {
		String chosenEnvironment = attributes.get(0);
		String chosenName = attributes.get(1);
		String chosenType = attributes.get(2);
		
		int chosenXP = -1;
		if(!attributes.get(3).equals("Any")) {
			chosenXP = Integer.parseInt(attributes.get(3));
		} 
		
		String chosenBook = attributes.get(4);
		
		ArrayList<Creature> output = new ArrayList<Creature>(creatures.size()/2);
		
		for(Creature creature : creatures) {
			if((creature.environment.equals(chosenEnvironment) || chosenEnvironment.equals("Any")) && 
					(creature.name.contains(chosenName) || chosenName.equals("Any")) &&
					(creature.type.equals(chosenType) || chosenType.equals("Any")) &&
					(creature.xp == chosenXP || chosenXP == -1) &&
					(creature.book.equals(chosenBook) || chosenBook.equals("Any"))) {
				output.add(creature);
			}
		}
		
		if(sortBy == null) {
			//do nothing
		}else if(sortBy.equals("Environment")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return o1.environment.compareTo(o2.environment);
			    }
			});
		}else if(sortBy.equals("Name")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return o1.name.compareTo(o2.name);
			    }
			});
		}else if(sortBy.equals("Type")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return o1.type.compareTo(o2.type);
			    }
			});
		}else if(sortBy.equals("XP")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return new Integer(o1.xp).compareTo(new Integer(o2.xp));
			    }
			});
		}else if(sortBy.equals("Book")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return o1.book.compareTo(o2.book);
			    }
			});
		}else if(sortBy.equals("Page Number")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return new Integer(o1.pageNumber).compareTo(new Integer(o2.pageNumber));
			    }
			});
		}else if(sortBy.equals("Book + PageNumber")) {
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return new Integer(o1.pageNumber).compareTo(new Integer(o2.pageNumber));
			    }
			});
			Collections.sort(output, new Comparator<Creature>() {
			    @Override
			    public int compare(Creature o1, Creature o2) {
			        return o1.book.compareTo(o2.book);
			    }
			});
		}else {
			System.err.println("sortBy value used in searchCreatures(...) was invalid - failed to sort creatures.");
		}
		
		//the exact same creature, but in a different environment, is treated as a different type of creature.
		//so delete all creatures whose attributes are all identical, excluding environment
		ArrayList<Creature> temp = new ArrayList<Creature>(output);
		
		for(int c=0;c<output.size();c++) {
			Creature cr = output.get(c);
			
			for(Creature cre : output) {
				if(cr.equals(cre)) {
					temp.remove(cre);
				}
			}
			
			temp.add(cr);
			
		}
		
		output = temp;
		return output;
	}
	
	/**
	 * For all creatures in this.creatures, it chooses a number of them based off of the xpBudget given.
	 * It tries to find a number of bosses (xp > 50% * xpBudget) = numberBosses, and a number of minions
	 * (xp < 50% * xpBudget) = numberMinions.
	 * If the value of creatures != null, then the list of creatures used will be the list provided.
	 * Otherwise it uses the list of creatures present in Model.
	 * @param numberBosses The number of bosses (a boss is a creature whose XP value > 50% * xpBudget).
	 * @param numberMinions The number of minions (a minion is a creature whose XP value < 50% * xpBudget).
	 * @param xpBudget The xpBudget to which the mob adheres.
	 * @param creatures The list of creatures from which this method generates an encounter. This == null if you wish to use the list already in model.
	 * @return the list of all creatures in the encounter (not including the PCs, obviously).
	 */
	public ArrayList<Creature> generateEncounter(int numberBosses, int numberMinions, int xpBudget, ArrayList<Creature> creatures) {
		ArrayList<Creature> chosenCreatures = creatures;
		if(creatures == null) {
			chosenCreatures = this.creatures;
		}
		
		ArrayList<Creature> encounter = new ArrayList<Creature>();
		
		int actualNumberBosses = 0;
		int actualNumberMinions = 0;
		int bossMinionDivide = xpBudget/2;
		
		ArrayList<Creature> bosses = new ArrayList<Creature>();
		ArrayList<Creature> minions = new ArrayList<Creature>();
		
		for(Creature c : chosenCreatures) {
			if(c.xp > xpBudget) {
				continue;
			}
			if(c.xp >= bossMinionDivide) {
				bosses.add(c);
			}
			if(c.xp <= bossMinionDivide) {
				minions.add(c);
			}
		}
		
		while(xpBudget > 0) {
			//we need more creatures
			if(random.nextBoolean()) {
				if(numberBosses > actualNumberBosses) {
					//we need more bosses
					int max = bosses.size()-1;
					int min = 0;
					Creature newCreature = bosses.get(random.nextInt((max - min) + 1) + min);
					xpBudget -= newCreature.xp;
					encounter.add(newCreature);
					actualNumberBosses++;
				}
			}else {
				if(numberMinions > actualNumberMinions) {
					//we need more minions
					int max = minions.size()-1;
					int min = 0;
					Creature newCreature = minions.get(random.nextInt((max - min) + 1) + min);
					xpBudget -= newCreature.xp;
					encounter.add(newCreature);
					actualNumberMinions++;
				}
			}
			
			if(numberBosses <= actualNumberBosses && numberMinions <= actualNumberMinions) {
				//we got our bosses, we got our minions... but it messed up. somehow we still need to burn through more XP
				Creature bestCreature = null;
				for(Creature c : chosenCreatures) {
					if(bestCreature == null) {
						bestCreature = c;
					}
					if(Math.abs(bestCreature.xp - xpBudget) > Math.abs(c.xp - xpBudget)) {
						bestCreature = c;
					}
				}
				encounter.add(bestCreature);
				xpBudget -= bestCreature.xp;
			}
			
		}
		
		return encounter;
	}
	
	/**
	 * Given some ArrayList<String>, it returns a random element from that list.
	 * @param list
	 * @return
	 */
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
	 * from the list of names that works for any race (i.e.; the generic list)! Also, it randomly chooses between giving
	 * 3 names to giving 1 (the difference between an NPC named "Drax" and an NPC named "Jefferson Beauregard Sessions III".
	 * @param race_ The race to check against.
	 * @return the name as a String.
	 */
	public String getName(String race_) {
		int[] numberNames = new int[] {1, 1, 2, 2, 2, 2, 3};
		
		int max = numberNames.length-1;
		int min = 0;
		int chosenNumber = numberNames[random.nextInt((max - min) + 1) + min];

		String name = "";
		
		ArrayList<String> generic = null;
		for(ArrayList<String> namesList : names) {
			if(race_.contains(namesList.get(0)) || namesList.get(0).contains(race_)) {
				for(int c=0;c<chosenNumber;c++) {
					if(name.equals("")) {
						name = getRandomElement(namesList, 1);
					}else {
						name += " " + getRandomElement(namesList, 1);
					}
				}
				return name;
			}
			if(namesList.get(0).contains("Generic")) {
				generic = namesList;
			}
		}
		if(generic == null) {
			generic = loadFile("\\files\\Names\\Names_List - Generic.txt");
		}
		for(int c=0;c<chosenNumber;c++) {
			if(name.equals("")) {
				name = getRandomElement(generic, 1);
			}else {
				name += " " + getRandomElement(generic, 1);
			}
		}
		return name;
	}
	
	/**
	 * @return some age from {"Younger", "Middle-Aged", "Middle-Aged", "Older"}.
	 */
	public String generateAge() {
		String[] ages = new String[] {"Younger", "Middle-Aged", "Middle-Aged", "Older"};
		return ages[random.nextInt(ages.length)];
	}

	/**
	 * @return return some gender from {"Male", "Female"}.
	 */
	public String generateGender() {
		String[] genders = new String[] {"Male", "Female"};
		return genders[random.nextInt(genders.length)];
	}

	/**
	 * @return return some sexuality from {"Straight", "Bisexual", "Bisexual", "Bisexual", "Gay"}.
	 */
	public String generateSexuality() {
		String[] sexualities = new String[] {"Straight", "Bisexual", "Bisexual", "Bisexual", "Gay"};
		return sexualities[random.nextInt(sexualities.length)];
	}

	/**
	 * @return a random element from emotions.
	 */
	public String getEmotion() {
		return getRandomElement(emotions);
	}

	/**
	 * @return a random modifier from {1, 1, 1, 1, 2, 2, 3}
	 */
	private int generateModifier() {
		Integer[] weightedOptions = new Integer[] {1, 1, 1, 1, 2, 2, 3};
		return weightedOptions[random.nextInt(weightedOptions.length)];
	}
	
	/**
	 * This method chooses some number of stats to make good or bad, and uses generateModifier() to figure out just how good/bad
	 * the character is at these stats.
	 * @return returns the String correlating to the good/bad stats, ordered in the classic order.
	 */
	public String generateStats() {
		Integer[] weightedOptions = new Integer[] {0, 0, 0, 0, 1, 1, 1, 2};
		
		int numberBadAt = weightedOptions[random.nextInt(weightedOptions.length)];
		int numberGoodAt = weightedOptions[random.nextInt(weightedOptions.length)];
		
		while(numberBadAt == 0 && numberGoodAt == 0) {
			numberBadAt = weightedOptions[random.nextInt(weightedOptions.length)];
			numberGoodAt = weightedOptions[random.nextInt(weightedOptions.length)];
		}
		
		ArrayList<String> abilityScores = new ArrayList<String>();
		abilityScores.add("STR");
		abilityScores.add("DEX");
		abilityScores.add("CON");
		abilityScores.add("INT");
		abilityScores.add("WIS");
		abilityScores.add("CHA");
		abilityScores.add("BEA");
		
		ArrayList<String> moddedScores = new ArrayList<String>();
		
		for(int c=0;c<numberGoodAt;c++) {
			String x = getRandomElement(abilityScores);
			abilityScores.remove(x);
			x += " +" + generateModifier();
			moddedScores.add(x);
		}
		
		for(int c=0;c<numberBadAt;c++) {
			String x = getRandomElement(abilityScores);
			abilityScores.remove(x);
			x += " -" + generateModifier();
			moddedScores.add(x);
		}
		
		ArrayList<String> orderedScores = new ArrayList<String>();
		
		//order moddedScores into orderedScores
		for(String entry : moddedScores) {
			if(entry.contains("STR")) {
				orderedScores.add(entry);
			}
		}
		for(String entry : moddedScores) {
			if(entry.contains("DEX")) {
				orderedScores.add(entry);
			}
		}
		for(String entry : moddedScores) {
			if(entry.contains("CON")) {
				orderedScores.add(entry);
			}
		}
		for(String entry : moddedScores) {
			if(entry.contains("INT")) {
				orderedScores.add(entry);
			}
		}
		for(String entry : moddedScores) {
			if(entry.contains("WIS")) {
				orderedScores.add(entry);
			}
		}
		for(String entry : moddedScores) {
			if(entry.contains("CHA")) {
				orderedScores.add(entry);
			}
		}
		for(String entry : moddedScores) {
			if(entry.contains("BEA")) {
				orderedScores.add(entry);
			}
		}
		
		String output = "";
		for(String element : orderedScores) {
			output += element + " | ";
		}
		
		return output.substring(0, output.length()-3);
	}

	/**
	 * This method randomly generate a position on the alignment grid - weighted to prefer Lawful & Neutral, then Good. Chaotic Evil is the least likely.
	 * @return as above.
	 */
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

	/**
	 * Returns a random element from worths.
	 */
	public String getWorth() {
		return getRandomElement(worths);
	}

	/**
	 * Returns a random element from traits.
	 */
	public String getTrait() {
		return getRandomElement(traits);
	}
	
	/**
	 * Returns a random element from ideals.
	 */
	public String getIdeal() {
		return getRandomElement(ideals);
	}

	/**
	 * Returns a random element from skills.
	 */
	public String getSkill() {
		return getRandomElement(skills);
	}

	/**
	 * Returns a random element from trades.
	 */
	public String getTrade() {
		return getRandomElement(trades);
	}	
	
	/**
	 * This method creates an NPC, using the NPC constructor. It then adds the NPC to ArrayList<NPC> npcs, and returns a reference to the NPC.
	 * @return a reference to the NPC object created.
	 */
	public NPC generateNPC(String header, String name, String race, String age, String gender, String sexuality, 
			String emotion, String stats, String moral, String worth, String trait, String ideal, String skill, 
			String trade) {
		NPC npc = new NPC(this, header, name, race, age, gender, sexuality, emotion, stats, moral, worth, trait, ideal, skill, trade);
		npcs.add(npc);
		return npc;
	}

	/**
	 * Generates a unique serial number based off of all of the NPCs in npcs (so all generated NPCs in this session as well as all savedNPCs loaded in).
	 * @return the serial number.
	 */
	public int generateSerialNumber() {
		if(npcs.size() == 0) {
			return 0;
		}
		
		int sn = 0;
		
		ArrayList<Integer> allSerialNumbers = new ArrayList<Integer>(npcs.size());
		
		for(NPC npc : npcs) {
			allSerialNumbers.add(npc.serialNumber);
		}
		
		while(allSerialNumbers.contains(sn)) {
			sn++;
		}
		
		return sn;
	}
	
	/**
	 * For some JList, it replaces all elements in it to the elements in items.
	 * @param items
	 * @param list
	 */
	public static void setListCreature(ArrayList<Creature> items, JList<String> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(Creature c : items) {
			listModel.addElement(c.toString());
		}
		
		list.setModel(listModel);
	}
	
	/**
	 * For some JList, it replaces all elements in it to the elements in items.
	 * @param items
	 * @param list
	 */
	public static void setListString(ArrayList<String> items, JList<String> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(String s : items) {
			listModel.addElement(s);
		}
		
		list.setModel(listModel);
	}
	
	/**
	 * For some serial number of some NPC in npcs, save that npc.toString() to the file "files\\npcs.npc".
	 * @param sn This is the serial number of the NPC in npcs to be written to the file (npcs.npc).
	 * @return True on a successful addition of that NPC to the file.
	 */
	public boolean saveNPC(int sn) {
		boolean output = true;
		try {
			FileWriter fw = new FileWriter(new File("files\\npcs.npc"), true);
			
			NPC npc = null;
			for(NPC npc_ : savedNPCs) {
				if(npc_.serialNumber == sn) {
					npc = npc_;
				}
			}
			
			if(npc == null) {
				fw.close();
				output = false;
				throw new Exception("NPC with SN=" + sn + " was not found in this.savedNPCs ==> \n" + savedNPCs.toString());
			}
			
			fw.write("\n<NPC>\n" + npc.toString() + "\n</NPC>\n");
			
			fw.close();
		} catch (Exception e) {
			System.err.println("Failed to write the NPC with SN=" + sn + " to the save file.");
			output = false;
		}
		return output;
	}

	/**
	 * This method overwrites the save file completely, replacing all of its contents with the NPCs that are currently in this.npcs.
	 */
	public void saveNPCs() {
		try {
			FileWriter fw = new FileWriter(new File("files\\npcs.npc"), false);
			
			for(NPC npc : savedNPCs) {
				fw.write("\n<NPC>\n" + npc.toString() + "\n</NPC>\n");
			}
			
			fw.close();
		} catch (Exception e) {
			System.err.println("Failed to overwrite the file with all current NPCs.");
		}
	}
	
	/**
	 * For some NPC with some serial number (sn), return a reference to them.
	 * @param sn The serial number of the NPC for which we are searching.
	 * @return a reference to the NPC. Returns null if no npc in npcs has that serial number.
	 */
	public NPC getNPC(int sn) {
		NPC npc = null;
		for(NPC npc_ : npcs) {
			if(npc_.serialNumber == sn) {
				npc = npc_;
			}
		}
		return npc;
	}

}