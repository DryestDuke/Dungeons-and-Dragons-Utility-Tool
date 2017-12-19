import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EncounterGenerator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_xpBudget;
	private JLabel lblAnd;
	private JTextField textField_numBosses;
	private JLabel lblAreBossesAnd;
	private JTextField textField_numMinions;
	private JButton btn_generateEncounter;
	private JLabel lblAlsoTheCreatures;
	private JLabel lblEnvironment;
	private JLabel lblName;
	private JLabel lblType;
	private JLabel lblBook;
	private JLabel lblXp;
	private JComboBox<String> comboBox_environment;
	private JTextField textField_name;
	private JTextField textField_type;
	private JTextField textField_xpIndividual;
	private JComboBox<String> comboBox_book;
	private JLabel lblBossesAnd;
	
	/**
	 * Launch the application.
	 */
	public static void main(Model model, int initialXPBudget) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncounterGenerator frame = new EncounterGenerator(model, initialXPBudget);
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
	public EncounterGenerator(Model model, int initialXPBudget) {
		setResizable(false);
		setTitle("Encounter Generator");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("XP:");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 12, 29, 18);
		contentPane.add(lblNewLabel);
		
		textField_xpBudget = new JTextField();
		textField_xpBudget.setToolTipText("Change this to represent the XP budget for the encounter.");
		textField_xpBudget.setHorizontalAlignment(SwingConstants.CENTER);
		textField_xpBudget.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_xpBudget.setBounds(39, 9, 59, 22);
		contentPane.add(textField_xpBudget);
		textField_xpBudget.setColumns(10);
		
		lblAnd = new JLabel("In this encounter, try to have");
		lblAnd.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblAnd.setBounds(12, 42, 240, 17);
		contentPane.add(lblAnd);
		
		textField_numBosses = new JTextField();
		textField_numBosses.setToolTipText("The number of bosses in the encounter (whose XP >= 50% XP budget).");
		textField_numBosses.setHorizontalAlignment(SwingConstants.CENTER);
		textField_numBosses.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_numBosses.setColumns(10);
		textField_numBosses.setBounds(256, 40, 59, 22);
		contentPane.add(textField_numBosses);
		
		lblAreBossesAnd = new JLabel("minions.");
		lblAreBossesAnd.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblAreBossesAnd.setBounds(76, 76, 112, 17);
		contentPane.add(lblAreBossesAnd);
		
		textField_numMinions = new JTextField();
		textField_numMinions.setToolTipText("The number of minions in the encounter (whose XP <= 50% XP budget).");
		textField_numMinions.setHorizontalAlignment(SwingConstants.CENTER);
		textField_numMinions.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_numMinions.setColumns(10);
		textField_numMinions.setBounds(12, 74, 53, 22);
		contentPane.add(textField_numMinions);
		
		lblAlsoTheCreatures = new JLabel("Also, the creatures must fit these criteria:");
		lblAlsoTheCreatures.setFont(new Font("Courier New", Font.BOLD, 14));
		lblAlsoTheCreatures.setBounds(12, 131, 352, 17);
		contentPane.add(lblAlsoTheCreatures);
		
		lblEnvironment = new JLabel("Environment:");
		lblEnvironment.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblEnvironment.setBounds(12, 154, 96, 17);
		contentPane.add(lblEnvironment);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblName.setBounds(12, 183, 40, 17);
		contentPane.add(lblName);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblType.setBounds(12, 212, 40, 17);
		contentPane.add(lblType);
		
		lblBook = new JLabel("Book:");
		lblBook.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblBook.setBounds(227, 154, 40, 17);
		contentPane.add(lblBook);
		
		lblXp = new JLabel("XP:");
		lblXp.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblXp.setBounds(12, 241, 24, 17);
		contentPane.add(lblXp);
		
		comboBox_environment = new JComboBox<String>();
		comboBox_environment.setToolTipText("Select one.");
		comboBox_environment.setModel(new DefaultComboBoxModel<String>(new String[] {"Any", "Arctic", "Coastal", "Desert", "Forest", "Grassland", "Hill", "Mountain", "Swamp", "Underdark", "Underwater", "Urban"}));
		comboBox_environment.setFont(new Font("Courier New", Font.PLAIN, 14));
		comboBox_environment.setBounds(114, 150, 106, 22);
		contentPane.add(comboBox_environment);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_name.setText("Any");
		textField_name.setToolTipText("Type in the name you're looking for.");
		textField_name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_name.setBounds(70, 179, 114, 22);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_type = new JTextField();
		textField_type.setToolTipText("Select the type of the creatures.");
		textField_type.setHorizontalAlignment(SwingConstants.CENTER);
		textField_type.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_type.setText("Any");
		textField_type.setBounds(70, 208, 114, 22);
		contentPane.add(textField_type);
		textField_type.setColumns(10);
		
		textField_xpIndividual = new JTextField();
		textField_xpIndividual.setToolTipText("Select the XP of the creatures.");
		textField_xpIndividual.setHorizontalAlignment(SwingConstants.CENTER);
		textField_xpIndividual.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_xpIndividual.setText("Any");
		textField_xpIndividual.setBounds(70, 237, 114, 22);
		contentPane.add(textField_xpIndividual);
		textField_xpIndividual.setColumns(10);
		
		comboBox_book = new JComboBox<String>();
		comboBox_book.setModel(new DefaultComboBoxModel<String>(new String[] {"Any", "MM", "VGtM", "ToB"}));
		comboBox_book.setToolTipText("Select one.");
		comboBox_book.setFont(new Font("Courier New", Font.PLAIN, 14));
		comboBox_book.setBounds(275, 150, 106, 22);
		contentPane.add(comboBox_book);
		
		if(initialXPBudget > -1) {
			textField_xpBudget.setText("" + initialXPBudget);
		}
		
		btn_generateEncounter = new JButton("Generate Encounter");
		btn_generateEncounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int xpBudget = initialXPBudget;
				if(initialXPBudget == -1) {
					try{
						xpBudget = Integer.parseInt(textField_xpBudget.getText());
					}catch(Exception e1) {
						new JErrorPane("Invalid value in the XP field. Please fix.");
						return;
					}
				}
				int numberBosses = 0;
				int numberMinions = 0;
				try {
					numberBosses = Integer.parseInt(textField_numBosses.getText());
					numberMinions = Integer.parseInt(textField_numMinions.getText());
				}catch (Exception e2) {
					System.err.println("You failed to input correct numbers to the bosses/minions fields.");
				}
				if(numberBosses <= 0 && numberMinions <= 0) {
					numberBosses = Integer.MAX_VALUE;
					numberMinions = Integer.MAX_VALUE;
				}
				
				ArrayList<String> attributes = new ArrayList<String>();
				attributes.add((String) comboBox_environment.getSelectedItem());
				attributes.add(textField_name.getText());
				attributes.add(textField_type.getText());
				attributes.add(textField_xpIndividual.getText());
				attributes.add((String) comboBox_book.getSelectedItem());

				//generate the encounter
				ArrayList<Creature> encounter = model.generateEncounter(numberBosses, numberMinions, xpBudget, 
						model.searchCreatures(attributes, null));

				for(Creature c : encounter) {
					System.out.println(c);
				}
				//run it
				Encounter.main(encounter, xpBudget);
			}
		});
		btn_generateEncounter.setToolTipText("Click this to generate the encounter based off of the fields you've provided.");
		btn_generateEncounter.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_generateEncounter.setBounds(12, 104, 152, 23);
		contentPane.add(btn_generateEncounter);
		
		lblBossesAnd = new JLabel("bosses, and");
		lblBossesAnd.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblBossesAnd.setBounds(320, 40, 112, 17);
		contentPane.add(lblBossesAnd);
		
	}

}
