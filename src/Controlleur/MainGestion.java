package Controlleur;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import Modele.Catalogue;
import fenetre.FenetrePrincipale;

import javax.swing.*;

public class MainGestion {
	
	public static void main(String[]args) {		 
		 Catalogue catalogue=generateCatalogue();
		 FenetrePrincipale fmain=new FenetrePrincipale(catalogue);		
	}
	
	public static Catalogue generateCatalogue() {		
		Properties p=LoadFileProperties();
		Catalogue catalogue=new Catalogue();
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
