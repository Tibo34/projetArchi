package Modele;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Utilitaire.Utilitaire;

public class Catalogue implements I_Catalogue {
	
    private ArrayList<I_Produit> produits = new ArrayList<>();

    private static Catalogue instance = null;

    private Catalogue() {}

    public static Catalogue getInstance() {
        if (instance == null)
            instance = new Catalogue();

        return instance;
    }

    @Override
    public boolean addProduit(I_Produit produit) {
    	if(produit==null) {
    		return false;
    	}
    	return addProduit(produit.getNom(),produit.getPrixUnitaireHT(),produit.getQuantite());
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
    		nom=supprimeEspace(nom);
          	I_Produit p=createProduit(nom, prix, qte);
          	if(p==null) {
          		return false;
          	}          	
          	return produits.add(p);
    }
    
    private String supprimeEspace(String nom) {		
		nom=nom.replaceAll("\\s", " ");
		return nom.trim();
	}

	private I_Produit createProduit(String nom, double prix, int qte) {
    	if(!nomExist(nom)&&prix>0&&qte>=0) {
    		return new Produit(nom,prix,qte);
    	}
    	return null;
    }

    @Override
    public int addProduits(List<I_Produit> listProduitAdd) {
    	if(listProduitAdd==null) {
    		return 0;
    	}
    	int i=0;
    	for(I_Produit p :listProduitAdd) {
    		if(addProduit(p)) {
    			i++;
    		}    		
    	}
       return i;
    }

	private boolean nomExist(String nom) {
    	String[]noms=getNomProduits();
    	for(String name :noms) {
    		if(name.equals(nom)) {
    			return true;
    		}
    	}
    	return false;
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
        Arrays.sort(noms);
        return  noms;
    }

    @Override
    public double getMontantTotalTTC() {
        double total = 0;
        for(I_Produit produit : produits) {
            total += produit.getPrixStockTTC();
        }
        return Utilitaire.roundDouble(total);
    }

    @Override
    public void clear() {
        this.produits.clear();
    }
    
    @Override
    public String toString() {
    	String str="";
		for(I_Produit p : produits) {
			str+=p.toString()+"\n";
		}
		str+="\n";
		str+="Montant total TTC du stock : "+Utilitaire.formatDouble(getMontantTotalTTC())+" �";		
		return str;
    }
}
