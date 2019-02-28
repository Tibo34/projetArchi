package BDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modele.Categorie;
import Modele.I_Categorie;

public class CategorieDAO implements I_CategorieDAO {
	
	private Connection cn;
	private PreparedStatement pst;
	private CallableStatement cst;
	private ResultSet rs;
	
	
	public CategorieDAO() {
		cn=DAOConnection.getDAOConnection();
	}

	@Override
	public boolean addCategorie(String n,double t) {
		try {
			cst=cn.prepareCall("call addCategorie(?,?)");
			cst.setString(1,n);
			cst.setDouble(2,t);
			rs=cst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCategorie(I_Categorie c) {
		try {
			cst=cn.prepareCall("call deleteCategorie(?)");
			cst.setString(1,c.getNom());
			rs=cst.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;

	}

	@Override
	public I_Categorie getCategorie(String cat) {
		try {
			pst=cn.prepareStatement("select * from Categories where nomCategorie=?");
			pst.setString(1,cat);
			rs=pst.executeQuery();
			Categorie c=null;
			if(rs.getRow()>0) {
				return null;
			}
			while(rs.next()) {
				int id=rs.getInt(1);
				String nom=rs.getString(2);
				double taux=rs.getDouble(3);
				c=new Categorie(id, nom, taux);				
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int getNbNextCategorie() {
		int nb=0;	
		try {
			pst=cn.prepareStatement("select count() from Catgories");
			rs=pst.executeQuery();
			nb=rs.getInt(1)+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nb;
	}

	@Override
	public List<String> getNamesCategorie() {
		List<String>names=new ArrayList<>();
		try {
			pst=cn.prepareStatement("select nomCategorie from Categories");
			rs=pst.executeQuery();
			while(rs.next()) {
				names.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		System.out.println(names);
		return names;
	}

}
