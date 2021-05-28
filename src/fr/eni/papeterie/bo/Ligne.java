package fr.eni.papeterie.bo;

/**
 * Classe qui permet de faire une ligne
 * qui contient un article et sa quantité
 */
public class Ligne {
    int qte;
    Article article;

    //////////////////Constructeurs///////////////////////
    public Ligne(Article article, int qte) {
        this.qte = qte;
        this.article = article;
    }

    //////////////////Getters/Setters///////////////////////
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public float getPrix(){
        return this.article.getPrixUnitaire();
    }

    //////////////////Méthode toString///////////////////////
    @Override
    public String toString() {
        return
                " qte=" + qte +
                " prix="+getPrix() +
                ", article=" + article +
                '}';
    }
}
