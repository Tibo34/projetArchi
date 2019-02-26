package Modele;

import BDD.I_ProduitDAO;
import BDD.ProduitDAOFactory;
import Utilitaire.Utilitaire;

public class Produit implements I_Produit  {
	
	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private double tauxTVA=0.2;
	private I_ProduitDAO bdd;
	
	public Produit(String name,double prixUHT,int qte) {		
		this.nom=name;		
		this.prixUnitaireHT=prixUHT;
		this.quantiteStock=qte;		
	}
	public Produit(String n, double p,int q, I_ProduitDAO b) {
		this(n,p,q);
		bdd=b;
	}
	
	
	@Override
	public boolean ajouter(int qteAchetee) {		
		if(qteAchetee<0) {
			return false;
		}
		else {
			quantiteStock+=qteAchetee;
			bdd.achatProduit(this);
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
			bdd.venteProduit(this);
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
		return prixUnitaireHT*(1+tauxTVA);
	}
	
	

	@Override
	public double getPrixStockTTC() {
		return getPrixUnitaireTTC()*quantiteStock;
	}
	
	@Override
	public String toString() {
		return nom+" - prix HT : "+Utilitaire.formatDouble(prixUnitaireHT)+" € - prix TTC : "+Utilitaire.formatDouble(getPrixUnitaireTTC())+" € - quantité en stock : "+quantiteStock;
	}
	
	public Produit CreerProduit(String name,double prixUHT,int qte,I_ProduitDAO bdd) {
		Produit p=new Produit(name,prixUHT,qte,bdd);
		return p;
	}


	public I_ProduitDAO getBdd() {
		return bdd;
	}


	public void setBdd(I_ProduitDAO bdd) {
		this.bdd = bdd;
	}

}