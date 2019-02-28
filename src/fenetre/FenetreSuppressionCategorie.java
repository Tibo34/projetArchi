package fenetre;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import BDD.I_CategorieDAO;
import Modele.I_Categorie;

public class FenetreSuppressionCategorie extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private I_CategorieDAO dao;
	
	public FenetreSuppressionCategorie(List<String> names, I_CategorieDAO dao) {
		this.dao=dao;
		setTitle("Suppression categorie");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>();
		for (String string : names) {
			combo.addItem(string);
		}
		
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Categorie"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int n=combo.getSelectedIndex();				
		String nom=combo.getItemAt(n);
		I_Categorie cat=dao.getCategorie(nom);
		dao.deleteCategorie(cat);
		this.dispose();
	}

}
