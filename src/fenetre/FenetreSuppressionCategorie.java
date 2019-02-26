package fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreSuppressionCategorie extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;

	
	public FenetreSuppressionCategorie(String lesCategories[]) {
		
		setTitle("Suppression categorie");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesCategories);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Categorie"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
