package BDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Utilitaire.Utilitaire;

public class CatalogueDAO implements I_CatalogueDAO {
	
	private Connection cn;
	private Statement st;
	private PreparedStatement pst;
	private CallableStatement cst;
	private ResultSet rs;
	
	public CatalogueDAO() {
		Properties dbProperties = Utilitaire.loadProperties("bdd.properties");

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = dbProperties.getProperty("url");
		String login = dbProperties.getProperty("login");
		String mdp = dbProperties.getProperty("mdp");
		try {
			Class.forName(driver);
			cn=DriverManager.getConnection(url, login, mdp);
			st=cn.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getNamesCatalogue() {
		List<String> names=new ArrayList<>();
		try {
			rs=st.executeQuery("select NOMCATALOGUE from Catalogue");
			if(rs.getRow()==0) {
				return null;
			}
			while(rs.next()) {
				names.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return names;
	}

	@Override
	public List<String> getIdCatalogue() {
		List<String> ids=new ArrayList<>();
		try {
			rs=st.executeQuery("select numCatalogue from Catalogue");
			if(rs.getRow()==0) {
				return null;
			}
			while(rs.next()) {
				ids.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ids;
	}

	@Override
	public int getNombreProduitCatalogue(String name) {
		try {
			pst=cn.prepareStatement("select count() from Catalogue natural join Produits where NOMCATALOGUE=?");
			pst.setString(1,name);
			rs=pst.executeQuery();
			int nb=0;
			while (rs.next()){
				nb=rs.getInt(1);				
			}
			return nb;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
