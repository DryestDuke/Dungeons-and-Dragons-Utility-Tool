import java.util.ArrayList;

public class Roll {
	
	public ArrayList<Integer> sortedRolls = null;
	public int total = 0;
	
	public Roll(ArrayList<Integer> sortedRolls, int total) {
		this.sortedRolls = sortedRolls;
		this.total = total;
	}

	public String getSortedRollsAsString() {
		String output = sortedRolls.toString();
		return output.replaceAll("(.{100})", "$1\n");
	}
}