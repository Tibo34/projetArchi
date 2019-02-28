package BDD;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class DAOXMLConnection {
	private static String uriCatalogue=null;
	private static String uriCategorie=null;
	private static Document docCatalogue=null;
	private static Document docCategorie=null;
	
	private DAOXMLConnection() {
		
	}
	
	public static Document getDocumentXML() {
		if(docCatalogue==null) {
			File xmlCatalogue=getFileXML();
			File xmlCategorie=getFileXML();
			uriCatalogue=xmlCatalogue.getAbsolutePath();
			uriCategorie=xmlCategorie.getAbsolutePath();
			SAXBuilder sdoc = new SAXBuilder();
			try {			
				docCatalogue = sdoc.build(uriCatalogue);
				docCategorie=sdoc.build(uriCategorie);
			} catch (Exception e) {
				System.err.println("erreur construction arbre JDOM");
			}
			CategorieDAOFactory.getDAOXML();
			return docCatalogue;
		}
		return docCatalogue;
	}
	
	public static boolean sauvegardeCatalogue() {
		return sauvegarde(docCatalogue,uriCatalogue);
	}

	private static boolean sauvegarde(Document d,String u) {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(d, new PrintWriter(u));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}
	
	public static boolean sauvegardeCategorie() {
		return sauvegarde(docCategorie, uriCategorie);		
	}
	
	private static File getFileXML() {
		String path=System.getProperty("user.dir");
		JFileChooser choose=new JFileChooser(path);
		choose.showOpenDialog(null);
		File filexml=choose.getSelectedFile();
		return filexml;
	}

	public static Document getDoc() {
		return docCatalogue;
	}

	

	public static String getUri() {
		return uriCatalogue;
	}

	public static void setUri(String uri) {
		DAOXMLConnection.uriCatalogue = uri;
	}

	public static String getUriCategorie() {
		return uriCategorie;
	}

	public static Document getDocCategorie() {
		return docCategorie;
	}

	public static void setDocCategorie(Document docCategorie) {
		DAOXMLConnection.docCategorie = docCategorie;
	}

}
