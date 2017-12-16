import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class NPC {
	private long serialNumber;
	static ArrayList<Long> serialNumbers = new ArrayList<Long>();
	
	public String header;
	
	public String name;
	public String race;
	public String age;
	
	public String gender;
	public String sexuality;
	public String emotion;
	
	public String stats;
	
	public String moral;
	public String worth;
	public String trait;
	public String ideal;
	public String skill;
	public String trade;
	
	public String toString() {
		return "Header: " + header
				+ "\nName: " + name
				+ "\n Race: " + race
				+ "\n Age: " + age
				+ "\n Gender: " + gender
				+ "\n Sexuality: " + sexuality
				+ "\n Emotion: " + emotion
				+ "\n   Stats: " + stats
				+ "\n   Moral: " + moral
				+ "\n   Ideal: " + ideal
				+ "\n   Trait: " + trait
				+ "\n Trade: " + trade
				+ "\n Skill: " + skill
				+ "\n Worth: " + worth;
				
	}
	
	public NPC(long serialNumber, String header, String name, String race, String age, String gender, String sexuality, 
			String emotion, String stats, String moral, String worth, String trait, String ideal, String skill, 
			String trade) {
		if(serialNumbers.contains(serialNumber)) {
			Random rand = new Random();
			long sn = rand.nextLong();
			while(serialNumbers.contains(sn)) {
				sn = rand.nextLong();
			}
			serialNumbers.add(sn);
		}
		
		this.header = header;
		
		this.name = name;
		this.race = race;
		this.age = age;
		
		this.gender = gender;
		this.sexuality = sexuality;
		this.emotion = emotion;
		
		this.stats = stats;
		
		this.moral = moral;
		this.worth = worth;
		this.trait = trait;
		this.ideal = ideal;
		this.skill = skill;
		this.trade = trade;
	}
	
	public static NPC generateNPC() {
		Model model = new Model();
		
		long serialNumber_ = new Random().nextLong();
		String header_ = "";
		
		String race_ = model.getRace();
		
		String name_ = model.getName(race_) + " " + model.getName(race_);
		String age_ = model.generateAge();
		
		String gender_ = model.generateGender();
		String sexuality_ = model.generateSexuality();
		String emotion_ = model.getEmotion();
		
		String stats_ = model.generateStats();
		
		String moral_ = model.generateMoral();
		String worth_ = model.getWorth();
		String trait_ = model.getTrait();
		String ideal_ = model.getIdeal();
		String skill_ = model.getSkill();
		String trade_ = model.getTrade();
		
		return new NPC(serialNumber_, header_, name_, race_, age_, gender_, sexuality_, 
				 emotion_, stats_, moral_, worth_, trait_, ideal_, skill_, 
				 trade_);
	}
	
	public static void main(String[] args) {
		System.out.println(NPC.generateNPC().toString());
	}
}