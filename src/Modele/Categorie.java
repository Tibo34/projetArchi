package Modele;

import BDD.I_CatalogueDAO;
import BDD.I_CategorieDAO;

public class Categorie implements I_Categorie {
	
	private double tauxTVA;
	private int num;
	private String nom;
	private I_CategorieDAO dao;
	
	public Categorie(int n, String name,double t) {
		num=n;
		nom=name;
		tauxTVA=t;
	}
	
	public Categorie(String name,double t) {		
		nom=name;
		tauxTVA=t;
	}

	
	public void setTauxTVA(double t) {
		this.tauxTVA = t;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public double getTauxTVA() {
		return tauxTVA;
	}

	@Override
	public void setNum(int n) {
		num=n;
	}

	@Override
	public I_CategorieDAO getDao() {
		return dao;
	}

	@Override
	public void setDao(I_CategorieDAO dao) {
		this.dao = dao;		
	}

	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		return num;
	}

}
