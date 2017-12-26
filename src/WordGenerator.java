import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class WordGenerator extends JFrame {

	private JPanel contentPane;
	private JSpinner spinner_numberWords;

	/**
	 * Launch the application.
	 */
	public static void main(Model model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordGenerator frame = new WordGenerator(model);
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
	public WordGenerator(Model model) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Word Generator");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_generateWords = new JButton("Generate Words");
		btn_generateWords.setToolTipText("Click this to generate some number of words given the language you've selected.");
		btn_generateWords.setFont(new Font("Courier New", Font.PLAIN, 14));
		btn_generateWords.setBounds(126, 12, 120, 23);
		contentPane.add(btn_generateWords);
		
		spinner_numberWords = new JSpinner();
		spinner_numberWords.setToolTipText("This is the number of words to generate.");
		spinner_numberWords.setModel(new SpinnerNumberModel(5, 0, 100, 1));
		spinner_numberWords.setFont(new Font("Courier New", Font.PLAIN, 14));
		spinner_numberWords.setBounds(258, 12, 31, 24);
		contentPane.add(spinner_numberWords);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 420, 211);
		contentPane.add(scrollPane);
		
		JTextArea textArea_generatedWords = new JTextArea();
		textArea_generatedWords.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea_generatedWords);
		
		JComboBox<String> comboBox_language = new JComboBox<String>();
		comboBox_language.setToolTipText("To add more languages to this list, add a new text file with example words for that language in Files\\Language, following the example given by the other lists in the folder.");
		
		String[] fns = model.getAllFilenames("files\\Languages\\");
		int indexFantasy = 0;
		for(int c=0;c<fns.length;c++) {
			fns[c] = fns[c].replace(".txt", "");
			if(fns[c].contains("Fantasy")) {
				indexFantasy = c;
			}
		}
		
		comboBox_language.setModel(new DefaultComboBoxModel<String>(fns));
		comboBox_language.setBounds(12, 11, 100, 22);
		comboBox_language.setSelectedIndex(indexFantasy);
		contentPane.add(comboBox_language);
		
		JSpinner spinner_lengthWords = new JSpinner();
		spinner_lengthWords.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_lengthWords.setToolTipText("This is the length of the words - leave at 0 or 1 to let the system randomly choose a size.");
		spinner_lengthWords.setFont(new Font("Courier New", Font.PLAIN, 14));
		spinner_lengthWords.setBounds(301, 12, 31, 24);
		contentPane.add(spinner_lengthWords);
		
		JRadioButton radio_capitalized = new JRadioButton("Capitalized");
		radio_capitalized.setBounds(340, 9, 92, 26);
		contentPane.add(radio_capitalized);
		
		btn_generateWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean capitalize = radio_capitalized.isSelected();
								
				int numberWords = (Integer) spinner_numberWords.getValue();
				
				MarkovChain mc = MarkovChain.create(("files\\Languages\\" + (String) comboBox_language.getSelectedItem() + ".txt"));
				
				String words = "";
				
				for(int c=0;c<numberWords;c++) {
					words += mc.generateWord((Integer) spinner_lengthWords.getValue(), capitalize) + ", ";
				}
				
				if(words.charAt(words.length()-2) == ',') {
					 words = words.substring(0, words.length()-2);
				}
				
				if(textArea_generatedWords.getText().equals("")) {
					textArea_generatedWords.setText(words);
				}else {
					textArea_generatedWords.setText(textArea_generatedWords.getText() + "\n" + words);
				}
				
			}
		});
	}
}
