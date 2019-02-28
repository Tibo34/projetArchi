package BDD;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class CatalogueDAOXML implements I_CatalogueDAO {
	
	private static String uri = "Catalogues.xml";
	private Document doc;
	
	
	public CatalogueDAOXML() {
		doc=DAOXMLConnection.getDocumentXML();
		uri=DAOXMLConnection.getUri();
	}

	

	@Override
	public List<String> getNamesCatalogue() {
		List<String> names=new ArrayList<>();
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("catalogue");
		for (Element e : lProd) {
			names.add(e.getAttributeValue("name"));
		}
		return names;
	}

	@Override
	public List<String> getIdCatalogue() {
		List<String> ids=new ArrayList<>();
		List<Element> root=doc.getRootElement().getChildren("catalogue");
		for (Element element : root) {
			ids.add(element.getAttributeValue("numero"));
		}
		return ids;
	}

	@Override
	public int getNombreProduitCatalogue(String name) {
		Element catalogue=getCatalogue(name);
		List<Element> produits=catalogue.getChildren("produit");
		return produits.size();
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

	@Override
	public int getId(String name) {
		Element catalogue=getCatalogue(name);
		return Integer.parseInt(catalogue.getAttributeValue("numero"));
	}

	@Override
	public boolean exist(String name) {
		Element catalogue=getCatalogue(name);
		return catalogue!=null;
	}

	@Override
	public boolean addCatalogue(String str) {
		Element root=doc.getRootElement();
		Element catalogue=new Element("catalogue");
		Integer num=getNamesCatalogue().size()+1;
		catalogue.setAttribute("numero",num.toString());
		catalogue.setAttribute("nom",str);
		root.addContent(catalogue);
		return DAOXMLConnection.sauvegardeCatalogue();
	}
	
	
	public String getUri() {
		return uri;
	}



	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public boolean deleteCatalogue(String name) {
		Element root=doc.getRootElement();
		Element catalogue=getCatalogue(name);
		return root.removeContent(catalogue);		
	}

}
