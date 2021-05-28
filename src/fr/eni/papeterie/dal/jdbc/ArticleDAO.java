package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;

import java.util.List;

/**
 * Interface permettant le transtypage
 * entre les différentes implantations
 * contient les signatures des différentes méthodes
 */
public interface ArticleDAO {

    Article selectById(Integer id);
    List<Article> selectAll();
    void insert(Article article);
    void update(Article article);
    void delete(Integer id);
}
