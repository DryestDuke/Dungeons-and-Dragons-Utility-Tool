import java.util.ArrayList;
import java.util.Random;


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
		return "" + name;
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
		long serialNumber_ = new Random().nextLong();
		String header_ = "";
		
		String race_ = Model.getRace();
		String name_ = Model.getName(race_);
		String age_ = Model.generateAge();
		
		String gender_ = Model.generateGender();
		String sexuality_ = Model.generateSexuality();
		String emotion_ = Model.getEmotion();
		
		String stats_ = Model.generateStats();
		
		String moral_ = Model.getMoral();
		String worth_ = Model.getWorth();
		String trait_ = Model.getTrait();
		String ideal_ = Model.getMoral();
		String skill_ = Model.getSkill(stats_);
		String trade_ = Model.getTrade();
		
		return new NPC(serialNumber_, header_, name_, race_, age_, gender_, sexuality_, 
				 emotion_, stats_, moral_, worth_, trait_, ideal_, skill_, 
				 trade_);
	}
	
	public static void main(String[] args) {
		System.out.println(NPC.generateNPC().toString());
	}
}