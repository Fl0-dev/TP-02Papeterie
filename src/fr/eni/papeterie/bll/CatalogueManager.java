package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.DALException;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DAOFactory;
import fr.eni.papeterie.dal.jdbc.ArticleDAO;


import java.util.List;

/**
 * Classe de la BLL qui contient les méthodes
 * pour gérer la DAO
 */
public class CatalogueManager {

    //création d'une instance de CatalogueManager (instance de classe)
    private static CatalogueManager instance;
    ArticleDAO daoArticle;

    // constructeur vide privé
    private CatalogueManager() {
    }

    //création d'une méthode getInstance
    public static CatalogueManager getInstance() {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }


    /**
     * récupère un article au catalogue
     * @param id idArticle
     * @return l'article séléctionné
     * @throws BLLException si souci SQL
     */
    public Article getArticle(Integer id) throws BLLException {

        try {
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();
            return articleDAO.selectById(id);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }

    /**
     *récupère la liste de tous les articles
     * @return la liste des articles
     * @throws BLLException si souci SQL
     */
    public List<Article> getCatalogue() throws BLLException {
        List<Article> list;
        try {
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();
            list = articleDAO.selectAll();
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
        return list;
    }

    /** ajoute un article
     * @param article à ajouter
     * @throws BLLException
     */
    public void addArticle(Article article) throws BLLException {
        try {
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();
            validerArticle(article);
            articleDAO.insert(article);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());

        }
    }

    /**
     * permet de modifier un article
     * @param article à modifier
     * @throws BLLException si souci SQL
     */
    public void updateArticle(Article article) throws BLLException {

        try {
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();

            validerArticle(article);
            articleDAO.update(article);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());

        }
    }

    /**
     * permet de supprimer un article
     * @param id de l'article
     * @throws BLLException si souci de SQL
     */
    public void removeArticle(Integer id) throws BLLException {

        try {
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();
            articleDAO.delete(id);
        }catch (DALException e) {
            throw new BLLException(e.getMessage());

        }
    }


    /**
     * permet de vérifier si l'article en paramètre contient une erreur
     * @param article à tester
     * @throws BLLException si souci de grammage, de couleur,
     * sur le nombre d'attribut de l'article et sur la quantité
     */
    public void validerArticle(Article article) throws BLLException {
        if (article instanceof Ramette && ((Ramette) article).getGrammage() <= 0) {
            throw new BLLException("Le grammage doit être positif");
        }
        if (article instanceof Stylo && ((Stylo) article).getCouleur() == null) {
            throw new BLLException("La couleur n'est pas valide");
        }
        if (article.getQteStock() < 1) {
            throw new BLLException("La quantité de stock doit être positif");
        }
        if (article.getReference() ==null || article.getReference().trim().length()==0 ||
                article.getMarque() ==null || article.getMarque().trim().length()==0 ||
                article.getDesignation() ==null|| article.getDesignation().trim().length()==0||
                article.getQteStock() == 0 || article.getPrixUnitaire()==0) {
            throw new BLLException("Il manque des attributs");
        }
    }
}
