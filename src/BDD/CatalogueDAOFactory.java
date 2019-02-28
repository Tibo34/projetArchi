package BDD;

import java.io.File;

import javax.swing.JFileChooser;

public class CatalogueDAOFactory {
	
	private static I_CatalogueDAO daoInstance = null;

    private CatalogueDAOFactory() {}

    public static I_CatalogueDAO getDAOOracle() {
          return new CatalogueDAO();
    }
    
    public static I_CatalogueDAO getDAOXML() {
    	JFileChooser choose=new JFileChooser("D:\\Java-Projet\\Archi");
    	choose.showOpenDialog(null);
    	File filexml=choose.getSelectedFile();
    	return new CatalogueDAOXML(filexml.getAbsolutePath());
    }
    
    public static I_CatalogueDAO getDAOXML(String uri) {
    	return new CatalogueDAOXML(uri);    	
    }

}
