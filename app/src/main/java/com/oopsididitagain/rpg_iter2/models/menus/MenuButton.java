package com.oopsididitagain.rpg_iter2.models.menus;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuButton extends Rectangle implements MouseListener{
	String title;
	boolean hover;

	
	public MenuButton( String title, int x, int y, int width, int height ){
		
		super(x, y, width, height);
		this.title = title;
		hover = false;
		
		
	}
	public boolean getHover(){
		
		return hover;
	}

public String getTitle(){
	
	return title;
}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		hover = true;
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		hover = false;
	}
	
}
