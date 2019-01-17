package BDD;

import java.sql.DriverManager;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean achatProduit(I_Produit p, int qte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean venteProduit(I_Produit p, int qte) {
		// TODO Auto-generated method stub
		return false;
	}

}
