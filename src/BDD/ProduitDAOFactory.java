package BDD;

import java.io.File;

import javax.swing.JFileChooser;

public class ProduitDAOFactory {
	
    

    private ProduitDAOFactory() {}

    public static I_ProduitDAO getDAOXML() {
      JFileChooser choose=new JFileChooser("D:\\Java-Projet\\Archi");
		choose.showOpenDialog(null);
			File filexml=choose.getSelectedFile();
                   
        return new ProduitDAO_XML_Adapter(filexml.getAbsolutePath());
    }
    
    public static I_ProduitDAO getDAOOracle() {    	
    	 return new ProduitDAO(); 	
    
    }

	public static I_ProduitDAO getDAOXML(String absolutePath) {
		 return new ProduitDAO_XML_Adapter(absolutePath);    	
	}
}
