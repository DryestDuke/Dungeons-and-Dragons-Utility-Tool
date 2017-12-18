import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NPCScreen extends JFrame {

	private JPanel contentPane;
	private Model model;
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
	private JTextField textField_tradea;
	private JTextField textField_skill;
	private JTextField textField_worth;

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
		this.model = model;
		
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
		lblNewLabel.setBounds(93, 12, 104, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Generate an NPC");
		lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(409, 12, 120, 17);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane_savedToFile = new JScrollPane();
		scrollPane_savedToFile.setBounds(12, 31, 273, 496);
		contentPane.add(scrollPane_savedToFile);
		
		JList list_savedToFile = new JList();
		list_savedToFile.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane_savedToFile.setViewportView(list_savedToFile);
		
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
		
		textField_tradea = new JTextField();
		textField_tradea.setHorizontalAlignment(SwingConstants.CENTER);
		textField_tradea.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_tradea.setColumns(10);
		textField_tradea.setBounds(370, 347, 212, 22);
		contentPane.add(textField_tradea);
		
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
		btn_randomizeAll.setToolTipText("Click this to randomize all fields above.");
		btn_randomizeAll.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_randomizeAll.setBounds(470, 436, 112, 23);
		contentPane.add(btn_randomizeAll);
		
		JButton btn_saveToFile = new JButton("Save to File");
		btn_saveToFile.setToolTipText("Select an NPC on the right and click this to save them across sessions.");
		btn_saveToFile.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_saveToFile.setBounds(478, 504, 104, 23);
		contentPane.add(btn_saveToFile);
		
		JLabel lblSavedForSession = new JLabel("Generated NPCs");
		lblSavedForSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblSavedForSession.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSavedForSession.setBounds(665, 12, 136, 17);
		contentPane.add(lblSavedForSession);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(596, 31, 273, 496);
		contentPane.add(scrollPane_1);
		
		JList list_savedForSession = new JList();
		list_savedForSession.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane_1.setViewportView(list_savedForSession);
		
		JButton btn_deleteSaved = new JButton("Delete Saved NPC");
		btn_deleteSaved.setToolTipText("Delete some saved NPC that you've selected on the left.");
		btn_deleteSaved.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_deleteSaved.setBounds(296, 504, 136, 23);
		contentPane.add(btn_deleteSaved);
		
		JButton btn_editSaved = new JButton("Edit Saved NPC");
		btn_editSaved.setToolTipText("Click this to edit the values of some saved NPC.");
		btn_editSaved.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_editSaved.setBounds(296, 470, 120, 23);
		contentPane.add(btn_editSaved);
		
		JButton btn_generate = new JButton("Generate NPC");
		btn_generate.setToolTipText("Generates an NPC from the information above. For all fields left blank, it automatically fills them with a random element.");
		btn_generate.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_generate.setBounds(478, 470, 104, 23);
		contentPane.add(btn_generate);
	}
}
