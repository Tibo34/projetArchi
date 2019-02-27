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
		cn=DAOConnection.getDAOConnection();
	}

	@Override
	public List<String> getNamesCatalogue() {
		List<String> names=new ArrayList<>();
		try {
			rs=st.executeQuery("select nomCatalogue from Catalogue");
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
	
	public int getId(String n) {
		try {
			rs=st.executeQuery("select numCatalogue,nomCatalogue from Catalogue");
			if(rs.getRow()==0) {
				return -1;
			}
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				if(name.equals(n)) {
					return id;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
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
		
		return 0;	}

	@Override
	public boolean exist(String name) {		
		try {
			pst=cn.prepareStatement("select * from Catalogue where nomCatalogue=?");
			pst.setString(1,name);
			rs=pst.executeQuery();
			int nb=rs.getRow();
			if(nb>=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addCatalogue(String n) {
		try {
			cst=cn.prepareCall("call addCatalogue(?)");
			cst.setString(1,n);
			rs=cst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
