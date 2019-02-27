package Modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BDD.CatalogueDAOFactory;
import BDD.I_CatalogueDAO;
import BDD.I_ProduitDAO;
import BDD.ProduitDAOFactory;
import Utilitaire.Utilitaire;

public class Catalogue implements I_Catalogue {
	
    private ArrayList<I_Produit> produits = new ArrayList<>();

    private static Catalogue instance = null;
    private static I_ProduitDAO produitDAO=ProduitDAOFactory.getDAOOracle();
    private static I_CatalogueDAO daoCatalogue=CatalogueDAOFactory.getDAO();
    private String nom;
    private int id;

    public Catalogue(String name) {
    	nom=name;
    	instance=this;
    	id=daoCatalogue.getId(name);
    }
   
    
    private Catalogue(List<I_Produit> p) {
    	produits.addAll(p);
    }

    public static I_ProduitDAO getBdd() {
		return produitDAO;
	}

	public static void setBdd(I_ProduitDAO bdd) {
		Catalogue.produitDAO = bdd;
	}

	public static Catalogue getInstance() {
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
          	if(p!=null&&!produits.contains(p)) {
          		produitDAO.addNouveauProduit(p,this);
          		return produits.add(p);
          	}
          	else {
          		return false;
          	}
          	
          	 
    }
    
    private String supprimeEspace(String nom) {		
		nom=nom.replaceAll("\\s", " ");
		return nom.trim();
	}

	private I_Produit createProduit(String nom, double prix, int qte) {
    	if(!nomExist(nom)&&prix>0&&qte>=0) {
    		return new Produit(nom,prix,qte,produitDAO);
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
    	I_Produit p=findProduit(nom);
    	if(p==null) {
    		return false;
    	}
    	else {
    		produitDAO.delProduit(p,this);
    	}
         return  produits.remove(p);     
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
            boolean r=produit.ajouter(qteAchetee);
            r=produitDAO.achatProduit(produit,this);
            return r;      
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {       
            I_Produit produit = findProduit(nomProduit);
            if (produit==null||qteVendue <= 0||produit.getQuantite() < qteVendue) {
            	return false;
            }
            boolean r=produit.enlever(qteVendue);
            r=produitDAO.venteProduit(produit, this);
           return  r;      
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

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		str+="Montant total TTC du stock : "+Utilitaire.formatDouble(getMontantTotalTTC())+" €";		
		return str;
    }

	@Override
	public String getName() {
		return nom;
	}

	@Override
	public int getNbProduits() {
		return produits.size();
	}

	@Override
	public int getNumCatalogue() {
		return id;
	}

	
}
