package fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import BDD.I_CategorieDAO;

public class FenetreNouvelleCategorie extends JFrame implements ActionListener {

	private JTextField txtTaux;
	private JTextField txtNom;
	private JButton btValider;
	private I_CategorieDAO dao;
	
	public FenetreNouvelleCategorie(I_CategorieDAO dao) {

		setTitle("Creation Categorie");
		setBounds(500, 500, 200, 210);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		this.dao=dao;	
		JLabel labNom = new JLabel("Nom categorie");
		JLabel labTaux = new JLabel("Taux TVA");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labTaux);
		txtTaux = new JTextField(15);
		contentPane.add(txtTaux);

		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	

	public void actionPerformed(ActionEvent e) {
		String nom,taux;
		double t;
		nom=txtNom.getText();
		taux=txtTaux.getText();
		if(nom.isEmpty()||taux.isEmpty()) {
			return;
		}
		try {
			t=Double.parseDouble(taux);
		}
		catch (NumberFormatException ex) {
			return;
		}
		dao.addCategorie(nom,t);
		this.dispose();
	}

}