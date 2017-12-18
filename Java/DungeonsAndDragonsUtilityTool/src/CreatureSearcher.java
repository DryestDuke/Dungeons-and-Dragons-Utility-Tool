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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreatureSearcher extends JFrame {

	private JPanel contentPane;
	private Model model;
	private JLabel lblEnvironment;
	private JLabel lblName;
	private JLabel lblType;
	private JLabel lblBook;
	private JLabel lblXp;
	private JComboBox comboBox_environment;
	private JTextField textField_name;
	private JTextField textField_type;
	private JTextField textField_xp;
	private JComboBox comboBox_book;
	private JButton btn_searchForCreatures;
	private JLabel lblSortTheResults;
	private JComboBox comboBox_sort;
	private JScrollPane scrollPane;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(Model model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatureSearcher frame = new CreatureSearcher(model);
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
	public CreatureSearcher(Model model) {
		setTitle("Creature Searcher");
		setResizable(false);
		this.model = model;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEnvironment = new JLabel("Environment:");
		lblEnvironment.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblEnvironment.setBounds(12, 77, 96, 17);
		contentPane.add(lblEnvironment);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblName.setBounds(52, 106, 40, 17);
		contentPane.add(lblName);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblType.setBounds(52, 135, 40, 17);
		contentPane.add(lblType);
		
		lblBook = new JLabel("Book:");
		lblBook.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblBook.setBounds(52, 193, 40, 17);
		contentPane.add(lblBook);
		
		lblXp = new JLabel("XP:");
		lblXp.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblXp.setBounds(68, 164, 24, 17);
		contentPane.add(lblXp);
		
		comboBox_environment = new JComboBox();
		comboBox_environment.setToolTipText("Select one.");
		comboBox_environment.setModel(new DefaultComboBoxModel(new String[] {"Any", "Arctic", "Coastal", "Desert", "Forest", "Grassland", "Hill", "Mountain", "Swamp", "Underdark", "Underwater", "Urban"}));
		comboBox_environment.setFont(new Font("Courier New", Font.PLAIN, 14));
		comboBox_environment.setBounds(114, 74, 106, 22);
		contentPane.add(comboBox_environment);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_name.setText("Any");
		textField_name.setToolTipText("Type in the name you're looking for.");
		textField_name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_name.setBounds(106, 106, 114, 22);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_type = new JTextField();
		textField_type.setToolTipText("Select the type of the creatures.");
		textField_type.setHorizontalAlignment(SwingConstants.CENTER);
		textField_type.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_type.setText("Any");
		textField_type.setBounds(106, 133, 114, 22);
		contentPane.add(textField_type);
		textField_type.setColumns(10);
		
		textField_xp = new JTextField();
		textField_xp.setToolTipText("Select the type of the creatures.");
		textField_xp.setText("Any");
		textField_xp.setHorizontalAlignment(SwingConstants.CENTER);
		textField_xp.setFont(new Font("Courier New", Font.PLAIN, 14));
		textField_xp.setColumns(10);
		textField_xp.setBounds(106, 162, 114, 22);
		contentPane.add(textField_xp);
		
		comboBox_book = new JComboBox();
		comboBox_book.setModel(new DefaultComboBoxModel(new String[] {"Any", "MM", "VGtM", "ToB"}));
		comboBox_book.setToolTipText("Select one.");
		comboBox_book.setFont(new Font("Courier New", Font.PLAIN, 14));
		comboBox_book.setBounds(106, 190, 114, 22);
		contentPane.add(comboBox_book);
		
		lblSortTheResults = new JLabel("Sort By:");
		lblSortTheResults.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSortTheResults.setBounds(28, 222, 64, 17);
		contentPane.add(lblSortTheResults);
		
		comboBox_sort = new JComboBox();
		comboBox_sort.setModel(new DefaultComboBoxModel(new String[] {"Environment", "Name", "Type", "XP", "Book", "Book + PageNumber", "Don't Bother"}));
		comboBox_sort.setToolTipText("Select one.");
		comboBox_sort.setFont(new Font("Courier New", Font.PLAIN, 14));
		comboBox_sort.setBounds(106, 219, 114, 22);
		contentPane.add(comboBox_sort);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 12, 296, 246);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		btn_searchForCreatures = new JButton("Search for Creatures");
		btn_searchForCreatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> attributes = new ArrayList<String>();
				attributes.add((String) comboBox_environment.getSelectedItem());
				attributes.add(textField_name.getText());
				attributes.add(textField_type.getText());
				attributes.add(textField_xp.getText());
				attributes.add((String) comboBox_book.getSelectedItem());
				
				String sortBy = (String) comboBox_sort.getSelectedItem();
				
				if(sortBy.equals("Don't Bother")) {
					sortBy = null;
				}
				
				ArrayList<Creature> results = model.searchCreatures(attributes, sortBy);
				
				Model.setListCreature(results, list);
			}
		});
		btn_searchForCreatures.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_searchForCreatures.setBounds(26, 28, 168, 23);
		contentPane.add(btn_searchForCreatures);
	}

}
