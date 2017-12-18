public class Creature {
	
	public String environment = null;
	public String name = null;
	public String type = null;
	public int xp = -1;
	public String book = null;
	public int pageNumber = -1;
	
	public Creature(String environment, String name, String type, int xp, String book, int page) {
		this.environment = environment.trim();
		this.name = name.trim();
		this.type = type.trim();
		this.xp = xp;
		this.book = book.trim();
		this.pageNumber = page;
	}
	
	public String toString() {
		return name + ", " + type + ", " + xp + "XP, " + book + " - pg. " + pageNumber;
	}
	
	/**
	 * Tests if one creature equals another. Checks all values except environment.
	 * Also, for name, it checks that other.name.contains(this.name).
	 */
	public boolean equals(Creature other) {
		return (this.name.equals(other.name) && this.type.equals(other.type) && this.xp == (other.xp) 
				&& this.book.equals(other.book) && this.pageNumber == (other.pageNumber));
	}
}