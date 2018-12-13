package Modele;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Catalogue implements I_Catalogue {
    private ArrayList<I_Produit> produits = new ArrayList<>();

    public Catalogue() {

    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if(this.produits.indexOf(produit) == -1) {
            this.produits.add(produit);
            return true;
        }
        return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        if (prix <= 0 || qte <= 0) {
            return false;
        }

        I_Produit produit = new Produits(nom, prix, qte);
        return addProduit(produit);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int i = 0;
        for (I_Produit produit : l) {
            if(this.addProduit(produit)) {
                i++;
            }
        }
        return i;
    }

    @Override
    public boolean removeProduit(String nom) {
        try {
            I_Produit produit = findProduit(nom);
            produits.remove(produit);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private I_Produit findProduit(String nom) {
        for (I_Produit produit : produits) {
            if (produit.getNom().equals(nom)) {
                return produit;
            }
        }

        throw new NoSuchElementException("Produit introuvable");
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        try {
            I_Produit produit = findProduit(nomProduit);
            if (qteAchetee <= 0) {
                return false;
            }

            produit.ajouter(qteAchetee);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        try {
            I_Produit produit = findProduit(nomProduit);
            if (qteVendue <= 0) {
                return false;
            }

            if (produit.getQuantite() < qteVendue) {
                throw new IllegalArgumentException("La quantitÃ© vendue ne peut Ãªtre supÃ©rieure Ã  la quantitÃ© stockÃ©e !");
            }

            produit.enlever(qteVendue);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public String[] getNomProduits() {
        ArrayList<String> noms = new ArrayList<>();
        for (I_Produit produit : this.produits) {
            noms.add(produit.getNom());
        }
        return (String[]) noms.toArray();
    }

    @Override
    public double getMontantTotalTTC() {
        double total = 0;
        for(I_Produit produit : produits) {
            total += produit.getPrixStockTTC();
        }
        return total;
    }

    @Override
    public void clear() {
        this.produits = new ArrayList<>();
    }
    
    @Override
    public String toString() {
    	String str="Catalogue : ";
    	for(I_Produit p : produits ) {
    		str+=p.toString();
    	}
    	return str;
    }
    
    public String formatDouble(double a) {
    	DecimalFormat format=new DecimalFormat("#0.##");    	
    	String s=format.format(a); 	
    	return s;
	}
    
   
	

	@Override
    public String getTextCatalogue() {
		String str="";
		for(I_Produit p : produits) {
			str+=p.getNom()+" prixHT: "+p.getPrixUnitaireHT()+"€ prixTTC: "+formatDouble(p.getPrixUnitaireTTC())+
					"€ quantité en stock: "+formatDouble(p.getQuantite())+"\n";
		}
		str+="\n\n\n";
		str+="Montant total TTC en stock: "+formatDouble(getMontantTotalTTC())+"€";		
		return str;
	}
}
