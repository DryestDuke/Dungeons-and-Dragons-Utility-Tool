import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class NPCScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField_header;
	private JTextField textField_name;
	private JTextField textField_race;
	private JTextField textField_age;
	private JTextField textField_gender;
	private JTextField textField_sexuality;
	private JTextField textField_stats;
	private JTextField textField_moral;
	private JTextField textField_ideal;
	private JTextField textField_trait;
	private JTextField textField_emotion;
	private JTextField textField_trade;
	private JTextField textField_skill;
	private JTextField textField_worth;
	private JTextField textField_sn;

	/**
	 * Launch the application.
	 */
	public static void main(Model model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NPCScreen frame = new NPCScreen(model);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NPCScreen(Model model) {
		setResizable(false);
		setTitle("NPCs");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Saved to File");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 12, 104, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Generate an NPC");
		lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(409, 12, 120, 17);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane_savedToFile = new JScrollPane();
		scrollPane_savedToFile.setBounds(12, 31, 273, 496);
		contentPane.add(scrollPane_savedToFile);
		
		JTextPane textPane_savedToFile = new JTextPane();
		textPane_savedToFile.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		String temp = "";
		for(NPC npc : model.savedNPCs) {
			if(temp.equals("")) {
				temp += npc.toString();
			}else {
				temp += "\n-----------------------\n" + npc.toString();
			}
		}
		textPane_savedToFile.setText(temp);
		
		scrollPane_savedToFile.setViewportView(textPane_savedToFile);
		
		JLabel lblNewLabel_2 = new JLabel("Header:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(303, 34, 64, 17);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblSsn = new JLabel("Name:");
		lblSsn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSsn.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSsn.setBounds(316, 61, 64, 17);
		contentPane.add(lblSsn);
		
		JLabel lblRace = new JLabel("Race:");
		lblRace.setHorizontalAlignment(SwingConstants.CENTER);
		lblRace.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblRace.setBounds(326, 90, 48, 17);
		contentPane.add(lblRace);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblAge.setBounds(338, 119, 32, 17);
		contentPane.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblGender.setBounds(314, 148, 56, 17);
		contentPane.add(lblGender);
		
		JLabel lblSexuality = new JLabel("Sexuality:");
		lblSexuality.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexuality.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSexuality.setBounds(290, 177, 80, 17);
		contentPane.add(lblSexuality);
		
		JLabel lblStats = new JLabel("Stats:");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblStats.setBounds(322, 206, 48, 17);
		contentPane.add(lblStats);
		
		JLabel lblMoral = new JLabel("Moral:");
		lblMoral.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoral.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblMoral.setBounds(322, 235, 48, 17);
		contentPane.add(lblMoral);
		
		JLabel lblIdeal = new JLabel("Ideal:");
		lblIdeal.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdeal.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblIdeal.setBounds(322, 264, 48, 17);
		contentPane.add(lblIdeal);
		
		JLabel lblTrait = new JLabel("Trait:");
		lblTrait.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrait.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblTrait.setBounds(322, 293, 48, 17);
		contentPane.add(lblTrait);
		
		JLabel lblEmotion = new JLabel("Emotion:");
		lblEmotion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmotion.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblEmotion.setBounds(306, 322, 64, 17);
		contentPane.add(lblEmotion);
		
		JLabel lblTrade = new JLabel("Trade:");
		lblTrade.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrade.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblTrade.setBounds(322, 351, 48, 17);
		contentPane.add(lblTrade);
		
		JLabel lblSkill = new JLabel("Skill:");
		lblSkill.setHorizontalAlignment(SwingConstants.CENTER);
		lblSkill.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSkill.setBounds(322, 380, 48, 17);
		contentPane.add(lblSkill);
		
		JLabel lblWorth = new JLabel("Worth:");
		lblWorth.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorth.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblWorth.setBounds(322, 407, 48, 17);
		contentPane.add(lblWorth);
		
		textField_header = new JTextField();
		textField_header.setText(" ");
		textField_header.setHorizontalAlignment(SwingConstants.CENTER);
		textField_header.setToolTipText("This is custom information for the NPC.");
		textField_header.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_header.setBounds(370, 30, 212, 22);
		contentPane.add(textField_header);
		textField_header.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_name.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_name.setColumns(10);
		textField_name.setBounds(370, 57, 212, 22);
		contentPane.add(textField_name);
		
		textField_race = new JTextField();
		textField_race.setHorizontalAlignment(SwingConstants.CENTER);
		textField_race.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_race.setColumns(10);
		textField_race.setBounds(370, 86, 212, 22);
		contentPane.add(textField_race);
		
		textField_age = new JTextField();
		textField_age.setHorizontalAlignment(SwingConstants.CENTER);
		textField_age.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_age.setColumns(10);
		textField_age.setBounds(370, 115, 212, 22);
		contentPane.add(textField_age);
		
		textField_gender = new JTextField();
		textField_gender.setHorizontalAlignment(SwingConstants.CENTER);
		textField_gender.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_gender.setColumns(10);
		textField_gender.setBounds(370, 144, 212, 22);
		contentPane.add(textField_gender);
		
		textField_sexuality = new JTextField();
		textField_sexuality.setHorizontalAlignment(SwingConstants.CENTER);
		textField_sexuality.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_sexuality.setColumns(10);
		textField_sexuality.setBounds(370, 173, 212, 22);
		contentPane.add(textField_sexuality);
		
		textField_stats = new JTextField();
		textField_stats.setHorizontalAlignment(SwingConstants.CENTER);
		textField_stats.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_stats.setColumns(10);
		textField_stats.setBounds(370, 202, 212, 22);
		contentPane.add(textField_stats);
		
		textField_moral = new JTextField();
		textField_moral.setHorizontalAlignment(SwingConstants.CENTER);
		textField_moral.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_moral.setColumns(10);
		textField_moral.setBounds(370, 231, 212, 22);
		contentPane.add(textField_moral);
		
		textField_ideal = new JTextField();
		textField_ideal.setHorizontalAlignment(SwingConstants.CENTER);
		textField_ideal.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_ideal.setColumns(10);
		textField_ideal.setBounds(370, 260, 212, 22);
		contentPane.add(textField_ideal);
		
		textField_trait = new JTextField();
		textField_trait.setHorizontalAlignment(SwingConstants.CENTER);
		textField_trait.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_trait.setColumns(10);
		textField_trait.setBounds(370, 289, 212, 22);
		contentPane.add(textField_trait);
		
		textField_emotion = new JTextField();
		textField_emotion.setHorizontalAlignment(SwingConstants.CENTER);
		textField_emotion.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_emotion.setColumns(10);
		textField_emotion.setBounds(370, 318, 212, 22);
		contentPane.add(textField_emotion);
		
		textField_trade = new JTextField();
		textField_trade.setHorizontalAlignment(SwingConstants.CENTER);
		textField_trade.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_trade.setColumns(10);
		textField_trade.setBounds(370, 347, 212, 22);
		contentPane.add(textField_trade);
		
		textField_skill = new JTextField();
		textField_skill.setHorizontalAlignment(SwingConstants.CENTER);
		textField_skill.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_skill.setColumns(10);
		textField_skill.setBounds(370, 376, 212, 22);
		contentPane.add(textField_skill);
		
		textField_worth = new JTextField();
		textField_worth.setHorizontalAlignment(SwingConstants.CENTER);
		textField_worth.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_worth.setColumns(10);
		textField_worth.setBounds(370, 403, 212, 22);
		contentPane.add(textField_worth);
		
		JButton btn_randomizeAll = new JButton("Randomize All");
		btn_randomizeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_race.setText(model.getRace());
				textField_name.setText(model.getName(textField_race.getText()));
				textField_age.setText(model.generateAge());
				textField_gender.setText(model.generateGender());
				textField_sexuality.setText(model.generateSexuality());
				textField_stats.setText(model.generateStats());
				textField_moral.setText(model.generateMoral());
				textField_ideal.setText(model.getIdeal());
				textField_trait.setText(model.getTrait());
				textField_emotion.setText(model.getEmotion());
				textField_trade.setText(model.getTrade());
				textField_skill.setText(model.getSkill());
				textField_worth.setText(model.getWorth());
			}
		});
		btn_randomizeAll.setToolTipText("Click this to randomize all fields above.");
		btn_randomizeAll.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_randomizeAll.setBounds(470, 436, 112, 23);
		contentPane.add(btn_randomizeAll);
		
		JLabel lblSavedForSession = new JLabel("Generated NPCs");
		lblSavedForSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblSavedForSession.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSavedForSession.setBounds(733, 12, 136, 17);		
		contentPane.add(lblSavedForSession);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(596, 31, 273, 496);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane_savedForSession = new JTextPane();
		textPane_savedForSession.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		temp = "";
		for(NPC npc : model.npcs) {
			if(model.savedNPCs.contains(npc)) {
				continue;
			}
			
			if(temp.equals("")) {
				temp += npc.toString();
			}else {
				temp += "\n-----------------------\n" + npc.toString();
			}
		}
		textPane_savedForSession.setText(temp);
		
		scrollPane_1.setViewportView(textPane_savedForSession);
		
		JButton btn_deleteSaved = new JButton("Delete Saved NPC");
		btn_deleteSaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int sn = Integer.parseInt(textField_sn.getText());
					//delete them from savedNPCs
					NPC toDelete = null;
					for(NPC npc : model.savedNPCs) {
						if(npc.serialNumber == sn) {
							toDelete = npc;
						}
					}
					model.savedNPCs.remove(toDelete);
					//rewrite save file
					model.saveNPCs();
					//rewrite textPane_savedToFile with savedNPCs (as per usual)
					String temp = "";
					for(NPC npc_ : model.savedNPCs) {
						if(temp.equals("")) {
							temp += npc_.toString();
						}else {
							temp += "\n-----------------------\n" + npc_.toString();
						}
					}
					textPane_savedToFile.setText(temp);
				}catch (Exception e1) {
					System.err.println(e1);
					new JErrorPane("Error in deleting the saved NPC. Make sure you've filled in the SN correctly!");
					return;
				}
			}
		});
		btn_deleteSaved.setToolTipText("Click this to delete some NPC from the file.");
		btn_deleteSaved.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_deleteSaved.setBounds(296, 504, 136, 23);
		contentPane.add(btn_deleteSaved);
		
		JButton btn_editSaved = new JButton("Edit Saved NPC");
		btn_editSaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//use the dropdowns/text boxes where you fill in information to generate an NPC as the information that will be overwriting
					//the values for the NPC you are editing. 
				NPC ourNPC = null;
				
				for(NPC npc : model.savedNPCs) {
					if(npc.serialNumber == Integer.parseInt(textField_sn.getText())) {
						ourNPC = npc;
						break;
					}
				}
				
				String header = textField_header.getText();
				String name = textField_name.getText();
				String race = textField_race.getText();
				String age = textField_age.getText();
				String gender = textField_gender.getText();
				String sexuality = textField_sexuality.getText();
				String emotion = textField_emotion.getText();
				String stats = textField_stats.getText();
				String moral = textField_moral.getText();
				String worth = textField_worth.getText();
				String trait = textField_trait.getText();
				String ideal = textField_ideal.getText();
				String skill = textField_skill.getText();
				String trade = textField_trade.getText();
				
				if(header.equals("")) {
					header = ourNPC.header;
				}
				
				if(name.equals("")) {
					name = ourNPC.name;
				}
				
				if(race.equals("")) {
					race = ourNPC.race;
				}
				
				if(age.equals("")) {
					age = ourNPC.age;
				}
				
				if(gender.equals("")) {
					gender = ourNPC.gender;
				}
				
				if(sexuality.equals("")) {
					sexuality = ourNPC.sexuality;
				}
				
				if(emotion.equals("")) {
					emotion = ourNPC.emotion;
				}
				
				if(stats.equals("")) {
					stats = ourNPC.stats;
				}
				
				if(moral.equals("")) {
					moral = ourNPC.moral;
				}
				
				if(worth.equals("")) {
					worth = ourNPC.worth;
				}
				
				if(trait.equals("")) {
					trait = ourNPC.trait;
				}
				
				if(ideal.equals("")) {
					ideal = ourNPC.ideal;
				}
				
				if(skill.equals("")) {
					skill = ourNPC.skill;
				}
				
				if(trade.equals("")) {
					trade = ourNPC.trade;
				}
				
				ourNPC = new NPC(header, ourNPC.serialNumber, name, race, age, gender, sexuality, emotion, stats, moral, 
						worth, trait, ideal, skill, trade);
				
				int indexOldNPC = -1;
				for(NPC npc : model.savedNPCs) {
					if(npc.serialNumber == ourNPC.serialNumber) {
						indexOldNPC = model.savedNPCs.indexOf(npc);
						model.savedNPCs.remove(npc);
						break;
					}
				}
				
				model.savedNPCs.add(indexOldNPC, ourNPC);
								
				model.saveNPCs();
				
				//this should also update the entry on the left hand side
				String temp = "";
				for(NPC npc_ : model.savedNPCs) {
					if(temp.equals("")) {
						temp += npc_.toString();
					}else {
						temp += "\n-----------------------\n" + npc_.toString();
					}
				}
				textPane_savedToFile.setText(temp);
				
				}catch(Exception e1) {
					System.err.println(e1);
					new JErrorPane("Error in editing the saved NPC. Make sure you've filled in the SN and the attributes correctly! Also the NPC should exist on the left-hand side.");
					return;
				}
			}
		});
		btn_editSaved.setToolTipText("Click this to edit the values of some saved NPC.");
		btn_editSaved.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_editSaved.setBounds(296, 470, 120, 23);
		contentPane.add(btn_editSaved);
		
		JButton btn_generate = new JButton("Generate NPC");
		btn_generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//for all of the values in the textFields, create an NPC with those values.
					//if some value is empty, don't fret. it's the user's fault :-)
				
				String header = textField_header.getText();
				String name = textField_name.getText();
				String race = textField_race.getText();
				String age = textField_age.getText();
				String gender = textField_gender.getText();
				String sexuality = textField_sexuality.getText();
				String emotion = textField_emotion.getText();
				String stats = textField_stats.getText();
				String moral = textField_moral.getText();
				String worth = textField_worth.getText();
				String trait = textField_trait.getText();
				String ideal = textField_ideal.getText();
				String skill = textField_skill.getText();
				String trade = textField_trade.getText();
				
				if(race.equals("")) {
					race = model.getRace();
				}
				
				if(name.equals("")) {
					name = model.getName(race);
				}
				
				if(age.equals("")) {
					age = model.generateAge();
				}
				
				if(gender.equals("")) {
					gender = model.generateGender();
				}
				
				if(sexuality.equals("")) {
					sexuality = model.generateSexuality();
				}
				
				if(emotion.equals("")) {
					emotion = model.getEmotion();
				}
				
				if(stats.equals("")) {
					stats = model.generateStats();
				}
				
				if(moral.equals("")) {
					moral = model.generateMoral();
				}
				
				if(worth.equals("")) {
					worth = model.getWorth();
				}
				
				if(trait.equals("")) {
					trait = model.getTrait();
				}
				
				if(ideal.equals("")) {
					ideal = model.getIdeal();
				}
				
				if(skill.equals("")) {
					skill = model.getSkill();
				}
				
				if(trade.equals("")) {
					trade = model.getTrade();
				}
				
				NPC npc = model.generateNPC(header, name, race, age, gender, sexuality, emotion, stats, moral, 
						worth, trait, ideal, skill, trade);
				//then pop them over into list_savedForSession - making sure to format them all good n' shit
				if(textPane_savedForSession.getText().equals("")) {
					textPane_savedForSession.setText(npc.toString());
				}else {
					textPane_savedForSession.setText(textPane_savedForSession.getText() + "\n-----------------------\n" + npc.toString());
				}
			}
		});
		btn_generate.setToolTipText("Click this to generate an NPC from the information above. If a field is left blank, the corresponding value for the NPC will be randomly generated.");
		btn_generate.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_generate.setBounds(478, 470, 104, 23);
		contentPane.add(btn_generate);
		
		JLabel lblSerialNumber = new JLabel("SN:");
		lblSerialNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerialNumber.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSerialNumber.setBounds(303, 439, 24, 17);
		contentPane.add(lblSerialNumber);
		
		textField_sn = new JTextField();
		textField_sn.setHorizontalAlignment(SwingConstants.CENTER);
		textField_sn.setToolTipText("Here you type in the Serial Number (or \"SN\") of the NPC with which you are attempting to interact.");
		textField_sn.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_sn.setBounds(332, 435, 89, 22);
		contentPane.add(textField_sn);
		textField_sn.setColumns(10);
		
		JButton btn_saveToFile = new JButton("Save to File");
		btn_saveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sn = 0;
				try {
					sn = Integer.parseInt(textField_sn.getText());
				}catch(Exception e) {
					new JErrorPane("Fill in the SN appropriately for the NPC with which you are attempting to interact.");
					return;
				}
				if(textField_sn.getText().equals("")) {
					new JErrorPane("Fill in the SN of the NPC with which you are attempting to interact.");
					return;
				}
				
				//add this npc to the list of savedNPCs
				model.savedNPCs.add(model.getNPC(sn));
				
				//then, save the NPC to the file
				if(model.saveNPC(sn)) {
					//if that happened successfully, add this to the savedNPCs pane (as it is now a saved NPC).
					if(textPane_savedToFile.getText().equals("")) {
						textPane_savedToFile.setText(model.getNPC(sn).toString());
					}else {
						textPane_savedToFile.setText(textPane_savedToFile.getText() + "\n-----------------------\n" + model.getNPC(sn).toString());
					}
				}				
			}
		});
		btn_saveToFile.setToolTipText("Click this to save some generated NPC to file - and also to add them to the pane on the left.");
		btn_saveToFile.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_saveToFile.setBounds(478, 504, 104, 23);
		contentPane.add(btn_saveToFile);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Courier New", Font.PLAIN, 14));
		menuBar.setBounds(0, 0, 41, 21);
		contentPane.add(menuBar);
		
		JMenu menu_edit = new JMenu("Edit");
		menu_edit.setFont(new Font("Courier New", Font.PLAIN, 14));
		menu_edit.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu_edit);
		
		JMenuItem item_load = new JMenuItem("Load an NPC");
		item_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NPC npc = model.getNPC(Integer.parseInt(textField_sn.getText()));
				textField_header.setText(npc.header);
				textField_name.setText(npc.name);
				textField_race.setText(npc.race);
				textField_age.setText(npc.age);
				textField_gender.setText(npc.gender);
				textField_sexuality.setText(npc.sexuality);
				textField_stats.setText(npc.stats);
				textField_moral.setText(npc.moral);
				textField_ideal.setText(npc.ideal);
				textField_trait.setText(npc.trait);
				textField_emotion.setText(npc.emotion);
				textField_trade.setText(npc.trade);
				textField_skill.setText(npc.skill);
				textField_worth.setText(npc.worth);
			}
		});
		item_load.setHorizontalAlignment(SwingConstants.CENTER);
		item_load.setFont(new Font("Courier New", Font.PLAIN, 14));
		menu_edit.add(item_load);
		
		JMenuItem item_clear = new JMenuItem("Clear Fields");
		item_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_header.setText("");
				textField_name.setText("");
				textField_race.setText("");
				textField_age.setText("");
				textField_gender.setText("");
				textField_sexuality.setText("");
				textField_stats.setText("");
				textField_moral.setText("");
				textField_ideal.setText("");
				textField_trait.setText("");
				textField_emotion.setText("");
				textField_trade.setText("");
				textField_skill.setText("");
				textField_worth.setText("");
			}
		});
		item_clear.setHorizontalAlignment(SwingConstants.CENTER);
		item_clear.setFont(new Font("Courier New", Font.PLAIN, 14));
		menu_edit.add(item_clear);
		
		JButton btn_removeSavedToFile = new JButton("Remove");
		btn_removeSavedToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeFromTextField(textPane_savedToFile, Integer.parseInt(textField_sn.getText()));	
			}
		});
		btn_removeSavedToFile.setToolTipText("Click this to remove some NPC given by some SN from this pane (but not to delete them from the system or the file).");
		btn_removeSavedToFile.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_removeSavedToFile.setBounds(176, 9, 81, 22);
		contentPane.add(btn_removeSavedToFile);
		
		JButton btn_removeGeneratedNPCs = new JButton("Remove");
		btn_removeGeneratedNPCs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeFromTextField(textPane_savedForSession, Integer.parseInt(textField_sn.getText()));	
			}
		});
		btn_removeGeneratedNPCs.setToolTipText("Click this to remove some NPC given by some SN from this pane (but not to delete them from the system or the file).");
		btn_removeGeneratedNPCs.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_removeGeneratedNPCs.setBounds(628, 9, 81, 22);
		contentPane.add(btn_removeGeneratedNPCs);
	}
}
