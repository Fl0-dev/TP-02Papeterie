package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private float montant;
    List<Ligne> lignesPanier = new ArrayList<>();


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

    public void addLigne(Article article, int qte){
        lignesPanier.add(new Ligne(article,qte));
    }
    public void removeLigne (int index){
        lignesPanier.remove(getLigne(index));
    }

    public void updateLigne(int index, int newQte) {
        this.getLigne(index).setQte(newQte);
    }

    @Override
    public String toString() {
        return
                "Ligne "+lignesPanier +
                '}';
    }
}
