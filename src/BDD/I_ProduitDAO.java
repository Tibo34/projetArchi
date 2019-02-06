package BDD;

import Modele.I_Produit;

import java.util.List;

public interface I_ProduitDAO {
    I_Produit getProduit(String name);

    List<I_Produit> getAllProduits();

    boolean addNouveauProduit(I_Produit p);

    boolean editProduit(I_Produit p);

    boolean achatProduit(I_Produit p);

    boolean venteProduit(I_Produit p);

    boolean delProduit(I_Produit p);
}
