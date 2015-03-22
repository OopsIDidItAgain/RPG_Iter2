package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.items.InventoryEquipableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUsableItem;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.views.View;

public class InventoryViewInteraction extends ModelViewInteraction {
	private InventoryMenu inventoryMenu;
	private Inventory inventory;
	private Graphics2D gr;

	public InventoryViewInteraction(InventoryMenu inventoryMenu, Inventory inventory) {
		this.inventoryMenu = inventoryMenu;
		this.inventory = inventory;
	}

	@Override
	public void accept(View view) {
		view.visit(this);
	}

	@Override
	public void drawModel(Graphics g) {
		int option = inventoryMenu.getSelectedOption();
		int size = inventory.size();

		int	firstItemRendered = option - (option % 12);
		int n = 0;
		int num = 1;
		int lastItemRendered = 0;
		int loop = size/11;
		if(size%11 != 0) ++loop;
		
		while(n<loop) {
			if(option <= 11 * num  &&  option >= 12 * n){
				if((size-1) < 11*num){
					lastItemRendered= size; 
					break;
				}else{
					lastItemRendered= 12*num; 
					break;
				}
			}
			++num;
			++n;
		}
		this.printMatrix(g,firstItemRendered,lastItemRendered,option);
	}
	
	private void printMatrix(Graphics g, int bottom, int top, int option) {
		Graphics2D g2 = (Graphics2D) g;
		this.gr = g2;
		g2.setPaint(Color.red);
		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(120, 90,
				360, 360, 20, 20);
		g2.fill(roundedRectangle);
		g2.setPaint(Color.blue);
		RoundRectangle2D roundedRectangle1 = new RoundRectangle2D.Float(140,
				150, 320, 280, 20, 20);
		g2.fill(roundedRectangle1);
		g2.setPaint(Color.green);

		int x = 130, y = 90;
		String[] s = { "OPTION: " + option,
				"Press USE or your 'use' key to select/unselect item",
				"Press INVENTORY to close inventory", "Press INVENTORY to close inventory" };
		for (int i = 0; i < 3; i++)
			g2.drawString(s[i], x, y += g2.getFontMetrics().getHeight());
		
		int size = inventory.size();
		int count = bottom;
		int w = 156;
		int h = 175;
		Assets assets = new Assets();
		for (int i = 0; i != 3; ++i) {
			for (int j = 0; j != 4; ++j) {
				if (count < top && count < size) {
					Rectangle r = new Rectangle(w, h, 60, 60);
					InventoryItem item = inventory.getItemAtIndex(count);
					item.accept(this); // Deals with displaying EquippedItems

					if (count == option) {
						r.grow(10, 10);
						g2.fill(r);
					}

					g2.fill(r);
					Image img = assets.getImage(item.getId());
					g.drawImage(img, w, h, 60, 60, null);
					++count;
				} else {
					Rectangle r = new Rectangle(w, h, 60, 60);
					g2.fill(r);
				}
				g2.setPaint(Color.green);
				w += 76;
			}
			w = 156;
			h += 85;
		}
	}

	public void visit(InventoryEquipableItem inventoryEquipableItem) {
		if (inventoryEquipableItem.isEquipped())
			gr.setPaint(Color.red);
	}

	public void visit(InventoryUsableItem inventoryUsableItem) {
		gr.setPaint(Color.cyan);
	}

	public void visit(InventoryUnusableItem inventoryUnusableItem) {
		gr.setPaint(Color.gray);
	}

}
