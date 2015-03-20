package com.oopsididitagain.rpg_iter2.views;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


/**
 *
 * void	update(Observable o, Object arg) This method is called whenever the observed object is changed.
 *
 */
public class MainMenuView extends JPanel implements Observer {

    private MainMenu menu;
    
    public MainMenuView(){//Constructor
        menu = new MainMenu();

    }


    @Override
    public void update(Observable obj, Object arg) {
        menu = (MainMenu)obj;
    }

 
}
