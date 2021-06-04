package fr.eni.papeterie.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Permet de lancer l'affichage créé
 */
public class Main {

    //création d'un logger en attribut de classe
    private static Logger logMain = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //placement de log
        logMain.debug("Démarrage du programme");
        //création d'un écran principal
        SwingUtilities.invokeLater(
                //on pourrait faire une lambda (peu importe le nom lance)
                new Runnable() {

                    @Override
                    public void run() {
                        //placement de log
                        logMain.debug("Lancement de GUI");
                        JFrame app =new GUI();
                    }
                }
        );
        //placement de log
        logMain.debug("Fin du programme");
    }
}
