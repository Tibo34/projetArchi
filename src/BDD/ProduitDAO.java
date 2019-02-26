package BDD;

import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Modele.I_Produit;
import Modele.Produit;
import Utilitaire.Utilitaire;


public class ProduitDAO implements I_ProduitDAO {
	private Connection cn;
	private Statement st;
	private PreparedStatement pst;
	private CallableStatement cst;
	private ResultSet rs;

	public ProduitDAO() {
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
	public I_Produit getProduit(String name) {
        I_Produit p=null;
	    try {
            rs=st.executeQuery("select * from Produits where nomproduit=?");
            pst.setString(1,name);
            pst.executeQuery();

            if (rs.next()) {
                p=new Produit(rs.getString(2), rs.getDouble(3),rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
	}

	public List<I_Produit> getAllProduits(String n) {
		try {
			pst=cn.prepareStatement("select * from Produits where NOMCATALOGUE=? order by nomProduit");
			pst.setString(1,n);
			rs=pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<I_Produit> listproduit=new ArrayList<I_Produit>();
		try {
			while(rs.next()) {
				I_Produit p=new Produit(rs.getString(2), rs.getDouble(3),rs.getInt(4));
				listproduit.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listproduit;
	}

	@Override
	public boolean addNouveauProduit(I_Produit p) {
		try {
			cst=cn.prepareCall("call addProduit(?,?,?)");
			cst.setString(1, p.getNom());
			cst.setDouble(2, p.getPrixUnitaireHT());
			cst.setInt(3, p.getQuantite());
			cst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean editProduit(I_Produit p) {
		try {
			pst=cn.prepareStatement("update Produits set quantite=? where nomProduit=?");
			pst.setString(2,p.getNom());
			pst.setInt(1,p.getQuantite());
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delProduit(I_Produit p) {
		try {
			pst=cn.prepareStatement("delete from Produits where nomProduit=?");
			pst.setString(1,p.getNom());
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean achatProduit(I_Produit p) {
		return this.editProduit(p);
	}

	@Override
	public boolean venteProduit(I_Produit p) {
		return this.editProduit(p);
	}

	
}
