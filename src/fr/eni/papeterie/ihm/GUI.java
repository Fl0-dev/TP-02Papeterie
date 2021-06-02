package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Couleur;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Création de l'affichage
 */
public class GUI extends JFrame {

    //panneaux
    private JPanel panneauPrincipal;
    private JPanel panneauChamp;
    private JPanel panneauType;
    private JPanel panneauRadio;
    private JPanel panneauGrammage;
    private JPanel panneauCheckBox;
    private JPanel panneauCouleur;
    private JPanel panneauButton;
    //Labels
    private JLabel ref;
    private JLabel designation;
    private JLabel marque;
    private JLabel stock;
    private JLabel prix;
    private JLabel typ;
    private JLabel grammage;
    private JLabel couleur;
    private JLabel affichage;
    //TextFields
    private JTextField refTexte;
    private JTextField designationTexte;
    private JTextField marqueTexte;
    private JTextField stockTexte;
    private JTextField prixTexte;
    //RadioButtons
    private JRadioButton ramette;
    private JRadioButton stylo;
    //CheckBox
    private JCheckBox g80;
    private JCheckBox g100;
    //ComboBox
    public JComboBox<Couleur> boxCouleur;
    //Buttons
    public JButton arriere;
    public JButton avant;
    public JButton remove;
    public JButton save;
    public JButton nvl;


    /////////////////////////Singleton Composants//////////////////////

    //Singleton Label
    public JLabel getRef() {
        if (ref == null) {
            ref = new JLabel("Référence");
        }
        return ref;
    }
    public JLabel getDesignation() {
        if (designation == null) {
            designation = new JLabel("Désignation");
        }
        return designation;
    }
    public JLabel getMarque() {
        if (marque == null) {
            marque = new JLabel("Marque");
        }
        return marque;
    }
    public JLabel getStock() {
        if (stock == null) {
            stock = new JLabel("Stock");
        }
        return stock;
    }
    public JLabel getPrix() {
        if (prix == null) {
            prix = new JLabel("Prix");
        }
        return prix;
    }
    public JLabel getTyp() {
        if (typ == null) {
            typ = new JLabel("Type");
        }
        return typ;
    }
    public JLabel getGrammage() {
        if (grammage == null) {
            grammage = new JLabel("Grammage");
        }
        return grammage;
    }
    public JLabel getCouleur() {
        if (couleur == null) {
            couleur = new JLabel("Couleur");
        }
        return couleur;
    }
    public JLabel getAffichage(){
        if (affichage == null) {
            affichage = new JLabel();
        }
        return affichage;
    }
    //Singleton TextField
    public JTextField getRefTexte() {
        if (refTexte == null) {
            refTexte = new JTextField(20);
        }
        return refTexte;
    }
    public JTextField getDesignationTexte() {
        if (designationTexte == null) {
            designationTexte = new JTextField(20);
        }
        return designationTexte;
    }
    public JTextField getMarqueTexte() {
        if (marqueTexte == null) {
            marqueTexte = new JTextField(20);
        }
        return marqueTexte;
    }
    public JTextField getStockTexte() {
        if (stockTexte == null) {
            stockTexte = new JTextField(20);
        }
        return stockTexte;
    }
    public JTextField getPrixTexte() {
        if (prixTexte == null) {
            prixTexte = new JTextField(20);
        }
        return prixTexte;
    }
    //Singleton RadioButton
    public JRadioButton getRamette() {
        if (ramette == null) {
            ramette = new JRadioButton("Ramette");


            // action si Ramette sélectionnée
            ramette.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //rend inactif laComboBox
                    getBoxCouleur().setEnabled(false);
                    getG80().doClick();
                    //rend actif les CheckBox
                    getG80().setEnabled(true);
                    getG100().setEnabled(true);
                }
            });
        }
        return ramette;
    }
    public JRadioButton getStylo()   {
        if (stylo == null) {
            stylo = new JRadioButton("Stylo");

            //action si Stylo sélectionnée
            stylo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //rend inactif les CheckBox
                    getG80().setEnabled(false);
                    getG100().setEnabled(false);
                    //rend actif la ComboBox
                    getBoxCouleur().setEnabled(true);

                }
            });
        }
        return stylo;
    }
    //Singleton CheckBox
    public JCheckBox getG80() {
        if (g80 == null) {
            g80 = new JCheckBox("80 grammes");
        }
        return g80;
    }
    public JCheckBox getG100() {
        if (g100 == null) {
            g100 = new JCheckBox("100 grammes");
        }
        return g100;
    }
    //Singleton ComboBox
    public JComboBox<Couleur> getBoxCouleur() {
        if (boxCouleur == null) {
            //Utilisation de l'énumération Couleur
            boxCouleur = new JComboBox<>(Couleur.values());
        }
        return boxCouleur;
    }
    //Singleton Button
    public JButton getAvant() {
        if (avant == null) {
            Icon icon = new ImageIcon("src/fr/eni/papeterie/ihm/resources/Forward24.gif");
            avant = new JButton(icon);
        }
        return avant;
    }//TODO
    public JButton getArriere() {
        if (arriere == null) {
            Icon icon = new ImageIcon("src/fr/eni/papeterie/ihm/resources/Back24.gif");
            arriere = new JButton(icon);
        }
        return arriere;
    }//TODO
    public JButton getRemove() {
        if (remove == null) {
            Icon icon = new ImageIcon("src/fr/eni/papeterie/ihm/resources/Delete24.gif");
            remove = new JButton(icon);


        }
        return remove;
    }//TODO
    public JButton getSave() {
        //création du bouton
        if (save == null) {
            Icon icon = new ImageIcon("src/fr/eni/papeterie/ihm/resources/Save24.gif");
            save = new JButton(icon);
            //Action du bouton save
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CatalogueManager cm = null;
                    Article article = null;
                    //Appel du CatalogueManager
                    try {
                        cm = CatalogueManager.getInstance();
                    } catch (BLLException bllException) {
                        bllException.printStackTrace();
                    }
                    //si ramette
                    if (ramette.isSelected()){
                        article = new Ramette();
                        //récupération des données des champs
                        article.setReference(getRefTexte().getText());
                        article.setDesignation(getDesignationTexte().getText());
                        article.setMarque(getMarqueTexte().getText());
                        article.setQteStock(Integer.parseInt(getStockTexte().getText()));
                        article.setPrixUnitaire(Integer.parseInt(getPrixTexte().getText()));
                        //si 80 de grammage
                        if (g80.isSelected()) {
                            ((Ramette) article).setGrammage(80);
                        }
                        //si 100 de grammage
                        else{
                            ((Ramette) article).setGrammage(100);
                        }
                    }
                    //si stylo
                    else{
                        article = new Stylo();
                        article.setReference(getRefTexte().getText());
                        article.setDesignation(getDesignationTexte().getText());
                        article.setMarque(getMarqueTexte().getText());
                        article.setQteStock(Integer.parseInt(getStockTexte().getText()));
                        article.setPrixUnitaire(Integer.parseInt(getPrixTexte().getText()));
                        //prendre la couleur sélectionnée de la ComboBox
                        ((Stylo) article).setCouleur(boxCouleur.getSelectedItem().toString());
                    }
                    try {
                        //si nouvel article
                        if(article.getIdArticle()==null) {
                            cm.addArticle(article);
                            //affichage du résultat
                            getAffichage().setText("Article bien ajouté");
                        }else {
                            //si article existant
                            cm.updateArticle(article);
                            //affichage du résultat
                            getAffichage().setText("Article bien modifié");
                        }
                    } catch (BLLException bllException) {
                        //affichage d'une erreur
                        getAffichage().setText("Impossible, souci d'attribut");

                    }


                }
            });
        }
        return save;
    }//TODO retour à la normale
    public JButton getNvl() {
        if (nvl == null) {
            Icon icon = new ImageIcon("src/fr/eni/papeterie/ihm/resources/New24.gif");
            nvl = new JButton(icon);

            //action nvl
            nvl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //mets les champs vides
                    refTexte.setText(null);
                    designationTexte.setText(null);
                    marqueTexte.setText(null);
                    stockTexte.setText(null);
                    prixTexte.setText(null);
                    //
                }
            });
        }
        return nvl;
    }

    //Singleton panneauPrincipal
    public JPanel getPanneauPrincipal() {
        //si pas de panneau
        if (panneauPrincipal == null) {
            // création du panneau principal
            panneauPrincipal = new JPanel();
            //mise en place de la grille de placement
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //Placement panneauChamp
            gbc.gridx = 0;
            gbc.gridy = 0;
            panneauPrincipal.add(getPanneauChamp(),gbc);
            //placement panneauRadio
            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauPrincipal.add(getPanneauType(), gbc);
            //placement panneauBox
            gbc.gridx = 0;
            gbc.gridy = 2;
            panneauPrincipal.add(getPanneauGrammage(), gbc);
            //Placement ComboBox
            gbc.gridx = 0;
            gbc.gridy = 3;
            panneauPrincipal.add(getPanneauCouleur(), gbc);
            //Placement panneauButton
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.insets = new Insets(5,5,5,5);
            panneauPrincipal.add(getPanneauButton(), gbc);
            //Placement label d'erreur
            gbc.gridx = 0;
            gbc.gridy = 5;
            panneauPrincipal.add(getAffichage(),gbc);
        }
        return panneauPrincipal;
    }

    //Singleton panneauChamp
    public JPanel getPanneauChamp() {
        //si pas de panneau
        if (panneauChamp == null) {
            // création du panneauChamp
            panneauChamp = new JPanel();
            //mise en place de la grille de placement
            panneauChamp.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            //le insets touche tous les gbc tant que je n'ai pas créé un new gbc
            gbc.insets = new Insets(0,5,5,10);
            panneauChamp.add(getRef(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauChamp.add(getDesignation(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panneauChamp.add(getMarque(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panneauChamp.add(getStock(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panneauChamp.add(getPrix(), gbc);
            //Placement TextField
            gbc.gridx = 1;
            gbc.gridy = 0;
            panneauChamp.add(getRefTexte(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panneauChamp.add(getDesignationTexte(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panneauChamp.add(getMarqueTexte(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panneauChamp.add(getStockTexte(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            panneauChamp.add(getPrixTexte(), gbc);
        }
        return panneauChamp;
    }

    //Singleton panneauType
    public JPanel getPanneauType(){
        //si pas de panneau
        if (panneauType == null) {
            // création du panneauChamp
            panneauType = new JPanel();
            //mise en place de la grille de placement
            panneauType.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //Placement Label
            gbc.insets = new Insets(0,0,0,85);
            panneauType.add(getTyp(),gbc);
            //Placement panneauRadio
            panneauType.add(getPanneauRadio(),gbc);
        }
        return panneauType;
    }

    // Singleton panneauRadio
    public JPanel getPanneauRadio(){
        //si pas de panneau
        if (panneauRadio == null) {
            // création du panneauCouleur
            panneauRadio = new JPanel();
            //mise en place de la grille de placement
            panneauRadio.setLayout(new BoxLayout(panneauRadio,BoxLayout.Y_AXIS));
            panneauRadio.add(getRamette());
            panneauRadio.add(getStylo());
            //regroupement des 2 boutons radio
            ButtonGroup radioButton = new ButtonGroup();
            radioButton.add(getRamette());
            radioButton.add(getStylo());
        }
        return panneauRadio;
    }

    //Singleton panneauGrammage
    public JPanel getPanneauGrammage(){
        //si pas de panneau
        if (panneauGrammage == null) {
            // création du panneauGrammage
            panneauGrammage = new JPanel();
            //mise en place de la grille de placement
            panneauGrammage.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //Placement Label
            gbc.insets = new Insets(0,0,0,70);
            panneauGrammage.add(getGrammage(),gbc);

            panneauGrammage.add(getPanneauCheckBox(),gbc);
        }
        return panneauGrammage;
    }

    // Singleton panneauCheckBox
    public JPanel getPanneauCheckBox(){
        //si pas de panneau
        if (panneauCheckBox == null) {
            // création du panneauCouleur
            panneauCheckBox = new JPanel();
            //mise en place de la grille de placement
            panneauCheckBox.setLayout(new BoxLayout(panneauCheckBox,BoxLayout.Y_AXIS));
            panneauCheckBox.add(getG80());
            panneauCheckBox.add(getG100());
            //Regroupement des boutons checkbox
            ButtonGroup boxButton = new ButtonGroup();
            boxButton.add(getG80());
            boxButton.add(getG100());
        }
        return panneauCheckBox;
    }

    //Singleton panneauCouleur
    public JPanel getPanneauCouleur(){
        //si pas de panneau
        if (panneauCouleur == null) {
            // création du panneauCouleur
            panneauCouleur = new JPanel();
            //mise en place de la grille de placement
            panneauCouleur.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0,0,0,88);
            panneauCouleur.add(getCouleur(),gbc);

            panneauCouleur.add(getBoxCouleur(),gbc);
        }
        return panneauCouleur;
    }

    //Singleton panneauButton
    public JPanel getPanneauButton() {
        //si pas de panneau
        if (panneauButton == null) {
            // création du panneau panneauButton
            panneauButton = new JPanel();
            //mise en place de la grille de placement
            panneauButton.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //placement des boutons
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5,1,5,1);
            panneauButton.add(getArriere(),gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panneauButton.add(getNvl(),gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            panneauButton.add(getSave(),gbc);
            gbc.gridx = 3;
            gbc.gridy = 0;
            panneauButton.add(getRemove(),gbc);
            gbc.gridx = 4;
            gbc.gridy = 0;
            panneauButton.add(getAvant(),gbc);
        }
        return panneauButton;
    }

    /**
     * Méthode pour l'affichage complet
     */
    public GUI() {
        //donne un titre à la fenêtre
        this.setTitle("Papeterie");
        //Donne une taille à la fenêtre
        //this.setSize(500, 500);
        //permet de centrer la fenêtre si null
        this.setLocationRelativeTo(null);
        //permet de fermer le programme quand on ferme la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //évite que la fenêtre soit modifiable
        this.setResizable(false);
        // mise en place du panneau principale
        this.setContentPane(getPanneauPrincipal());
        // permet une taille de fenêtre en fonction du panneauPrincipal
        this.pack();
        //permet l'affichage de l'écran
        this.setVisible(true);
    }
}

