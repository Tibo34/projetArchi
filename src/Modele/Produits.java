package Modele;

public class Produits implements I_Produit  {
	
	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private double tauxTVA=0.2;
	
	public Produits(String name,double prixUHT,int qte) {
		this.nom=name;
		this.prixUnitaireHT=prixUHT;
		this.quantiteStock=qte;		
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		int init=quantiteStock;
		quantiteStock+=qteAchetee;
		return init<quantiteStock;
	}

	public int getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean enlever(int qteVendue) {
		int init=quantiteStock;
		quantiteStock-=qteVendue;
		return init>quantiteStock;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return prixUnitaireHT*(1+tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return getPrixUnitaireTTC()*quantiteStock;
	}

}
