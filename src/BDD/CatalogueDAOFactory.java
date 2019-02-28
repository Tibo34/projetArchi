package BDD;

import java.io.File;

import javax.swing.JFileChooser;

public class CatalogueDAOFactory {
	
	

    private CatalogueDAOFactory() {}

    public static I_CatalogueDAO getDAOOracle() {
          return new CatalogueDAO();
    }    
   
    
    public static I_CatalogueDAO getDAOXML() {
    	return new CatalogueDAOXML();    	
    }

}
