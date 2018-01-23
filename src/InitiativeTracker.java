import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class InitiativeTracker extends JFrame {

	private JPanel contentPane;
	private ArrayList<CharacterRolls> currentConditions;

	/**
	 * Launch the application.
	 */
	public static void main(Model model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitiativeTracker frame = new InitiativeTracker(model);
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
	public InitiativeTracker(Model model) {
		currentConditions = new ArrayList<CharacterRolls>();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Initiative Tracker");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 86, 211);
		contentPane.add(scrollPane);
		
		JTextPane textPane_characterNames = new JTextPane();
		textPane_characterNames.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane.setViewportView(textPane_characterNames);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(110, 12, 86, 211);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane_theirRolls = new JTextPane();
		scrollPane_1.setViewportView(textPane_theirRolls);
		textPane_theirRolls.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(208, 12, 224, 211);
		contentPane.add(scrollPane_2);
		
		JList<String> list_initOrder = new JList<String>();
		list_initOrder.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane_2.setViewportView(list_initOrder);
		
		JButton btnOrderInitiative = new JButton("Order Initiative");
		btnOrderInitiative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] characters = textPane_characterNames.getText().split("\n");
				String[] rolls = textPane_theirRolls.getText().split("\n");
				
				int[] rollResults = new int[rolls.length];
				
				for(int c=0;c<(int) characters.length;c++) {
					rollResults[c] = model.roll(rolls[c].trim()).total;
				}
				
				//rollResults[n] = initiative value for characters[n], from rolls[n]
				
				//order characters by their initiative value, display them in list_initOrder
				ArrayList<CharacterRolls> chars = new ArrayList<CharacterRolls>();
				
				for(int c=0;c<characters.length;c++) {
					chars.add(new CharacterRolls(characters[c], rollResults[c]));
				}
				
				Collections.sort(chars, new Comparator<CharacterRolls>() {
				    @Override
				    public int compare(CharacterRolls c1, CharacterRolls c2) {
				    	return new Integer(c2.init).compareTo(new Integer(c1.init));
				    }
				});
				
				ArrayList<String> initOrdering = new ArrayList<String>();
				
				for(CharacterRolls c : chars) {
					initOrdering.add(c.character);
				}
				
				ArrayList<CharacterRolls> nonendedConditions = new ArrayList<CharacterRolls>(currentConditions);				
				for(CharacterRolls c : currentConditions) {
					c.init--;
					
					if(!(c.init < 0)) {
						initOrdering.add(c.character + " | " + c.init);
					}else {
						nonendedConditions.remove(c);
					}
				}
				
				currentConditions = nonendedConditions;
				
				Model.setListString(initOrdering, list_initOrder);
			}
		});
		btnOrderInitiative.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnOrderInitiative.setBounds(296, 234, 136, 23);
		contentPane.add(btnOrderInitiative);
		
		JLabel lblNewLabel = new JLabel("# Rounds:");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 237, 72, 17);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner_ = new JSpinner();
		spinner_.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner_.setBounds(90, 232, 48, 24);
		contentPane.add(spinner_);
		
		JTextField textField_statusEffectName = new JTextField();
		textField_statusEffectName.setToolTipText("Enter name of status effect here (e.g.; \"poisoned\"). Then select the number of rounds it will last. Then click \"Add\".");
		textField_statusEffectName.setHorizontalAlignment(SwingConstants.CENTER);
		textField_statusEffectName.setBounds(144, 233, 86, 22);
		contentPane.add(textField_statusEffectName);
		textField_statusEffectName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get number of rounds from spinner, name from textField_statusEffectName
				int numberRounds = (int) spinner_.getValue();
				String name = textField_statusEffectName.getText();
				//add this to the list of all current conditions
				CharacterRolls c = new CharacterRolls(name, numberRounds);
				currentConditions.add(c);
			}
		});
		btnAdd.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnAdd.setBounds(244, 234, 32, 23);
		contentPane.add(btnAdd);
	}
}

final class CharacterRolls {
	
	public String character;
	public int init;
	
	public CharacterRolls(String character, int init) {
		this.character = character;
		this.init = init;
	}
}
