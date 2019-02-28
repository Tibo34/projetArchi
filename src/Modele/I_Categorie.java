package Modele;

import BDD.I_CategorieDAO;

public interface I_Categorie {

	public abstract String getNom();
	public abstract double getTauxTVA();
	public abstract int getNum();
	public abstract void setNum(int n);
	public abstract I_CategorieDAO getDao();
	public abstract void setDao(I_CategorieDAO dao);
	
	
}
