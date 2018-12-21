package Controlleur;

import javax.swing.JOptionPane;

import Modele.I_Catalogue;
import fenetre.FenetreAchat;
import fenetre.FenetrePrincipale;
import fenetre.FenetreVente;

public class ControllerAchatVente {
	
		
	public static void AfficheAchat(FenetrePrincipale fenetre, I_Catalogue c) {
		FenetreAchat fenetraAchat=new FenetreAchat(fenetre,c);		
	}
	
	public static void AfficheVente(FenetrePrincipale fenetre, I_Catalogue c) {		
		FenetreVente f=new FenetreVente(c);	
	}
	
	public static boolean AchatProduit(String nom,int qte,I_Catalogue catalogue) {
		return catalogue.acheterStock(nom,qte);		
	}
	
	public static boolean VenteProduit(String nom,int qte,I_Catalogue catalogue) {
		return catalogue.vendreStock(nom,qte);		
	}
}
