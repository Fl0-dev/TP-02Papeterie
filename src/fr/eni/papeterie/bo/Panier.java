package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe panier qui a comme attribut
 * le montant du panier
 * et une liste de lignes d'article (ass avec Ligne)
 */
public class Panier {
    private float montant;
    List<Ligne> lignesPanier = new ArrayList<>();

    //////////////////Getters/Setters///////////////////////
    public float getMontant() {
        return montant;
    }

    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    public void setLignesPanier(List<Ligne> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }

    public Ligne getLigne(int index) {
        return lignesPanier.get(index);
    }


    /**
     * Pour ajout d'un article et sa quantité dans le panier
     * @param article instance d'Article
     * @param qte quantité
     */
    public void addLigne(Article article, int qte){
        lignesPanier.add(new Ligne(article,qte));
    }

    /**
     * Pour supprimer une ligne du panier selon
     * @param index de cette ligne
     */
    public void removeLigne (int index){
        lignesPanier.remove(getLigne(index));
    }

    /**
     * Permet de changer la quantité grâce à
     * @param index de la ligne
     * @param newQte nouvelle quantité à mettre
     */
    public void updateLigne(int index, int newQte) {
        this.getLigne(index).setQte(newQte);
    }
    //////////////////Méthode toString///////////////////////
    @Override
    public String toString() {
        return
                "Ligne "+lignesPanier +
                '}';
    }
}
