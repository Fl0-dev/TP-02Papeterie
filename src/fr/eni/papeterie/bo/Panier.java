package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier extends Ligne{
    float montant;
    List<Ligne> lignesPanier = new ArrayList<>();

    public Panier() {

    }

    public float getMontant() {
        return montant;
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

    @Override
    public String toString() {

        return super.toString() +"Panier{" +
                "qte=" + qte +
                ", article=" + article +
                ", montant=" + montant +
                ", lignesPanier=" + lignesPanier +
                '}';
    }

    public void updateLigne(int index, int newQte) {
        lignesPanier.get(index).setQte(newQte);
    }
}
