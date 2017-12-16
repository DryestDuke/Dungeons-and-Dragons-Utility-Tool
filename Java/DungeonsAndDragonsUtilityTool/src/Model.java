import java.util.ArrayList;

public class Model {
	
	ArrayList<Creature> creatures = new ArrayList<Creature>(500);
	ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public Model() {
		creatures = Model.loadCreatures();
		npcs = Model.loadNPCs();
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
	 * This loads all creature files and instantiates each of them as a Creature object, returning a list of all of them.
	 * @return all creatures.
	 */
	public static ArrayList<Creature> loadCreatures(){
		return null;
	}
	
	/**
	 * This loads all NPC files and instantiates each of them as a NPC object, returning a list of all of them.
	 * @return all saved NPCs.
	 */
	public static ArrayList<NPC> loadNPCs(){
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
	
	
}