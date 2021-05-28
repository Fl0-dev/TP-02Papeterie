package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe de la DAL qui gère la relation avec la DB PAPETERIE
 */
public class ArticleDAOJdbcImpl {
    //constante du chemin de la DB
    private final String URL = "jdbc:sqlite:PAPETERIE_DB.sqlite";
    //constante pour la requête de insert()
    private final String SQLINSERT = "INSERT INTO Articles (reference,marque,designation,prixUnitaire,qteStock,grammage,couleur,type)" +
            "VALUES (?,?,?,?,?,?,?,?);";
    //constante pour la requête de delete()
    private final String SQLDELETE = "DELETE FROM Articles WHERE idArticle =?;";
    //constante pour la requête de selectById()
    private final String SQLSELECTID = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles WHERE idArticle=?;";
    //constante pour la requête de update()
    private final String SQLUPDATE = "UPDATE Articles SET reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? WHERE idArticle=?;";


    /**
     * permet de retouner tous les articles de la DB
     * @return articleListe
     */
    public List<Article> selectAll() {
        List<Article> articleList = new ArrayList<>();
        //tout ce qui est ouvert dans le try se fermera à la fin du try
        //try with ressourcies
        //ouverture de la connection vers DB
        try (Connection connection = DriverManager.getConnection(URL);
             Statement requete = connection.createStatement()) {
            String sql = "SELECT * FROM Articles;";
            ResultSet rs = requete.executeQuery(sql);
            Article article;
            while (rs.next()) {
                int id = rs.getInt("idArticle");
                String ref = rs.getString("reference");
                String marq = rs.getString("marque");
                String designation = rs.getString("designation");
                float prix = rs.getFloat("prixUnitaire");
                int qte = rs.getInt("qteStock");
                String type = rs.getString("type");
                if (rs.getString("couleur") == null) {
                    int grammage = rs.getInt("grammage");
                    article = new Ramette(id, marq, ref, designation, prix, qte, grammage);
                } else {
                    String couleur = rs.getString("couleur");
                    article = new Stylo(id, marq, ref, designation, prix, qte, couleur);
                }
                articleList.add(article);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //retourne la liste des articles
        return articleList;

    }

    /**
     * retourne un article grâce à son id
     * @param id l'id de l'article recherché
     * @return article
     */
    public Article selectById(Integer id) {
        Article article = null;
        //ouverture de la connexion à la DB
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement requete = connection.prepareStatement(SQLSELECTID)) {
            //initialisation de SQLSELECTID
            requete.setInt(1,id);
            ResultSet rs = requete.executeQuery();
            //si l'id est valide
            if (rs.next()) {
                String ref = rs.getString("reference");
                String marq = rs.getString("marque");
                String designation = rs.getString("designation");
                float prix = rs.getFloat("prixUnitaire");
                int qte = rs.getInt("qteStock");
                String type = rs.getString("type");
                //savoir si c'est une ramette
                if (rs.getString("couleur") == null) {
                    int grammage = rs.getInt("grammage");
                    //création d'une ramette
                    article = new Ramette(id, marq, ref, designation, prix, qte, grammage);
                    //sinon stylo
                } else {
                    String couleur = rs.getString("couleur");
                    //création d'un stylo
                    article = new Stylo(id, marq, ref, designation, prix, qte, couleur);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return article;
    }

    /**
     * permet de modifier un article de la table
     * avec un article de Java
     * @param article à modifier
     */
    public void update(Article article) {

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement requete = connection.prepareStatement(SQLUPDATE)) {

            //initialisation et déclaration de la variable type
            String type = "";
            //si stylo
            if (article instanceof Stylo) {
                //on met dans la requête la couleur du stylo (on caste l'article en Stylo car le getter n'est pas dans article mais dans Stylo
                //type = "couleur ='" + ((Stylo) article).getCouleur() + "'";
                requete.setString(7,((Stylo) article).getCouleur());
            }
            //si ramette est le type de l'instance article
            if (article instanceof Ramette) {
                //on met dans la requête le grammage de la ramette (on caste l'article en Ramette car le getter n'est pas dans article mais dans Ramette
                //type = "grammage=" + ((Ramette) article).getGrammage() + "";
                requete.setInt(6,((Ramette) article).getGrammage());
            }
            //Construction de la requête si requête normale
            //!!!!!!PENSEZ AUX SIMPLES '' POUR ENTOURER LES STRING DANS UNE REQUETE SQL
            /*String SQLUPDATE = "UPDATE Articles SET " +
                    "reference ='" + article.getReference() + "', " +
                    "marque ='" + article.getMarque() + "', " +
                    "designation ='" + article.getDesignation() + "', " +
                    "qteStock =" + article.getQteStock() + ", " +
                    "prixUnitaire =" + article.getPrixUnitaire() + ", " +
                    type +
                    " WHERE idArticle=" + article.getIdArticle() + ";";*/
            //initialisation de SQLUPDATE
            requete.setString(1, article.getReference());
            requete.setString(2, article.getMarque());
            requete.setString(3, article.getDesignation());
            requete.setFloat(4,article.getPrixUnitaire());
            requete.setInt(5,article.getQteStock());

            requete.executeUpdate();
            System.out.println("L''article a été bien modifié");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    /**
     * permet d'insérer un article dans la table
     * @param article à insérer
     */
    public void insert(Article article) {
        //ouverture de la connexion à la DB
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement requete = connection.prepareStatement(SQLINSERT)) {
            //si stylo
            if (article instanceof Stylo) {

                //initialisation de SQLINSERT
                requete.setString(1,article.getReference());
                requete.setString(2,article.getMarque());
                requete.setString(3,article.getDesignation());
                requete.setInt(4,article.getQteStock());
                requete.setFloat(5,article.getPrixUnitaire());
                requete.setString(7,((Stylo) article).getCouleur());
                requete.setString(8,"STYLO");
                // exécution
                requete.executeUpdate();
                // pour récupérer l'id auto incrémenté
                ResultSet rs = requete.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    //changement de l'id de l'article
                    article.setIdArticle(id);
                }
            }
            //si stylo
            if (article instanceof Ramette) {

                //initialisation de SQLINSERT
                requete.setString(1,article.getReference());
                requete.setString(2,article.getMarque());
                requete.setString(3,article.getDesignation());
                requete.setInt(4,article.getQteStock());
                requete.setFloat(5,article.getPrixUnitaire());
                requete.setInt(6,((Ramette) article).getGrammage());
                requete.setString(8,"RAMETTE");
                // exécution
                requete.executeUpdate();//sous sqlServer requête.executeUpdate(sql,statement.RETURN_GENERATED_KEY);
                // pour récupérer l'id auto incrémenté
                ResultSet rs = requete.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    //changement de l'id de l'article
                    article.setIdArticle(id);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * suppression d'un article grâce à son id
     * @param id de l'article
     */
    public void delete(Integer id) {

        try {
            //connexion à la DB
            Connection connection = DriverManager.getConnection(URL);
            PreparedStatement requete = connection.prepareStatement(SQLDELETE);
            //requête SQL
            //String sqlDelete = "DELETE FROM Articles WHERE idArticle =" + id;
            //initialisation des valeurs de SQLDELETE
            requete.setInt(1,id);
            //exécution
            requete.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
