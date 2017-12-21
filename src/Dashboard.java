import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model = new Model();
	private Roll lastRoll = null;
	
	private JPanel contentPane;
	private JTextField textField_roll;
	private JTextField textField_rollResult;
	private JButton btn_seeAllRolls;
	private JTextField textfield_numCharacters;
	private JTextField textField_averageLevel;
	private JTextField textField_xp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setResizable(false);
		setTitle("Dungeons & Dragons Utility Tool");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_roll = new JTextField();
		textField_roll.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_roll.setText("1d20");
		textField_roll.setToolTipText("Type your roll in here.");
		textField_roll.setHorizontalAlignment(SwingConstants.CENTER);
		textField_roll.setBounds(12, 236, 85, 22);
		contentPane.add(textField_roll);
		textField_roll.setColumns(10);
		
		JButton btn_roll = new JButton("Roll");
		btn_roll.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_roll.setToolTipText("Click this to submit the roll on the left & see it in the box on the right.");
		btn_roll.setBounds(108, 234, 73, 24);
		contentPane.add(btn_roll);
		btn_roll.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					lastRoll = model.roll(textField_roll.getText());
					textField_rollResult.setText(lastRoll.total + "");
				}catch (Exception e) {
					new JErrorPane("Invalid roll: " + textField_roll.getText() + ". Please fix. Please.");
					System.err.println(e.toString());
				}
			}
		});
		
		textField_rollResult = new JTextField();
		textField_rollResult.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_rollResult.setHorizontalAlignment(SwingConstants.CENTER);
		textField_rollResult.setToolTipText("Here is where the result of your roll is shown.");
		textField_rollResult.setBounds(318, 236, 114, 22);
		contentPane.add(textField_rollResult);
		textField_rollResult.setColumns(10);
		
		btn_seeAllRolls = new JButton("See All Rolls");
		btn_seeAllRolls.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_seeAllRolls.setToolTipText("Press this button after you've rolled to see a sorted list of dice rolled as a part of your last roll.");
		btn_seeAllRolls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lastRoll == null) {
					new JErrorPane("Make a roll first. Any roll, it doesn't matter. Just make one.");
					return;
				}
				else {
					JOptionPane.showMessageDialog(null, lastRoll.getSortedRollsAsString());
				}
			}
		});
		btn_seeAllRolls.setBounds(192, 234, 114, 24);
		contentPane.add(btn_seeAllRolls);
		
		JLabel lblFor = new JLabel("For");
		lblFor.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblFor.setBounds(12, 12, 24, 18);
		contentPane.add(lblFor);
		
		textfield_numCharacters = new JTextField();
		textfield_numCharacters.setToolTipText("Type in the number of characters in the party here.");
		textfield_numCharacters.setHorizontalAlignment(SwingConstants.CENTER);
		textfield_numCharacters.setFont(new Font("Courier New", Font.PLAIN, 14));
		textfield_numCharacters.setBounds(42, 11, 38, 22);
		contentPane.add(textfield_numCharacters);
		textfield_numCharacters.setColumns(10);
		
		JLabel lblCharactersOf = new JLabel("Characters with an Average Level of ");
		lblCharactersOf.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblCharactersOf.setBounds(83, 12, 288, 18);
		contentPane.add(lblCharactersOf);
		
		textField_averageLevel = new JTextField();
		textField_averageLevel.setToolTipText("Type in the average level of the party members here.");
		textField_averageLevel.setHorizontalAlignment(SwingConstants.CENTER);
		textField_averageLevel.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_averageLevel.setColumns(10);
		textField_averageLevel.setBounds(368, 11, 64, 22);
		contentPane.add(textField_averageLevel);
		
		JLabel lblAndWith = new JLabel("and of");
		lblAndWith.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblAndWith.setBounds(12, 42, 64, 18);
		contentPane.add(lblAndWith);
		
		JLabel lblDifficulty = new JLabel("Difficulty: ");
		lblDifficulty.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblDifficulty.setBounds(171, 42, 96, 18);
		contentPane.add(lblDifficulty);
		
		JComboBox<String> comboBox_difficulty = new JComboBox<String>();
		comboBox_difficulty.setModel(new DefaultComboBoxModel<String>(new String[] {"Easy", "Medium", "Hard", "Deadly"}));
		comboBox_difficulty.setSelectedIndex(1);
		comboBox_difficulty.setToolTipText("Choose a difficulty. Medium is automatically selected.");
		comboBox_difficulty.setFont(new Font("Courier New", Font.PLAIN, 14));
		comboBox_difficulty.setMaximumRowCount(4);
		comboBox_difficulty.setBounds(66, 40, 100, 22);
		contentPane.add(comboBox_difficulty);
		
		textField_xp = new JTextField();
		textField_xp.setToolTipText("This is the XP budget given for the situation above.a");
		textField_xp.setHorizontalAlignment(SwingConstants.CENTER);
		textField_xp.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_xp.setColumns(10);
		textField_xp.setBounds(318, 66, 64, 22);
		contentPane.add(textField_xp);
		
		JButton btn_calcXPBudget = new JButton("Calculate XP Budget");
		btn_calcXPBudget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int numberCharacters = Integer.parseInt(textfield_numCharacters.getText());
					int averageLevel = Integer.parseInt(textField_averageLevel.getText());
					int difficulty = comboBox_difficulty.getSelectedIndex()+1;
					
					textField_xp.setText("" + model.calculateXP(numberCharacters, averageLevel, difficulty));
				}catch(Exception e) {
					new JErrorPane("Values in fields for XP budget are invalid.\nPlease ensure all valid numbers using digits.");
					return;
				}
				
			}
		});
		btn_calcXPBudget.setToolTipText("Click to calculate the XP budget, based off of the values above.");
		btn_calcXPBudget.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_calcXPBudget.setBounds(266, 41, 166, 24);
		contentPane.add(btn_calcXPBudget);
		
		JButton btn_generateEncounter = new JButton("Generate Encounter");
		btn_generateEncounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//XP budget is taken from the value in textField_3, or must be typed in
				int xpBudget = 0;
				boolean failed = false;
				try{
					xpBudget = Integer.parseInt(textField_xp.getText());
				}catch(Exception e1) {
					failed = true;
				}
				
				if(failed || textField_xp.getText().equals("")) {
					xpBudget = -1;
				}
				
				EncounterGenerator.main(model, xpBudget);
			}
		});
		btn_generateEncounter.setToolTipText("Click to begin generating an encounter.");
		btn_generateEncounter.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_generateEncounter.setBounds(12, 86, 169, 24);
		contentPane.add(btn_generateEncounter);
		
		JButton btn_searchCreatures = new JButton("Search Creatures");
		btn_searchCreatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatureSearcher.main(model);
			}
		});
		btn_searchCreatures.setToolTipText("Click to search through the list of all creatures.");
		btn_searchCreatures.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_searchCreatures.setBounds(12, 136, 169, 24);
		contentPane.add(btn_searchCreatures);
		
		JButton btn_openNpcScreen = new JButton("Open NPC Screen");
		btn_openNpcScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NPCScreen.main(model);
			}
		});
		btn_openNpcScreen.setToolTipText("Open the NPC screen tonight.");
		btn_openNpcScreen.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_openNpcScreen.setBounds(12, 187, 169, 24);
		contentPane.add(btn_openNpcScreen);
	}
}
