import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Encounter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Creature> encounter;

	/**
	 * Launch the application.
	 */
	public static void main(Model model, ArrayList<Creature> encounter, int numberBosses, int numberMinions, ArrayList<String> attributes, ArrayList<String> types, int xpBudget) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Encounter frame = new Encounter(model, encounter, numberBosses, numberMinions, attributes, types, xpBudget);
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
	public Encounter(Model model, ArrayList<Creature> encounter_, int numberBosses, int numberMinions, ArrayList<String> attributes, ArrayList<String> types, int xpBudget) {
		encounter = encounter_;
		
		setResizable(false);
		setTitle("An Encounter");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> dmodel = new DefaultListModel<>();
		int totalXP = 0;
		for (Creature c : encounter){
			  dmodel.addElement(c.toString());
			  totalXP += c.xp;
		}
		
		JLabel lblNewLabel = new JLabel("XP Budget: " + xpBudget + " | Actual XP Total: " + totalXP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 4, 570, 18);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 34, 570, 227);
		
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<String>(dmodel);
		scrollPane.setViewportView(list);
		list.setValueIsAdjusting(true);
		list.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Improve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Creature> enc = null;
				
				int totalXP = 0;

				for (Creature c : encounter){
					totalXP += c.xp;
				}
				
				while(enc == null) {
					ArrayList<Creature> newEncounter = model.generateEncounter(xpBudget, model.searchCreatures(attributes, types, null));
					int newTotalXP = model.getTotalXP(newEncounter);
					if(Math.abs(newTotalXP-xpBudget) < Math.abs(totalXP-xpBudget) || newTotalXP == xpBudget) {
						totalXP = newTotalXP;
						enc = newEncounter;
					}
				}

				encounter = enc;
				
				Model.setListCreature(enc, list);
				lblNewLabel.setText("XP Budget: " + xpBudget + " | Actual XP Total: " + totalXP);
			}
		});
		btnNewButton.setToolTipText("Click this to bring the Actual XP total more in line with the given xp budget (by changing the creatures).");
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnNewButton.setBounds(240, 266, 121, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("More Creatures");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				encounter = model.changeEncounter(model.searchCreatures(attributes, types, null), encounter, true);
				encounter = model.randomizeEncounter(model.searchCreatures(attributes, types, null), encounter);
				
				int totalXP = 0;

				for (Creature c : encounter){
					totalXP += c.xp;
				}
				
				Model.setListCreature(encounter, list);
				lblNewLabel.setText("XP Budget: " + xpBudget + " | Actual XP Total: " + totalXP);
			}
		});
		btnNewButton_2.setToolTipText("This button takes some creature from the encounter and tries to find two other creatures that can provide the same amount of XP (thereby taking one creature & \"splitting\" it into 2).");
		btnNewButton_2.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnNewButton_2.setBounds(371, 266, 145, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnLessCreatures = new JButton("Less Creatures");
		btnLessCreatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				encounter = model.changeEncounter(model.searchCreatures(attributes, types, null), encounter, false);
				encounter = model.randomizeEncounter(model.searchCreatures(attributes, types, null), encounter);
				
				int totalXP = 0;

				for (Creature c : encounter){
					totalXP += c.xp;
				}
				
				Model.setListCreature(encounter, list);
				lblNewLabel.setText("XP Budget: " + xpBudget + " | Actual XP Total: " + totalXP);
			}
		});
		btnLessCreatures.setToolTipText("This button takes some pair of creatures from the encounter and tries to find another creature that can provide the same amount of XP (thereby taking two creatures & \"combining\" them into 1).");
		btnLessCreatures.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnLessCreatures.setBounds(85, 266, 145, 25);
		contentPane.add(btnLessCreatures);

	}
}
