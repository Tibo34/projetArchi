package Controlleur;

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
	
	public static void AchatProduit(String nom,int qte,I_Catalogue catalogue) {
		boolean r=catalogue.acheterStock(nom,qte);		
	}
	
	public static void VenteProduit(String nom,int qte,I_Catalogue catalogue) {
		boolean r=catalogue.vendreStock(nom,qte);		
	}
}
