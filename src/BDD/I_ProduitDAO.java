package BDD;

import Modele.I_Catalogue;
import Modele.I_Produit;

import java.util.List;

public interface I_ProduitDAO {
    I_Produit getProduit(String name);

    List<I_Produit> getAllProduits(String name);

    boolean addNouveauProduit(I_Produit p,I_Catalogue c);

    boolean editProduit(I_Produit p,I_Catalogue c);

    boolean achatProduit(I_Produit p,I_Catalogue c);

    boolean venteProduit(I_Produit p,I_Catalogue c);

    boolean delProduit(I_Produit p,I_Catalogue c);
}
