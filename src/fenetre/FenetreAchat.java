package fenetre;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.xml.internal.ws.api.server.ContainerResolver;

import Controlleur.ControllerAchatVente;
import Modele.I_Catalogue;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private I_Catalogue catalogue;
	private String[] lesProduits;

	public FenetreAchat(I_Catalogue c) {
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
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {		
		ControllerAchatVente.AchatProduit(combo.getSelectedItem().toString(), Integer.parseInt(txtQuantite.getText()),catalogue);
		this.dispose();
	}

	

}
