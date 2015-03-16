package com.oopsididitagain.rpg_iter2.views;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;


/**
 *
 * void	update(Observable o, Object arg) This method is called whenever the observed object is changed.
 *
 */
public class MainMenuView implements Observer {

    private MainMenu menu = null;


    @Override
    public void update(Observable obj, Object arg) {
        menu = (MainMenu)obj;
    }
}
