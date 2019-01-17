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

import Modele.I_Produit;
import Modele.Produit;


public class ProduitDAO implements I_ProduitDAO {

	private Connection cn;
	private String url;
	private String login;
	private String mdp;
	private String driver;
	private Statement st;
	private PreparedStatement pst;
	private CallableStatement cst;
	private ResultSet rs;

	public ProduitDAO() {
		driver="oracle.jdbc.driver.OracleDriver";
		url="jbdc:oracle:thin:@162.38.222.149:1521:iut";
		login="molinat";
		mdp="123";
		try {
			Class.forName(driver);
			cn=DriverManager.getConnection(url, login, mdp);
			st=cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<I_Produit>getAllProduits() {
		try {
			rs=st.executeQuery("select * from Produits order by nomProduit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<I_Produit> listproduit=new ArrayList<I_Produit>();
		try {
			while(rs.next()) {
				I_Produit p=new Produit(rs.getString(2), rs.getDouble(3),rs.getInt(4));
				listproduit.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		try {
			pst=cn.prepareStatement("update Produits set quantite=? where nomProduit=?");
			pst.setString(2,p.getNom());
			pst.setInt(1,p.getQuantite());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean venteProduit(I_Produit p) {
		try {
			pst=cn.prepareStatement("update Produits set quantite=? where nomProduit=?");
			pst.setString(2,p.getNom());
			pst.setInt(1,p.getQuantite());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
