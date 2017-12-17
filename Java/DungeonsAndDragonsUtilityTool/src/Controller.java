public class Controller {
	public static void main(String[] args) {
		Model model = new Model();
		System.out.println(model.roll("132d40").total);
		System.out.println(model.roll("1d20").total);
		System.out.println(model.roll("0d20").total);
		System.out.println(model.roll("1d1").total);
	}
}