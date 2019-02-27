package BDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modele.I_Catalogue;
import Modele.I_Produit;
import Modele.Produit;


public class ProduitDAO implements I_ProduitDAO {
	private Connection cn;
	private Statement st;
	private PreparedStatement pst;
	private CallableStatement cst;
	private ResultSet rs;

	public ProduitDAO() {
		cn=DAOConnection.getDAOConnection();
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
			pst=cn.prepareStatement("select * from Produits natural join Catalogues where nomcatalogue=? order by nomProduit");
			pst.setString(1,n);
			rs=pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<I_Produit> listproduit=new ArrayList<I_Produit>();
		try {
			while(rs.next()) {
				I_Produit p=new Produit(rs.getString(3), rs.getDouble(4),rs.getInt(5),this);
				listproduit.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(listproduit);
		return listproduit;
	}

	@Override
	public boolean addNouveauProduit(I_Produit p,I_Catalogue c) {
		try {			
			cst=cn.prepareCall("call addProduit(?,?,?,?)");
			cst.setString(1, p.getNom());
			cst.setDouble(2, p.getPrixUnitaireHT());
			cst.setInt(3, p.getQuantite());
			cst.setInt(4, c.getNumCatalogue());
			cst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean editProduit(I_Produit p,I_Catalogue c) {
		try {
			pst=cn.prepareStatement("update Produits set quantite=? where nomProduit=? and numCatalogue=?");
			pst.setString(2,p.getNom());
			pst.setInt(1,p.getQuantite());
			pst.setInt(3,c.getNumCatalogue());
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delProduit(I_Produit p,I_Catalogue c) {
		try {
			pst=cn.prepareStatement("delete from Produits where nomProduit=? and numCatalogue=?");
			pst.setString(1,p.getNom());
			pst.setInt(2,c.getNumCatalogue());
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean achatProduit(I_Produit p,I_Catalogue c) {
		return this.editProduit(p,c);
	}

	@Override
	public boolean venteProduit(I_Produit p,I_Catalogue c) {
		return this.editProduit(p,c);
	}

	
}
