package fr.eni.papeterie.bo;

public class Ligne {
    int qte;
    Article article;

    public Ligne(Article article, int qte) {
        this.qte = qte;
        this.article = article;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return this.article;
    }

    public float getPrix(){
        return this.article.getPrixUnitaire();
    }


    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return
                " qte=" + qte +
                " prix="+getPrix() +
                ", article=" + article +
                '}';
    }
}
