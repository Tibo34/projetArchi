package BDD;

import java.io.File;

import javax.swing.JFileChooser;

public class ProduitDAOFactory {
	
    

    private ProduitDAOFactory() {}
   
    
    public static I_ProduitDAO getDAOOracle() {    	
    	 return new ProduitDAO(); 	
    
    }

	public static I_ProduitDAO getDAOXML() {
		 return new ProduitDAO_XML_Adapter();    	
	}
}
