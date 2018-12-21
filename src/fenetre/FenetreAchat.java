package fenetre;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Controlleur.MainGestion;
import com.sun.xml.internal.ws.api.server.ContainerResolver;

import Controlleur.ControllerAchatVente;
import Modele.I_Catalogue;
import Utilitaire.Utilitaire;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private I_Catalogue catalogue;
	private String[] lesProduits;

	public FenetreAchat(FenetrePrincipale fenetre, I_Catalogue c) {
		lesProduits=c.getNomProduits();
		catalogue=c;
		setTitle("Achat");		
		setBounds(500, 500, 250, 175);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");
		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String text = txtQuantite.getText();
		if (Utilitaire.checkQte(this, text)) return;

		boolean r=ControllerAchatVente.AchatProduit(combo.getSelectedItem().toString(), Integer.parseInt(text), catalogue);
		if(r) {
			PopupMess.display(this, "produit achet�");
		}
		this.dispose();
	}
}
