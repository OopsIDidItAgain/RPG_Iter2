package com.oopsididitagain.rpg_iter2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observer;

/**
 *
 *
 */
public class RunGame extends JFrame{


    JPanel MainWindow;


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RunGame runGame = new RunGame("The Best RPG in the Business!");

        runGame.setSize(640, 480);
        runGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runGame.setResizable(false);
        runGame.setVisible(true);
        runGame.updateMainWindow();
    }

    private RunGame(String s){
        super (s);

        MainWindow = new JPanel(new BorderLayout(5,5));

        JPanel centerPanel = new JPanel(new BorderLayout(5,5));
        centerPanel.add(MainWindow, BorderLayout.CENTER);

        setLayout(new BorderLayout(5,5));
    }



    public void updateMainWindow(){
        MainWindow.removeAll();

        MainWindow.revalidate();
        MainWindow.repaint();
    }


}
