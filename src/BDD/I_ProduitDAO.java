package BDD;

import Modele.Categorie;
import Modele.I_Catalogue;
import Modele.I_Produit;

import java.util.List;

public interface I_ProduitDAO {
    public abstract I_Produit getProduit(String name,I_Catalogue c);

    public abstract List<I_Produit> getAllProduits(String name);

    public abstract boolean addNouveauProduit(I_Produit p,I_Catalogue c);

    public abstract boolean editProduit(I_Produit p,I_Catalogue c);

    public abstract boolean achatProduit(I_Produit p,I_Catalogue c);

    public abstract boolean venteProduit(I_Produit p,I_Catalogue c);

    public abstract boolean delProduit(I_Produit p,I_Catalogue c);
    
    
}
