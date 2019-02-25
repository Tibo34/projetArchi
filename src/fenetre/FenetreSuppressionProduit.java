package fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controlleur.ControllerCreerSupprimer;
import Modele.I_Catalogue;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private I_Catalogue catalogue;
	private String[] lesProduits;
	
	public FenetreSuppressionProduit(JFrame fenetrePrincipale, I_Catalogue c) {
		catalogue=c;
		setTitle("Suppression produit");
		lesProduits=c.getNomProduits();
		setBounds(500, 500, 250, 150);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		boolean r=ControllerCreerSupprimer.supprimerProduit(combo.getSelectedItem().toString(), catalogue);
		if(r) {
			JOptionPane.showMessageDialog(this, "produit supprimé"+combo.getSelectedItem(), "Suppression", JOptionPane.NO_OPTION);
		}
		this.dispose();
	}

}
