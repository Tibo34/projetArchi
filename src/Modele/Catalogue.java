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
        if(!produits.contains(produit)) {
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
    public int addProduits(List<I_Produit> listProduitAdd) {
        int i = 0;
        for (I_Produit produit : listProduitAdd) {
            if(this.addProduit(produit)) {
                i++;
            }
        }
        return i;
    }

    @Override
    public boolean removeProduit(String nom) {             
         return  produits.remove( findProduit(nom));     
    }

    private I_Produit findProduit(String nom) {
        for (I_Produit produit : produits) {
            if (produit.getNom().equals(nom)) {
                return produit;
            }
        }
       return null;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {       
            I_Produit produit = findProduit(nomProduit);
            if (produit==null||qteAchetee <= 0) {
                return false;
            }           
            return  produit.ajouter(qteAchetee);
      
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {       
            I_Produit produit = findProduit(nomProduit);
            if (produit==null||qteVendue <= 0||produit.getQuantite() < qteVendue) {
                return false;
            }     
           return  produit.enlever(qteVendue);      
    }

    @Override
    public String[] getNomProduits() {
        String[]noms = new String[produits.size()];
        int i=0;
        for (I_Produit produit : produits) {
            noms[i++]=produit.getNom();
        }
        return  noms;
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
        this.produits.clear();
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
