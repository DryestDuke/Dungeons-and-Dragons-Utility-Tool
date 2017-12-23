import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class TypesSelector extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TypesSelector(Model model, ArrayList<String> types) {		
		setTitle("Select All Types");
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/com/jtattoo/plaf/icons/empty_8x8.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 227, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 201, 231);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<String>();
		Model.setListString(types, list);
		scrollPane.setViewportView(list);
		
		JButton button = new JButton("Set Types");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] indices = list.getSelectedIndices();
				
				ArrayList<String> selectedTypes = new ArrayList<String>(indices.length);
				
				for(int c=0;c<types.size();c++) {
					for(int index : indices) {
						if(c == index) {
							selectedTypes.add(types.get(index));
						}
					}
				}
				
				types.clear();
				for(String type : selectedTypes) {
					types.add(type);
				}
				
				close();
			}
		});
		button.setToolTipText("Clicking this closes this windows and finalizes your choice of types.");
		button.setFont(new Font("Courier New", Font.PLAIN, 14));
		button.setBounds(59, 246, 105, 25);
		contentPane.add(button);
	}
	
	public void close() {
		this.dispose();
	}
}
