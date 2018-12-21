package Controlleur;
import java.util.Map.Entry;
import java.util.Properties;
import Modele.Catalogue;
import Utilitaire.Utilitaire;
import fenetre.FenetrePrincipale;

public class MainGestion {
	
	public static void main(String[]args) {		 
		 Catalogue catalogue=generateCatalogue();
		 FenetrePrincipale fmain=new FenetrePrincipale(catalogue);		
	}
	
	public static Catalogue generateCatalogue() {		
		Properties p= Utilitaire.LoadFileProperties();
		Catalogue catalogue=new Catalogue();
		for(Entry<Object, Object> entre : p.entrySet()) {
			double prix=getPrix((String) entre.getValue());
			int qte=getQte((String) entre.getValue());
			catalogue.addProduit((String) entre.getKey(),prix,qte);
		}		
		return catalogue;
	}

	private static int getQte(String value) {
		return Integer.parseInt(value.split(" ")[0].split(":")[1]);
	}

	private static double getPrix(String value) {		
		return Double.parseDouble(value.split(" ")[1].split(":")[1]);
	}
}
