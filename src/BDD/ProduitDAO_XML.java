package BDD;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import Modele.I_Produit;
import Modele.Produit;


public class ProduitDAO_XML {
	private static String uri = "C:/Produits.xml";
	private Document doc;

	public ProduitDAO_XML() {
		this(uri);
	}

	public ProduitDAO_XML(String absolutePath) {
		uri=absolutePath;
		SAXBuilder sdoc = new SAXBuilder();
		try {			
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
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
			Element root = doc.getRootElement();
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

	public I_Produit lire(String nom,String cat) {
		Element e = chercheProduit(nom,cat);
		if (e != null)
			return new Produit(e.getAttributeValue("nom"), Double.parseDouble(e.getChildText("prixHT")), Integer.parseInt(e.getChildText("quantite")));
		else
			return null;
	}

	public List<I_Produit> lireTous(String name) {

		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			Element catalogue = getCatalogue(name);
			List<Element> listProduits=catalogue.getChildren("produit");
			for (Element prod : listProduits) {				
				String nomP = prod.getAttributeValue("nom");
				Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
				int qte = Integer.parseInt(prod.getChild("quantite").getText());
				l.add(new Produit(nomP, prix, qte));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
			e.printStackTrace();
		}
		return l;
	}

	private Element getCatalogue(String name) {
		List<Element> catalogues=doc.getRootElement().getChildren("catalogue");
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
			out.output(doc, new PrintWriter(uri));
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
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
