import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Encounter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList<Creature> encounter) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Encounter frame = new Encounter(encounter);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param encounter 
	 */
	public Encounter(ArrayList<Creature> encounter) {
		setTitle("An Encounter");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Creature c : encounter){
			  model.addElement(c.toString());
		}
		
		JList<String> list = new JList<String>(model);
		list.setFont(new Font("Courier New", Font.PLAIN, 14));
		list.setBounds(12, 12, 420, 246);
		contentPane.add(list);
		

		
	}
}
