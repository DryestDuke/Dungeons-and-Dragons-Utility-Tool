public class Creature {
	
	public String environment = null;
	public String name = null;
	public String type = null;
	public int xp = -1;
	public String book = null;
	public int pageNumber = -1;
	
	public Creature(String environment, String name, String type, int xp, String book, int page) {
		this.environment = environment;
		this.name = name;
		this.type = type;
		this.xp = xp;
		this.book = book;
		this.pageNumber = page;
	}
}