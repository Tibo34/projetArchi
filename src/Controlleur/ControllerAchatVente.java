package Controlleur;

import javax.swing.JOptionPane;

import Modele.I_Catalogue;
import fenetre.FenetreAchat;
import fenetre.FenetreVente;

public class ControllerAchatVente {
	
		
	public static void AfficheAchat(I_Catalogue c) {
		FenetreAchat f=new FenetreAchat(c);		
	}
	
	public static void AfficheVente(I_Catalogue c) {		
		FenetreVente f=new FenetreVente(c);	
	}
	
	public static boolean AchatProduit(String nom,int qte,I_Catalogue catalogue) {
		return catalogue.acheterStock(nom,qte);		
	}
	
	public static boolean VenteProduit(String nom,int qte,I_Catalogue catalogue) {
		return catalogue.vendreStock(nom,qte);		
	}
}
