package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDAO;
import fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl;

/**
 * Classe DAOFactory
 * permet de choisir selon le contexte l'instance à la DAO
 * Ici on a que SQLite
 *
 */
public class DAOFactory {

    /**
     * permet de créer une instance à partir
     * de ArticleDAOJdbc
     * @return instance d'ArticleDAO
     */
    public static ArticleDAO getArticleDAO(){
        ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
        return articleDAO;
    }
}
