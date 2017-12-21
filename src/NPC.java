public class NPC {
	
	public int serialNumber;
	
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
				+ "\n  Race: " + race
				+ "\n  Age: " + age
				+ "\n  Gender: " + gender
				+ "\n  Sexuality: " + sexuality
				+ "\n     Stats: " + stats
				+ "\n     Moral: " + moral
				+ "\n     Ideal: " + ideal
				+ "\n     Trait: " + trait
				+ "\n  Emotion: " + emotion
				+ "\n  Trade: " + trade
				+ "\n  Skill: " + skill
				+ "\n  Worth: " + worth;
				
	}
	
	public NPC(String header, int serialNumber, String name, String race, String age, String gender, String sexuality, 
			String emotion, String stats, String moral, String worth, String trait, String ideal, String skill, 
			String trade) {
		this.serialNumber = serialNumber;
		
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
	
	public NPC(Model model, String header, String name, String race, String age, String gender, String sexuality, 
			String emotion, String stats, String moral, String worth, String trait, String ideal, String skill, 
			String trade) {
		this.serialNumber = model.generateSerialNumber();
		
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
	
	public static NPC generateNPC(Model model) {
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
		
		return new NPC(model, header_, name_, race_, age_, gender_, sexuality_, 
				 emotion_, stats_, moral_, worth_, trait_, ideal_, skill_, 
				 trade_);
	}
}