package com.oopsididitagain.rpg_iter2.models;

import java.awt.Image;
import java.awt.Toolkit;

public class Decal {
	public final static Decal HEART_DECAL = new Decal("/images/decals/heart_decal.png");
	public final static Decal[] AREA_EFFECTS = {
		null,
		new Decal("/images/decals/take_damage.png"),
		new Decal("/images/decals/heal_damage.png"),
		new Decal("/images/decals/instant_death.png"),
		new Decal("/images/decals/level_up.png")
	};
	
	
	
	Image img;
	
	public Decal(Image img) {
		this.img = img;
	}
	
	public Decal(String imageLink) { 
		// as in new Decal("/heart.png");
		img = Toolkit.getDefaultToolkit()
				.createImage(getClass().getResource(imageLink));
		
		// JLabel picLabel = new JLabel(new ImageIcon(img));
		// JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
	}
	
	public Image getImage() {
		return img;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}

}
