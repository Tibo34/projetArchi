package Modele;

import BDD.I_CategorieDAO;
import BDD.I_ProduitDAO;
import Utilitaire.Utilitaire;

public class Produit implements I_Produit  {
	
	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;	
	private I_Categorie categorie;
	
	public Produit(String name,double prixUHT,int qte,I_Categorie c) {		
		this.nom=name;		
		this.prixUnitaireHT=prixUHT;
		this.quantiteStock=qte;		
		categorie=c;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Produit) {
			Produit p=(Produit) obj;
			if(nom.equals(p.getNom())) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean ajouter(int qteAchetee) {		
		if(qteAchetee<0) {
			return false;
		}
		else {
			quantiteStock+=qteAchetee;
			return true;
		}
	}

	@Override
	public boolean enlever(int qteVendue) {		
		if(qteVendue>quantiteStock) {
			return false;
		}
		else {
			quantiteStock-=qteVendue;
			return true;
		}
		
	}


	public int getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return prixUnitaireHT*(1+categorie.getTauxTVA());
	}
	
	

	@Override
	public double getPrixStockTTC() {
		return getPrixUnitaireTTC()*quantiteStock;
	}
	
	@Override
	public String toString() {
		return nom+" - prix HT : "+Utilitaire.formatDouble(prixUnitaireHT)+" € - prix TTC : "+Utilitaire.formatDouble(getPrixUnitaireTTC())+" € - quantité en stock : "+quantiteStock;
	}
	
	public Produit CreerProduit(String name,double prixUHT,int qte,I_Categorie c) {
		Produit p=new Produit(name,prixUHT,qte,c);
		return p;
	}
	
	@Override
	public void setCategorie(I_Categorie c) {
		categorie=c;
		
	}
	@Override
	public I_Categorie getCategorie() {
		return categorie;
		
	}

}