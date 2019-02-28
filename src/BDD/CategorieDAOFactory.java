package BDD;

public class CategorieDAOFactory {
	
	public static I_CategorieDAO dao=new CategorieDAO();
	
	public static I_CategorieDAO getDAOOracle() {
		 dao=new CategorieDAO();
		 return dao;
	}
	
	public static I_CategorieDAO getDAOXML() {
		dao= new CategorieDAOXML();
		return dao;
	}
	
	public static I_CategorieDAO getDAOCategorie() {
		return dao;
	}

}
