package fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controlleur.ControllerAchatVente;
import Controlleur.MainGestion;
import Modele.I_Catalogue;
import Utilitaire.Utilitaire;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private I_Catalogue catalogue;
	private String[] lesProduits;

	public FenetreVente(I_Catalogue c) {
		setTitle("Vente");
		setBounds(500, 500, 250, 175);
		catalogue=c;
		lesProduits=c.getNomProduits();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit? vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String text = txtQuantite.getText();
		if (Utilitaire.checkQte(this, text)) return;

		boolean r=ControllerAchatVente.VenteProduit(combo.getSelectedItem().toString(), Integer.parseInt(text),catalogue);
		if(r) {
			PopupMess.display(this, "produit vendu");
		}
		
		this.dispose();
	}

}
