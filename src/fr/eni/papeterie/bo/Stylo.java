package fr.eni.papeterie.bo;

/**
 * Classe Stylo avec en attribut sa couleur
 * qui hérite de la classe Article
 */
public class Stylo extends Article{
    String couleur;

    //////////////////Constructeurs///////////////////////
    public Stylo(String couleur) {
        this.couleur = couleur;
    }

    public Stylo(Integer idArticle, String marque, String ref, String designation, float pu, int qte, String couleur) {
        super(idArticle, marque, ref, designation, pu, qte);
        this.couleur = couleur;
    }

    public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur) {
        super(marque, ref, designation, pu, qte);
        this.couleur = couleur;
    }

    //////////////////Getters/Setters///////////////////////
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    //////////////////Méthode toString///////////////////////
    @Override
    public String toString() {
        return super.toString() +"Stylo{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
