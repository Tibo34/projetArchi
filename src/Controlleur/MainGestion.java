package Controlleur;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import Modele.Catalogue;
import Modele.I_Catalogue;
import fenetre.FenetrePrincipale;

import javax.swing.*;

import BDD.ProduitDAO;

public class MainGestion {
	
	public static void main(String[]args) {		 
		 I_Catalogue catalogue = generateCatalogueBD();
		 FenetrePrincipale fmain=new FenetrePrincipale(catalogue);		
	}
	
	public static I_Catalogue generateCatalogueBD() {
		I_Catalogue catalogue = Catalogue.getInstance();
		ProduitDAO dao=new ProduitDAO();
		catalogue.addProduits(dao.getAllProduits());
		return catalogue;
	}

	public static I_Catalogue generateCatalogue() {
		Properties p = LoadFileProperties();
		I_Catalogue catalogue = Catalogue.getInstance();
		for(Entry<Object, Object> entre : p.entrySet()) {
			double prix=getPrix((String) entre.getValue());
			int qte=getQte((String) entre.getValue());
			catalogue.addProduit((String) entre.getKey(),prix,qte);
		}		
		return catalogue;
	}

	public static Properties LoadFileProperties() {
		String file="properties.propertie";
		Properties p=new Properties();
		 try(InputStream in=new FileInputStream(file)){
			 p.load(in);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 return p;
	}

	private static int getQte(String value) {
		return Integer.parseInt(value.split(" ")[0].split(":")[1]);
	}

	private static double getPrix(String value) {		
		return Double.parseDouble(value.split(" ")[1].split(":")[1]);
	}
}
