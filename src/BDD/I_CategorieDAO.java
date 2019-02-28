package BDD;

import java.util.List;

import Modele.I_Categorie;

public interface I_CategorieDAO {
	
	public abstract boolean addCategorie(String name,double t);
	public abstract boolean deleteCategorie(I_Categorie c);
	public abstract I_Categorie getCategorie(String cat);
	public abstract int getNbNextCategorie();
	public abstract List<String> getNamesCategorie();
	

}
