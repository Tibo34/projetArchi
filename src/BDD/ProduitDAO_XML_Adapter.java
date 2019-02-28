package BDD;

import Modele.I_Catalogue;
import Modele.I_Produit;

import java.util.List;

public class ProduitDAO_XML_Adapter implements I_ProduitDAO {
	
    private ProduitDAO_XML dao;
    private String uri;

    public ProduitDAO_XML_Adapter() {
        dao = new ProduitDAO_XML();
        setUri(dao.getUri());
    }

    public ProduitDAO_XML_Adapter(String absolutePath) {
		dao=new ProduitDAO_XML(absolutePath);
		setUri(absolutePath);
	}

	@Override
    public boolean addNouveauProduit(I_Produit p,I_Catalogue c) {
        return dao.creer(p,c.getName());
    }

    @Override
    public boolean editProduit(I_Produit p,I_Catalogue c) {
        return dao.maj(p,c.getName());
    }

    @Override
    public boolean achatProduit(I_Produit p,I_Catalogue c) {
        return dao.maj(p,c.getName());
    }

    @Override
    public boolean venteProduit(I_Produit p,I_Catalogue c) {
        return dao.maj(p,c.getName());
    }

    @Override
    public boolean delProduit(I_Produit p,I_Catalogue c) {
        return dao.supprimer(p,c.getName());
    }

    @Override
    public I_Produit getProduit(String name,I_Catalogue c) {
        return dao.lire(name,c.getName());
    }
    
	@Override
	public List<I_Produit> getAllProduits(String name) {
		return dao.lireTous(name);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
		dao.setUri(uri);
	}
}
