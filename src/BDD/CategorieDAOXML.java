package BDD;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import Modele.Categorie;
import Modele.CategorieFactory;
import Modele.I_Categorie;

public class CategorieDAOXML implements I_CategorieDAO {
	
	private static String uriCatalogue = "Catalogues.xml";
	private static String uriCategorie="Categorie.xml";
	private Document docCategorie;
	
	
	
	public CategorieDAOXML() {
		docCategorie=DAOXMLConnection.getDocCategorie();
		uriCategorie=DAOXMLConnection.getUriCategorie();
	}

	@Override
	public boolean addCategorie(String name, double t) {
		Element root=docCategorie.getRootElement();
		Element cat=new Element("Categorie");
		cat.setAttribute("nom",name);
		Element taux=new Element("taux");		
		taux.addContent(String.valueOf(t));
		cat.addContent(taux);
		root.addContent(cat);
		return DAOXMLConnection.sauvegardeCategorie();
	}

	@Override
	public boolean deleteCategorie(I_Categorie c) {
		String name=c.getNom();
		Element root=docCategorie.getRootElement();
		Element cat=getCategorie(root,name);
		return root.removeContent(cat);		
	}

	private Element getCategorie(Element root, String name) {
		List<Element> list=root.getChildren("Categorie");
		for (Element e : list) {
			if(e.getAttributeValue("nom").equals(name)) {
				return e;
			}
		}
		return null;
	}

	@Override
	public I_Categorie getCategorie(String cat) {
		Element e=getCategorie(docCategorie.getRootElement(),cat);
		int pos=docCategorie.getRootElement().indexOf(e);
		return CategorieFactory.createCategorie(pos,e.getAttributeValue("nom"),e.getChild("taux").getValue());		
	}

	@Override
	public int getNbNextCategorie() {
		List<Element> categorie=docCategorie.getRootElement().getChildren("Categorie");
		return categorie.size()+1;
	}

	@Override
	public List<String> getNamesCategorie() {
		List<Element> categories=docCategorie.getRootElement().getChildren("Categorie");
		List<String>names=new ArrayList<>();
		for (String str : names) {
			names.add(str);
		}
		return names;
	}

}
