package BDD;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import Modele.CategorieFactory;
import Modele.I_Categorie;
import Modele.I_Produit;
import Modele.Produit;


public class ProduitDAO_XML {
	


	private static String uriCatalogue = "Catalogues.xml";
	private static String uriCategorie="Categorie.xml";
	private Document docCategorie;
	private Document docCatalogue;
	

	public ProduitDAO_XML() {
		docCatalogue=DAOXMLConnection.getDocumentXML();
		docCategorie=DAOXMLConnection.getDocCategorie();
		uriCatalogue=DAOXMLConnection.getUri();	
		uriCategorie=DAOXMLConnection.getUriCategorie();
	}

	public boolean creer(I_Produit p,String name) {
		try {			
			Element catalogue=getCatalogue( name);
			Element prod = new Element("produit");
			prod.setAttribute("nom", p.getNom());
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
			catalogue.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	public boolean maj(I_Produit p,String name) {
		try {
			Element prod = chercheProduit(p.getNom(),name);
			if (prod != null) {
				prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
				return sauvegarde();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}

	public boolean supprimer(I_Produit p,String cat) {
		try {
			Element root = docCatalogue.getRootElement();
			Element prod = chercheProduit(p.getNom(),cat);
			if (prod != null) {
				root.removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}

	public I_Produit lire(String nom,String catalogue) {
		Element e = chercheProduit(nom,catalogue);
		
		Element cat=chercherCategorie(e.getChild("categorie").getValue());
		int pos=docCategorie.getRootElement().indexOf(cat);
		I_Categorie c=CategorieFactory.createCategorie(pos, cat.getAttributeValue("nom"),cat.getChildText("taux"));
		if (e != null)
			return new Produit(e.getAttributeValue("nom"), Double.parseDouble(e.getChildText("prixHT")), Integer.parseInt(e.getChildText("quantite")),c);
		else
			return null;
	}

	private Element chercherCategorie(String value) {
		List<Element> root=docCategorie.getRootElement().getChildren("Categorie");
		for (Element element : root) {
			if(element.getAttributeValue("nom").equals(value)) {
				return element;
			}
		}
		return null;
	}

	public List<I_Produit> lireTous(String name) {

		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			Element catalogue = getCatalogue(name);
			List<Element> listProduits=catalogue.getChildren("produit");
			for (Element prod : listProduits) {				
				String nomP = prod.getAttributeValue("nom");
				Element cat=chercherCategorie(prod.getChild("categorie").getValue());
				int pos=docCategorie.getRootElement().indexOf(cat);
				I_Categorie c=CategorieFactory.createCategorie(pos, cat.getAttributeValue("nom"),cat.getChildText("taux"));
				Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
				int qte = Integer.parseInt(prod.getChild("quantite").getText());
				l.add(new Produit(nomP, prix, qte,c));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
			e.printStackTrace();
		}
		return l;
	}

	private Element getCatalogue(String name) {
		List<Element> catalogues=docCatalogue.getRootElement().getChildren("catalogue");
		for (Element e : catalogues) {
			if(e.getAttributeValue("name").equals(name)) {
				return e;
			}
		}
		return null;
	}

	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(docCatalogue, new PrintWriter(uriCatalogue));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

	private Element chercheProduit(String nom,String cat) {
		Element catalogue=getCatalogue(cat);
		List<Element> lProd = catalogue.getChildren("produit");
		for(Element e:  lProd) {
			if(e.getAttributeValue("nom").equals(nom)) {
				return e;
			}
		}
		return null;
	}

	public String getUri() {
		return uriCatalogue;
	}

	public void setUri(String uri) {
		this.uriCatalogue = uri;
	}
}
