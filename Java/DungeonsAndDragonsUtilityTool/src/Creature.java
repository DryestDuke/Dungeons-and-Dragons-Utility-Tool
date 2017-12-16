public class Creature {
	
	String environment = null;
	String name = null;
	String type = null;
	int xp = -1;
	String book = null;
	int pageNumber = -1;
	
	public Creature(String environment, String name, String type, int xp, String book, int page) {
		this.environment = environment;
		this.name = name;
		this.type = type;
		this.xp = xp;
		this.book = book;
		this.pageNumber = page;
	}
}