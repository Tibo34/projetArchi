package BDD;

import Modele.I_Produit;

import java.util.List;

public interface I_ProduitDAO {
	
    public List<I_Produit> getAllProduits();    
    public boolean addNouveauProduit(I_Produit p);
    public boolean achatProduit(I_Produit p);
    public boolean venteProduit(I_Produit p);
    public boolean delProduit(I_Produit p);
    
    
}
