package fr.eni.papeterie.bo;

/**
 * Classe ramette
 * avec en attribut son grammage
 * qui hérite d'Article
 */
public class Ramette extends Article{
    private int grammage;

    //////////////////Constructeurs///////////////////////
    public Ramette(Integer idArticle, String marque, String ref, String designation, float pu, int qte, int grammage) {
        super(idArticle, marque, ref, designation, pu, qte);
        this.grammage = grammage;
    }

    public Ramette(String marque, String ref, String designation, float pu, int qte, int grammage) {
        super(marque, ref, designation, pu, qte);
        this.grammage = grammage;
    }
    public Ramette(){
        super();
    }

    //////////////////Getters/Setters///////////////////////
    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    //////////////////Méthode toString///////////////////////
    @Override
    public String toString() {
        return super.toString() +
         "Ramette{" +
                "grammage=" + grammage +
                '}';
    }
}
