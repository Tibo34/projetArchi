package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Utilitaire.Utilitaire;

public class DAOConnection {	
	
	private static Connection cn=null;
	
	private DAOConnection() {
		Properties dbProperties = Utilitaire.loadProperties("bdd.propertie");
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = dbProperties.getProperty("url");
		String login = dbProperties.getProperty("username");
		String mdp = dbProperties.getProperty("password");
		try {
			Class.forName(driver);
			cn=DriverManager.getConnection(url, login, mdp);			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getDAOConnection() {
		if(cn==null) {
			new DAOConnection();
		}
		return cn;
	}

}
