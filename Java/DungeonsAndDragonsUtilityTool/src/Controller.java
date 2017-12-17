import java.util.ArrayList;

public class Controller {
	public static void main(String[] args) {
		Model model = new Model();
		ArrayList<String> attributes = new ArrayList<String>();
		//Environment, Name, Type, XP, Book
		attributes.add("Any");
		attributes.add("Any");
		attributes.add("Humanoid");
		attributes.add("Any");
		attributes.add("Any");
		String sortBy = "Environment";
		ArrayList<Creature> output = model.searchCreatures(attributes, sortBy);
		for(Creature c : output) {
			System.out.println(c.toString());
		}
	}
}