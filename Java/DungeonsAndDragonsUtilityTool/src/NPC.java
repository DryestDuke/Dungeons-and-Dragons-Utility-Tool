import java.util.Random;

public class NPC {
	
	private static Model model = new Model();
	
	private int serialNumber;
	
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
				+ "\nSN: " + serialNumber
				+ "\nName: " + name
				+ "\n Race: " + race
				+ "\n Age: " + age
				+ "\n Gender: " + gender
				+ "\n Sexuality: " + sexuality
				+ "\n   Stats: " + stats
				+ "\n   Moral: " + moral
				+ "\n   Ideal: " + ideal
				+ "\n   Trait: " + trait
				+ "\n Emotion: " + emotion
				+ "\n Trade: " + trade
				+ "\n Skill: " + skill
				+ "\n Worth: " + worth;
				
	}
	
	public NPC(int serialNumber, String header, String name, String race, String age, String gender, String sexuality, 
			String emotion, String stats, String moral, String worth, String trait, String ideal, String skill, 
			String trade) {
		if(model.serialNumbers.contains(serialNumber)) {
			Random rand = new Random();
			int sn = rand.nextInt(Integer.MAX_VALUE);
			while(model.serialNumbers.contains(sn)) {
				sn = rand.nextInt(Integer.MAX_VALUE);
			}
			model.serialNumbers.add(sn);
		}else {
			model.serialNumbers.add(serialNumber);
		}
		
		this.header = header;
		this.serialNumber = serialNumber;
		
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
		int serialNumber_ = new Random().nextInt(Integer.MAX_VALUE);
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