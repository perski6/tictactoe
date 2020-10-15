package game;

import game.gui.ClientGame;
import game.gui.ServerGame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int choice = Integer.parseInt(JOptionPane.showInputDialog("1 - Serwer | 2 - Klient"));
        if (choice == 1){
        new ServerGame();}
        else if (choice == 2){
            new ClientGame();
        }
    }
}
