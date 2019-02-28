package Modele;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class CatalogueTest {

	I_Catalogue cat;
	
	@Before
	public void setUp() {
		cat = Catalogue.getInstance();
		//Si votre Catalogue est un Singleton, il faut changer la ligne pr�c�dente puis vider le Catalogue avec la m�thode clear() comme indiqu� � la ligne suivante
		cat.clear();
	}
	
	@Test
	public void testConstructeurCatalogue() {
		assertNotNull("cr�er catalogue", cat);
	}

	@Test
	public void testAddProduitIProduit_null() {
		I_Produit p1 = null;
		assertFalse("ajout produit null", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_unProduit() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		assertTrue("ajout un produit", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_deuxProduits() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		assertTrue("ajout deux produits", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois m�me produit cons�cutif", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitNonConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois m�me produit non cons�cutif", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Treets", 15, 2);
		assertFalse("ajout deux produits avec m�me nom cons�cutif", cat.addProduit(p3));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomNonConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Mars", 15, 2);
		assertFalse("ajout deux produits avec m�me nom non consecutif", cat.addProduit(p3));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomEspacesAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit(" Mars", 15, 2);
		assertFalse("ajout deux produits avec m�me nom mais un avec des espaces au d�but", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomTabulationsAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("	Mars", 15, 2);
		assertFalse("ajout deux produits avec m�me nom mais un avec des tabulations au d�but", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomEspacesALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Mars ", 15, 2);
		assertFalse("ajout deux produits avec m�me nom mais un avec des espaces � la fin", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomTabulationsALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Mars	", 15, 2);
		assertFalse("ajout deux produits avec m�me nom mais un avec des tabulations � la fin", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_stockNegatif() {
		I_Produit p1 = createProduit("Raider", 10, -1);
		assertFalse("ajout produit avec un stock n�gatif", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_stockNul() {
		I_Produit p1 = createProduit("Snickers", 1, 0);
		assertTrue("ajout produit avec un stock nul", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_prixNul() {
		I_Produit p1 = createProduit("Lion", 0, 3);
		assertFalse("ajout produit avec un prix nul", cat.addProduit(p1));
	}	
		
	@Test
	public void testAddProduitIProduit_prixNegatif() {
		I_Produit p1 = createProduit("Bounty", -5, 4);
		assertFalse("ajout produit avec un prix n�gatif", cat.addProduit(p1));
	}	

	
	@Test
	public void testAddProduitStringDoubleInt_unProduit() {
		Categorie c = createCategorie();
		assertTrue("ajout un produit", cat.addProduit("Mars", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxProduit() {		
		Categorie c = createCategorie();
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertTrue("ajout deux produits", cat.addProduit("Treets", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		assertFalse("ajout deux fois m�me produit cons�cutif", cat.addProduit("Treets", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomNonConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		assertFalse("ajout deux fois m�me produit non cons�cutif", cat.addProduit("Mars", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomEspacesAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		assertFalse("ajout deux fois m�me produit mais un avec espaces au d�but", cat.addProduit(" Mars", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomTabulationsAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		assertFalse("ajout deux fois m�me produit mais un avec tabulations au d�but", cat.addProduit("	Mars", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomEspacesALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		assertFalse("ajout deux fois m�me produit mais un avec espaces � la fin", cat.addProduit("Mars ", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomTabulationsALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		assertFalse("ajout deux fois m�me produit mais un avec tabulations � la fin", cat.addProduit("Mars	", 10, 1,c));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_stockNegatif() {
		Categorie c = createCategorie();
		assertFalse("ajout produit avec stock n�gatif", cat.addProduit("Raider", 10, -1,c));
	}

	@Test
	public void testAddProduitStringDoubleInt_stockNul() {
		Categorie c = createCategorie();
		assertTrue("ajout produit avec stock nul", cat.addProduit("Snickers", 1, 0,c));
	}

	private Categorie createCategorie() {
		Categorie c=new Categorie(1, "TVANormale", 0.2);
		return c;
	}

	@Test
	public void testAddProduitStringDoubleInt_prixNul() {
		assertFalse("ajout produit avec prix nul", cat.addProduit("Lion", 0, 3,createCategorie()));
	}	
	
	@Test
	public void testAddProduitStringDoubleInt_prixNegatif() {
		assertFalse("ajout produit avec prix n�gatif", cat.addProduit("Bounty", -5, 4,createCategorie()));
	}	

	@Test
	public void testAddProduits_null() {
		List<I_Produit> liste = null;
		assertEquals("ajout liste null", 0, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_vide() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		assertEquals("ajout liste vide", 0, cat.addProduits(liste));
	}	

	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueVide() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec deux produits dans un catalogue vide",2, cat.addProduits(liste));
	}	
	
	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueDejaRempli() {
		I_Produit p0 = createProduit("Twix", 10, 6);
		cat.addProduit(p0);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec deux produits dans un catalogue plein",2, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecUnSeulDoublon() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec un seul des produits d�j� dans le catalogue",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecCertainsDoublons() {
		I_Produit p1 = createProduit("Twix", 10, 4);
		I_Produit p2 = createProduit("Bounty", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec plusieurs produits d�j� dans le catalogue",2, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecQueDesDoublons() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec tous les produits d�j� dans le catalogue",0, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_produitsAvecNomsDoublons() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Treets", 10, 6);
		liste.add(p3);
		liste.add(p4);
		assertEquals("ajout liste produits dont tous les noms dans le catalogue",0, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsEspacesAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit(" Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit espaces au d�but du nom",3, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsTabulationsAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("	Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit tabulations au d�but du nom",3, cat.addProduits(liste));
	}	
	
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsEspacesALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars ", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit espace � la fin du nom",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsTabulationsALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars ", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit tabulation � la fin du nom",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_avecStocksNegatifs() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 15, 2);
		I_Produit p2 = createProduit("Kit Kat", 8, -3);
		I_Produit p3 = createProduit("Lion", 4, 6);
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		assertEquals("ajout liste produit avec stock n�gatif",2, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_avecStocksNull() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 15, 2);
		I_Produit p2 = createProduit("Snickers", 1, 0);
		I_Produit p3 = createProduit("Lion", 4, 6);
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		assertEquals("ajout liste produit avec stock nul",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_avecPrixNul() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Nuts", 0, 1);
		liste.add(p1);
		assertEquals("ajout liste produit avec prix nul",0, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_avecPrixNegatif() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Topset", -8, 3);
		I_Produit p2 = createProduit("Nuts", 4, 6);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste produit avec prix n�gatif",1, cat.addProduits(liste));
	}

	@Test
	public void testRemoveProduit_existe() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertTrue("suppression produit existant", cat.removeProduit("Mars"));
	}	
	
	@Test
	public void testRemoveProduit_existePas() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertFalse("suppression produit qui n'existe pas", cat.removeProduit("Lion"));
	}
		
	@Test
	public void testRemoveProduit_null() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertFalse("suppression avec un nom null", cat.removeProduit(null));
	}	
		
	@Test
	public void testAcheterProduit_existePas() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertFalse("acheter produit qui n'existe pas", cat.acheterStock("Nuts", 3));
	}
	
	@Test
	public void testAcheterProduit_existe() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertTrue("acheter produit qui existe", cat.acheterStock("Raider", 3));
	}
		
	@Test
	public void testAcheterProduit_quantiteNegative() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertFalse("acheter quantit� n�gative", cat.acheterStock("Mars", -4));
	}	
		
	@Test
	public void testAcheterProduit_quantiteNulle() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		assertFalse("acheter quantit� nulle", cat.acheterStock("Treets", 0));
	}	

	@Test
	public void testVendreProduit_existePas() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 0,c);
		cat.addProduit("Raider", 12, 3,c);
		assertFalse("vendre produit qui n'existe pas", cat.vendreStock("Nuts", 3));
	}	
	
	@Test
	public void testVendreProduit_existe() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 0,c);
		cat.addProduit("Raider", 12, 3,c);
		assertTrue("vendre produit qui existe", cat.vendreStock("Raider", 1));
	}

	@Test
	public void testVendreProduit_quantiteNegative() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 0,c);
		cat.addProduit("Raider", 12, 3,c);
		assertFalse("vendre quantit� n�gative", cat.vendreStock("Mars", -4));
	}	
	
	@Test
	public void testVendreProduit_quantiteNulle() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 0,c);
		cat.addProduit("Raider", 12, 3,c);
		assertFalse("vendre quantit� nulle", cat.vendreStock("Treets", 0));
	}
	
	@Test
	public void testVendreProduit_stockNul() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 0,c);
		cat.addProduit("Raider", 12, 3,c);		
		assertFalse("vendre produit sans stock", cat.vendreStock("Treets", 4));
	}
	
	@Test
	public void testVendreProduit_stockInsuffisant() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 0,c);
		cat.addProduit("Raider", 12, 3,c);
		assertFalse("vendre produit stock insuffisant", cat.vendreStock("Raider", 10));
	}
	
	@Test
	public void testGetNomProduits_vide() {
		String[] tab0 = new String[0];
		assertArrayEquals("recup�re nom produits catalogue vide", tab0, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduit() {
		String[] tab = {"Mars"};
		cat.addProduit("Mars", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produits avec un seul produit", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesAuDebut() {
		String[] tab = {"Mars"};
		cat.addProduit(" Mars", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produit avec espace debut ; les espaces au d�but ne doivent pas �tre stock�s", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesALaFin() {
		String[] tab = {"Mars"};
		cat.addProduit("Mars ", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produit avec espace fin ; les espaces � la fin ne doivent pas �tre stock�s", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesAuMilieu() {
		String[] tab = {"Kit Kat"};
		cat.addProduit("Kit Kat", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produit avec des espace au milieu", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsAuDebut() {
		String[] tab = {"Mars"};
		cat.addProduit("	Mars", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produit avec tabulation debut ; les tabulations au d�but ne doivent pas �tre stock�s", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsALaFin() {
		String[] tab = {"Mars"};
		cat.addProduit("Mars	", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produit avec tabulation fin ; les tabulations � la fin ne doivent pas �tre stock�s", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsAuMilieu() {
		String[] tab = {"Kit Kat"};
		cat.addProduit("Kit	Kat", 10, 1,createCategorie());
		assertArrayEquals("recup�re nom produit avec des tabulations au milieu ; les tabulations au milieu doivent �tre remplac�es par des espaces", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_deuxProduits() {
		Categorie c=createCategorie();
		String[] tab = {"Mars", "Treets"};
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		assertArrayEquals("recup�re nom de deux produits", tab, cat.getNomProduits());
	}
		
	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAlphabetique() {
		String[] tab = {"Mars", "Raider", "Treets"};
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		cat.addProduit("Treets", 10, 1,c);
		assertArrayEquals("recup�re nom de plusieurs produits ajout�s dans ordre alphab�tique", tab, cat.getNomProduits());
	}	
		
	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAleatoire() {
		Categorie c=createCategorie();
		String[] tab = {"Bounty", "Mars", "Raider", "Treets"};
		cat.addProduit("Mars", 10, 1,c);
		cat.addProduit("Treets", 10, 1,c);
		cat.addProduit("Raider", 12, 2,c);
		cat.addProduit("Bounty", 12, 2,c);
		assertArrayEquals("recup�re nom de plusieurs produits ajout�s dans ordre al�atoire (doivent �tre retourn�s dans l'ordre alphab�tique)", tab, cat.getNomProduits());
	}
	
	@Test
	public void testMontantTotalTTC_catalogueVide() {
		assertEquals("montant TTC catalogue vide",0,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testMontantTotalTTC_pasDeStock() {
		cat.addProduit("Nuts", 1, 0,createCategorie());
		assertEquals("montant TTC sans stock",0,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testMontantTotalTTC_sansVirgule() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 100, 4,c);
		cat.addProduit("Raider", 20, 5,c);
		assertEquals("montant TTC sans virgule ",600,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_UnChiffreApresLaVirgule() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 6 ,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 12,c);
		assertEquals("montant TTC avec virgule ; 1 chiffre",134.4,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_DeuxChiffresApresLaVirgule() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 12.6, 1,c);		
		assertEquals("montant TTC avec virgule ; 2 chiffres",135.12,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiInferieur_TroisChiffresApresLaVirgule() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 12.66, 1,c);
		assertEquals("montant TTC avec virgule ; 135.192 doit �tre arrondi � 135.19",135.19,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 12.69, 1,c);
		assertEquals("montant TTC avec virgule ; 135.228 doit �tre arrondi � 135.23",135.23,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule_IlNeFautPasArrondirLePrixDuStockUnitaireMaisLePrixDuStockTotal() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 12.67, 1,c);
		cat.addProduit("Nuts", 12.67, 1,c);
		assertEquals("c'est le montant total TTC qu'il faut arrondir, pas les prix TTC des diff�rents produits",150.41,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testToString_CatalogueVide() {
		String resultatAttendu = "\n" +
								 "Montant total TTC du stock : 0,00 �";
		assertEquals("toString catalogue vide", resultatAttendu, cat.toString());
	}
	
	@Test	
	public void testToString_CatalogueAvecDesProduits_TotalAvecAucunChiffreApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 120,00 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		assertEquals("toString catalogue sans virgule", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_AvecDesEspaceDansLesNomsDesProduit() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
				 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
				 "Kit Kat - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
				 "\n" +
				 "Montant total TTC du stock : 120,00 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars ", 10, 5,c);
		cat.addProduit(" Treets", 10, 4,c);
		cat.addProduit("Kit Kat", 1, 10,c);
		assertEquals("toString avec des espaces dans les noms des produits", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_AvecDesTabulationsDansLesNomsDesProduit() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
				 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
				 "Kit Kat - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
				 "\n" +
				 "Montant total TTC du stock : 120,00 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars	", 10, 5,c);
		cat.addProduit("	Treets", 10, 4,c);
		cat.addProduit("Kit	Kat", 1, 10,c);
		assertEquals("toString avec des tabulations dans les noms des produits", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecUnChiffreApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
								 "Twix - prix HT : 10,45 � - prix TTC : 12,54 � - quantit� en stock : 5" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 182,70 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 10.45, 5,c);
		assertEquals("toString catalogue avec un total d'un chiffre apr�s la virgule", resultatAttendu, cat.toString());
	}
		
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecDeuxChiffresApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
								 "Twix - prix HT : 10,40 � - prix TTC : 12,48 � - quantit� en stock : 1" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 132,48 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 10.4, 1,c);
		assertEquals("toString catalogue avec un total de deux chiffres apr�s virgule", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiInferieur() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
								 "Twix - prix HT : 10,47 � - prix TTC : 12,56 � - quantit� en stock : 1" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 132,56 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 10.47, 1,c);
		assertEquals("on affiche que deux chiffres apr�s la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calcul� avec les prix unitaires TTC non arrondis",resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiSuperieur() {
		String resultatAttendu = "Mars - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 � - prix TTC : 12,00 � - quantit� en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 � - prix TTC : 1,20 � - quantit� en stock : 10" + "\n" +
								 "Twix - prix HT : 10,47 � - prix TTC : 12,56 � - quantit� en stock : 2" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 145,13 �";
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 10.47, 2,c);
		assertEquals("on affiche que deux chiffres apr�s la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calcul� avec les prix unitaires TTC non arrondis",resultatAttendu, cat.toString());
	}
	
	@Test
	public void testClear() {
		Categorie c=createCategorie();
		cat.addProduit("Mars", 10, 5,c);
		cat.addProduit("Treets", 10, 4,c);
		cat.addProduit("Raider", 1, 10,c);
		cat.addProduit("Twix", 10.47, 2,c);
		cat.clear();
		assertEquals("test clear ", 0,cat.getNomProduits().length);		
	}

	private I_Produit createProduit(String nom, double prixHT, int quantite) {
		Categorie c=new Categorie(1,"TVARestoration",0.05);
		try {
			return new Produit(nom,prixHT,quantite,c);
		}
		catch (Exception e) { return null; }
	}		
		
	
}
