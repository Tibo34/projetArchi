package BDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modele.CategorieFactory;
import Modele.I_Catalogue;
import Modele.I_Categorie;
import Modele.I_Produit;
import Modele.Produit;


public class ProduitDAO implements I_ProduitDAO {
	private Connection cn;
	private PreparedStatement pst;
	private CallableStatement cst;
	private ResultSet rs;

	public ProduitDAO() {
		cn=DAOConnection.getDAOConnection();
	}

	@Override
	public I_Produit getProduit(String name,I_Catalogue n) {
        I_Produit p=null;
	    try {
            pst=cn.prepareStatement("select * from Produits natural join Catalogues natural join Categories where nomproduit=? and nomCatalogue=?");
            pst.setString(1,name);
            pst.setString(2,n.getName());
            pst.executeQuery();           
            if (rs.next()) {       
            	I_Categorie c=CategorieFactory.createCategorie(rs.getInt(1),rs.getString(8),rs.getString(9));
                p=new Produit(rs.getString(4), rs.getDouble(5),rs.getInt(6),c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
	}

	public List<I_Produit> getAllProduits(String n) {
		try {
			pst=cn.prepareStatement("select * from Produits natural join Catalogues natural join Categories where nomcatalogue=? order by nomProduit");
			pst.setString(1,n);
			rs=pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<I_Produit> listproduit=new ArrayList<I_Produit>();
		I_Produit p=null;
		try {
			while(rs.next()) {
				I_Categorie c=CategorieFactory.createCategorie(rs.getInt(1),rs.getString(8),rs.getString(9));
                p=new Produit(rs.getString(4), rs.getDouble(5),rs.getInt(6),c);
				listproduit.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
