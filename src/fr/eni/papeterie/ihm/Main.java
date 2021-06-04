package fr.eni.papeterie.ihm;

import javax.swing.*;

/**
 * Permet de lancer l'affichage créé
 */
public class Main {
    public static void main(String[] args) {
        //création d'un écran principal
        SwingUtilities.invokeLater(
                //on pourrait faire une lambda (peu importe le nom lance)
                new Runnable() {
                    @Override
                    public void run() {
                        JFrame app =new GUI();
                    }
                }
        );
    }
}
