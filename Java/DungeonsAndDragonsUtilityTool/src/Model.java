import java.util.ArrayList;

public class Model {
	
	ArrayList<Creature> Creatures = new ArrayList<Creature>(500);
	
	public Model() {
		Creatures = Model.loadCreatures();
	}
	
	/**
	 * 
	 * @param numberDice
	 * @param numberSides
	 * @return
	 */
	public static int makeRoll(int numberDice, int numberSides) {
		return 0;
	}
	
	/**
	 * 
	 * @param roll
	 * @return
	 */
	public static int roll(String roll) {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public static ArrayList<Creature> loadCreatures(){
		return null;
	}
	
	/**
	 * 
	 * @param numberPlayers
	 * @param averageLevel
	 * @return
	 */
	public static int calculateXP(int numberPlayers, int averageLevel) {
		return 0;
	}
	
	/**
	 * 
	 * @param attributes
	 * @return
	 */
	public ArrayList<Creature> searchCreatures(ArrayList<String> attributes) {
		return null;
	}
	
	
	/**
	 * 
	 * @param numberCreatures
	 * @param numberBosses
	 * @param numberMinions
	 * @param xpBudget
	 * @param attributes
	 * @return
	 */
	public ArrayList<Creature> generateEncounter(int numberCreatures, int numberBosses, int numberMinions, int xpBudget, ArrayList<String> attributes) {
		return null;
	}
	
	
}