package fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controlleur.ControllerCreerSupprimer;
import Exception.ConvertDoubleExcpetion;
import Exception.ConvertIntegerException;
import Exception.CreationProduitException;
import Exception.StringException;
import Modele.I_Catalogue;
import Utilitaire.Utilitaire;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	private I_Catalogue catalogue;
	
//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(I_Catalogue c) {	
		catalogue=c;
		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantité en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {		
			boolean r=false;
			String nom="";
			String ajouteEchoue = "le produit"+nom+" n'a pas été ajouté ";
			String ajoutReussi="produit ajouté : "+nom;
			try {
				nom=Utilitaire.extractString(txtNom.getText());
				ajouteEchoue="le produit"+nom+" n'a pas été ajouté ";
				ajoutReussi="produit ajouté : "+nom;
				r = ControllerCreerSupprimer.creerProduit(nom,Utilitaire.convertDouble(txtPrixHT.getText()),Utilitaire.convertInteger(txtQte.getText()),catalogue);
				if(!r) {
					throw new CreationProduitException();
				}
			} catch (ConvertDoubleExcpetion e1) {
				PopupMess.display(this, ajouteEchoue);
				e1.printStackTrace();
				return;
			} catch (ConvertIntegerException e1) {
				PopupMess.display(this, ajouteEchoue);
				e1.printStackTrace();
				return;
			} catch (StringException e1) {
				PopupMess.display(this,ajouteEchoue);
				e1.printStackTrace();
				return;
			}
			catch(CreationProduitException e1) {
				PopupMess.display(this,ajouteEchoue);
				e1.printStackTrace();
				return;
			}
			PopupMess.display(this, ajoutReussi);
			
		
		this.dispose();
	}

	

}