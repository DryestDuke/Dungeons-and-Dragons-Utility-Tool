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
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Initiative Tracker");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number of Characters:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 14, 168, 17);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		spinner.setBounds(192, 9, 41, 24);
		contentPane.add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 45, 126, 211);
		contentPane.add(scrollPane);
		
		JTextPane textPane_characterNames = new JTextPane();
		textPane_characterNames.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane.setViewportView(textPane_characterNames);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(150, 45, 126, 211);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane_theirRolls = new JTextPane();
		textPane_theirRolls.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane_1.setViewportView(textPane_theirRolls);
		
		JButton btn_help = new JButton("Help");
		btn_help.setToolTipText("Click for help.");
		btn_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JErrorPane("Select the number of character. Then, on the left hand side, write in on each line the name of a character. On the right hand side, write in their initiative roll.");
			}
		});
		btn_help.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_help.setBounds(392, 11, 40, 23);
		contentPane.add(btn_help);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(288, 45, 144, 211);
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
				
				for(int c=0;c<(int) spinner.getValue();c++) {
					rollResults[c] = model.roll(rolls[c].trim()).total;
				}
				
				//rollResults[n] = initiative value for characters[n], from rolls[n]
				
				//order characters by their initiative value, display them in list_initOrder
				ArrayList<CharacterRolls> chars = new ArrayList<CharacterRolls>();
				
				for(int c=0;c<(int) spinner.getValue();c++) {
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
				
				Model.setListString(initOrdering, list_initOrder);
			}
		});
		btnOrderInitiative.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnOrderInitiative.setBounds(244, 11, 136, 23);
		contentPane.add(btnOrderInitiative);
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
