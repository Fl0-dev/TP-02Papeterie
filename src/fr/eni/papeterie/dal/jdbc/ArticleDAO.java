package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.DALException;

import java.util.List;

/**
 * Interface permettant le transtypage
 * entre les différentes implantations
 * contient les signatures des différentes méthodes
 */
public interface ArticleDAO {

    Article selectById(Integer id) throws BLLException, DALException;
    List<Article> selectAll() throws BLLException, DALException;
    void insert(Article article) throws BLLException, DALException;
    void update(Article article) throws BLLException, DALException;
    void delete(Integer id) throws BLLException, DALException;
}
