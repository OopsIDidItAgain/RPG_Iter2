package com.oopsididitagain.rpg_iter2.models;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public class Shotgun {

		private Position avatarPosition;
		private int radiusEffected;
		private Direction direction;
		public Shotgun(Position p, Direction d){
			radiusEffected = 1;
			this.avatarPosition = p;
			direction = d;
		}
		public Direction getDirection(){
			
			return direction;
		}
		
		public void setDirection(Direction d){
			this.direction = d;
		}
		public void update(){
			
			radiusEffected+= 1;
			
		}
		public int getRadius(){
			
			return radiusEffected;
		}
		public Position getPosition(){
			return avatarPosition;
		}
		
		public StatBlob getStatBlob(int i, int j) {
			return new StatBlob(0, 0, 0, 0, 0, 0, 0, -1 *( 5 - Math.max(Math.abs(i), Math.abs(j))), 0);
		}
		
		public void render(Graphics g, int r){
			switch(direction){
			
				case NORTH: 
					drawRenderNorth(g, r);
					break;
				case SOUTH:
					drawRenderSouth(g,r);
					break;
				case EAST:
					drawRenderEast(g,r);
					break;
					
				case WEST:
					drawRenderWest(g,r);
					break;
				case NORTHEAST: 
					drawRenderNorthEast(g, r);
					break;
				case SOUTHEAST:
					drawRenderSouthEast(g,r);
					break;
				case NORTHWEST:
					drawRenderNorthWest(g,r);
					break;
					
				case SOUTHWEST:
					drawRenderSouthWest(g,r);
					break;
				default:
					break;
				
				}
		}
		
		public void drawRenderNorth(Graphics g, int r){
			
			Position p = avatarPosition;
			
			 if(r == 1){
			//draw first layer
			g.setColor(Color.BLACK);	
			g.fillRect(p.getX()* 50,(p.getY() - 1) * 50, 50, 50);
			}else if (r == 2){
			//draw first layer
			g.setColor(Color.BLACK);	
			g.fillRect(p.getX()* 50,(p.getY() - 1) * 50, 50, 50);
			
			//draw second layer
			g.setColor(Color.BLUE);	
			g.fillRect(p.getX() - 1* 50,(p.getY() - 2) * 50, 50, 50);
			g.fillRect(p.getX()* 50,(p.getY() - 2) * 50, 50, 50);
			g.fillRect(p.getX() + 1* 50,(p.getY() - 2) * 50, 50, 50);

			}
			else if(r == 3){
				//draw first
				g.setColor(Color.BLACK);	
				g.fillRect(p.getX()* 50,(p.getY() - 1) * 50, 50, 50);
				
				//draw second layer
				g.setColor(Color.BLUE);	
				g.fillRect((p.getX() - 1)* 50,(p.getY() - 2) * 50, 50, 50);
				g.fillRect(p.getX()* 50,(p.getY() - 2) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() - 2) * 50, 50, 50);
				
				//draw third
				g.setColor(Color.GREEN);	
				g.fillRect((p.getX() - 2)* 50,(p.getY() - 3) * 50, 50, 50);
				g.fillRect((p.getX() - 1)* 50,(p.getY() - 3) * 50, 50, 50);

				g.fillRect(p.getX()* 50,(p.getY() - 3) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() - 3) * 50, 50, 50);

				g.fillRect((p.getX() + 2)* 50,(p.getY() - 3) * 50, 50, 50);
				
			
			}else if( r == 4){
				
				//draw first
				g.setColor(Color.BLACK);	
				g.fillRect(p.getX()* 50,(p.getY() - 1) * 50, 50, 50);
				
				//draw second layer
				g.setColor(Color.BLUE);	
				g.fillRect((p.getX() - 1)* 50,(p.getY() - 2) * 50, 50, 50);
				g.fillRect(p.getX()* 50,(p.getY() - 2) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() - 2) * 50, 50, 50);
				
				//draw third
				g.setColor(Color.GREEN);	
				g.fillRect((p.getX() - 2)* 50,(p.getY() - 3) * 50, 50, 50);
				g.fillRect((p.getX() - 1)* 50,(p.getY() - 3) * 50, 50, 50);

				g.fillRect(p.getX()* 50,(p.getY() - 3) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() - 3) * 50, 50, 50);

				g.fillRect((p.getX() + 2)* 50,(p.getY() - 3) * 50, 50, 50);
				
				
				//draw fourth
				g.setColor(Color.RED);	
				g.fillRect((p.getX() - 3)* 50,(p.getY() - 4) * 50, 50, 50);
				g.fillRect((p.getX() - 2)* 50,(p.getY() - 4) * 50, 50, 50);
				g.fillRect((p.getX() - 1)* 50,(p.getY() - 4) * 50, 50, 50);
				g.fillRect((p.getX())* 50,(p.getY() - 4) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() - 4) * 50, 50, 50);
				g.fillRect((p.getX() + 2)* 50,(p.getY() - 4) * 50, 50, 50);
				g.fillRect((p.getX() + 3)* 50,(p.getY() - 4) * 50, 50, 50);

				
			}
			
		  
			
			
			
			
		}
public void drawRenderSouth(Graphics g, int r){
			
			Position p = avatarPosition;
			
			 if(r == 1){
			//draw first layer
			g.setColor(Color.BLACK);	
			g.fillRect(p.getX()* 50,(p.getY() + 1) * 50, 50, 50);
			}else if (r == 2){
			//draw first layer
			g.setColor(Color.BLACK);	
			g.fillRect(p.getX()* 50,(p.getY() + 1) * 50, 50, 50);
			
			//draw second layer
			g.setColor(Color.BLUE);	
			g.fillRect(p.getX() - 1* 50,(p.getY() + 2) * 50, 50, 50);
			g.fillRect(p.getX()* 50,(p.getY() + 2) * 50, 50, 50);
			g.fillRect(p.getX() + 1* 50,(p.getY() + 2) * 50, 50, 50);

			}
			else if(r == 3){
				//draw first
				g.setColor(Color.BLACK);	
				g.fillRect(p.getX()* 50,(p.getY() + 1) * 50, 50, 50);
				
				//draw second layer
				g.setColor(Color.BLUE);	
				g.fillRect((p.getX() - 1)* 50,(p.getY() + 2) * 50, 50, 50);
				g.fillRect(p.getX()* 50,(p.getY() + 2) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() + 2) * 50, 50, 50);
				
				//draw third
				g.setColor(Color.GREEN);	
				g.fillRect((p.getX() - 2)* 50,(p.getY() + 3) * 50, 50, 50);
				g.fillRect((p.getX() - 1)* 50,(p.getY() + 3) * 50, 50, 50);

				g.fillRect(p.getX()* 50,(p.getY() + 3) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() + 3) * 50, 50, 50);

				g.fillRect((p.getX() + 2)* 50,(p.getY() + 3) * 50, 50, 50);
				
			
			}else if( r == 4){
				
				//draw first
				g.setColor(Color.BLACK);	
				g.fillRect(p.getX()* 50,(p.getY() + 1) * 50, 50, 50);
				
				//draw second layer
				g.setColor(Color.BLUE);	
				g.fillRect((p.getX() - 1)* 50,(p.getY() + 2) * 50, 50, 50);
				g.fillRect(p.getX()* 50,(p.getY() + 2) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() + 2) * 50, 50, 50);
				
				//draw third
				g.setColor(Color.GREEN);	
				g.fillRect((p.getX() - 2)* 50,(p.getY() + 3) * 50, 50, 50);
				g.fillRect((p.getX() - 1)* 50,(p.getY() + 3) * 50, 50, 50);

				g.fillRect(p.getX()* 50,(p.getY() + 3) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() + 3) * 50, 50, 50);

				g.fillRect((p.getX() + 2)* 50,(p.getY() + 3) * 50, 50, 50);
				
				
				//draw fourth
				g.setColor(Color.RED);	
				g.fillRect((p.getX() - 3)* 50,(p.getY() + 4) * 50, 50, 50);
				g.fillRect((p.getX() - 2)* 50,(p.getY() + 4) * 50, 50, 50);
				g.fillRect((p.getX() - 1)* 50,(p.getY() + 4) * 50, 50, 50);
				g.fillRect((p.getX())* 50,(p.getY() + 4) * 50, 50, 50);
				g.fillRect((p.getX() + 1)* 50,(p.getY() + 4) * 50, 50, 50);
				g.fillRect((p.getX() + 2)* 50,(p.getY() + 4) * 50, 50, 50);
				g.fillRect((p.getX() + 3)* 50,(p.getY() + 4) * 50, 50, 50);

				
			}
			
		  
			
			
			
			
		}
public void drawRenderWest(Graphics g, int r){
	
	Position p = avatarPosition;
	
	 if(r == 1){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() - 1)* 50,(p.getY()) * 50, 50, 50);
	}else if (r == 2){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() - 1)* 50,(p.getY()) * 50, 50, 50);
	
	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() - 2)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 1) * 50, 50, 50);

	}
	else if(r == 3){
		//draw first layer
		g.setColor(Color.BLACK);	
		g.fillRect((p.getX() - 1)* 50,(p.getY()) * 50, 50, 50);
		
		//draw second layer
		g.setColor(Color.BLUE);	
		g.fillRect((p.getX() - 2)* 50,(p.getY() + 1) * 50, 50, 50);
		g.fillRect((p.getX() - 2)* 50,(p.getY() ) * 50, 50, 50);
		g.fillRect((p.getX() - 2)* 50,(p.getY() - 1) * 50, 50, 50);

		//draw third
		g.setColor(Color.GREEN);	
		g.fillRect((p.getX() - 3)* 50,(p.getY() + 2 ) * 50, 50, 50);
		g.fillRect((p.getX() - 3)* 50,(p.getY()  + 1) * 50, 50, 50);

		g.fillRect((p.getX() - 3)* 50,(p.getY() ) * 50, 50, 50);
		g.fillRect((p.getX() - 3)* 50,(p.getY() - 1) * 50, 50, 50);

		g.fillRect((p.getX() - 3)* 50,(p.getY()  - 2) * 50, 50, 50);
		
	
	}else if( r == 4){
		
		//draw first layer
		g.setColor(Color.BLACK);	
		g.fillRect((p.getX() - 1)* 50,(p.getY()) * 50, 50, 50);
		
		//draw second layer
		g.setColor(Color.BLUE);	
		g.fillRect((p.getX() - 2)* 50,(p.getY() + 1) * 50, 50, 50);
		g.fillRect((p.getX() - 2)* 50,(p.getY() ) * 50, 50, 50);
		g.fillRect((p.getX() - 2)* 50,(p.getY() - 1) * 50, 50, 50);

		//draw third
		g.setColor(Color.GREEN);	
		g.fillRect((p.getX() - 3)* 50,(p.getY() + 2 ) * 50, 50, 50);
		g.fillRect((p.getX() - 3)* 50,(p.getY()  + 1) * 50, 50, 50);

		g.fillRect((p.getX() - 3)* 50,(p.getY() ) * 50, 50, 50);
		g.fillRect((p.getX() - 3)* 50,(p.getY() - 1) * 50, 50, 50);

		g.fillRect((p.getX() - 3)* 50,(p.getY()  - 2) * 50, 50, 50);
		
		
		//draw fourth
		g.setColor(Color.RED);	
		g.fillRect((p.getX() - 4)* 50,(p.getY()  + 3) * 50, 50, 50);

		g.fillRect((p.getX() - 4)* 50,(p.getY() + 2 ) * 50, 50, 50);
		g.fillRect((p.getX() - 4)* 50,(p.getY()  + 1) * 50, 50, 50);

		g.fillRect((p.getX() - 4)* 50,(p.getY() ) * 50, 50, 50);
		g.fillRect((p.getX() - 4)* 50,(p.getY() - 1) * 50, 50, 50);

		g.fillRect((p.getX() - 4)* 50,(p.getY()  - 2) * 50, 50, 50);
		g.fillRect((p.getX() - 4)* 50,(p.getY()  - 3) * 50, 50, 50);

		

		
	}
	
  
	
	
	
	

		
}
public void drawRenderEast(Graphics g, int r){

Position p = avatarPosition;

if(r == 1){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY()) * 50, 50, 50);
}else if (r == 2){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY()) * 50, 50, 50);
	
	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 1) * 50, 50, 50);

}
else if(r == 3){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY()) * 50, 50, 50);
	
	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX()+ 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX()+ 2)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 1) * 50, 50, 50);
	
	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()+3)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX()+ 3)* 50,(p.getY()  + 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 3)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() + 3)* 50,(p.getY() - 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 3)* 50,(p.getY()  - 2) * 50, 50, 50);
	

}else if( r == 4){

	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY()) * 50, 50, 50);
	
	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 1) * 50, 50, 50);
	
	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX() + 3)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX() + 3)* 50,(p.getY()  + 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 3)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() + 3)* 50,(p.getY() - 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 3)* 50,(p.getY()  - 2) * 50, 50, 50);


	//draw fourth
	g.setColor(Color.RED);	
	g.fillRect((p.getX() + 4)* 50,(p.getY()  + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 4)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX() + 4)* 50,(p.getY()  + 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 4)* 50,(p.getY() ) * 50, 50, 50);
	g.fillRect((p.getX() + 4)* 50,(p.getY() - 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 4)* 50,(p.getY()  - 2) * 50, 50, 50);
	g.fillRect((p.getX() + 4)* 50,(p.getY()  - 3) * 50, 50, 50);

}


}

public void drawRenderNorthEast(Graphics g, int r){

Position p = avatarPosition;

if(r == 1){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() + 1)* 50,(p.getY() - 1) * 50, 50, 50);
}else if (r == 2){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() + 1)* 50,(p.getY() - 1) * 50, 50, 50);

//draw second layer
g.setColor(Color.BLUE);	
g.fillRect((p.getX() + 1)* 50,(p.getY() - 2) * 50, 50, 50);
g.fillRect((p.getX() + 2)* 50,(p.getY() - 1 ) * 50, 50, 50);

}
else if(r == 3){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY() - 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() + 1)* 50,(p.getY() - 2) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 1 ) * 50, 50, 50);
	
	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()+3)* 50,(p.getY() - 1 ) * 50, 50, 50);
	g.fillRect((p.getX()+ 3)* 50,(p.getY()  - 2) * 50, 50, 50);
	
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 2 ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 1)* 50,(p.getY()  - 3) * 50, 50, 50);
	

}else if( r == 4){

	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY() - 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() + 1)* 50,(p.getY() - 2) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 1 ) * 50, 50, 50);
	
	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()+3)* 50,(p.getY() - 1 ) * 50, 50, 50);
	g.fillRect((p.getX()+ 3)* 50,(p.getY()  - 2) * 50, 50, 50);
	
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 2 ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() - 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 1)* 50,(p.getY()  - 3) * 50, 50, 50);
	

	//draw fourth
	g.setColor(Color.RED);	
	g.fillRect((p.getX() + 4)* 50,(p.getY()  - 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 4)* 50,(p.getY()  - 2) * 50, 50, 50);
	g.fillRect((p.getX() + 4)* 50,(p.getY()  - 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 3)* 50,(p.getY() - 3) * 50, 50, 50);
	g.fillRect((p.getX() + 3)* 50,(p.getY() - 4) * 50, 50, 50);
	
	g.fillRect((p.getX() + 2)* 50,(p.getY()  - 4) * 50, 50, 50);
	g.fillRect((p.getX() + 1)* 50,(p.getY()  - 4) * 50, 50, 50);

}


}
public void drawRenderSouthEast(Graphics g, int r){

Position p = avatarPosition;

if(r == 1){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() + 1)* 50,(p.getY() + 1) * 50, 50, 50);
}else if (r == 2){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() + 1)* 50,(p.getY() + 1) * 50, 50, 50);

//draw second layer
g.setColor(Color.BLUE);	
g.fillRect((p.getX() + 2)* 50,(p.getY() + 1) * 50, 50, 50);
g.fillRect((p.getX() + 1)* 50,(p.getY() + 2 ) * 50, 50, 50);

}
else if(r == 3){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY() + 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() + 1)* 50,(p.getY() + 2 ) * 50, 50, 50);

	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()+3)* 50,(p.getY() + 1 ) * 50, 50, 50);
	g.fillRect((p.getX()+ 3)* 50,(p.getY()  + 2) * 50, 50, 50);
	
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 1)* 50,(p.getY()  + 3) * 50, 50, 50);
	

}else if( r == 4){

	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() + 1)* 50,(p.getY() + 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() + 1)* 50,(p.getY() + 2 ) * 50, 50, 50);

	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()+3)* 50,(p.getY() + 1 ) * 50, 50, 50);
	g.fillRect((p.getX()+ 3)* 50,(p.getY()  + 2) * 50, 50, 50);
	
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX() + 2)* 50,(p.getY() + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 1)* 50,(p.getY()  + 3) * 50, 50, 50);
	
	
	//draw fourth
	g.setColor(Color.RED);	
	g.fillRect((p.getX() + 4)* 50,(p.getY()  + 1) * 50, 50, 50);
	
	g.fillRect((p.getX() + 4)* 50,(p.getY()  + 2) * 50, 50, 50);
	g.fillRect((p.getX() + 4)* 50,(p.getY()  + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() + 3)* 50,(p.getY() + 3) * 50, 50, 50);
	g.fillRect((p.getX() + 3)* 50,(p.getY()  +4) * 50, 50, 50);
	
	g.fillRect((p.getX() + 2)* 50,(p.getY()  + 4) * 50, 50, 50);
	g.fillRect((p.getX() + 1)* 50,(p.getY()  + 4) * 50, 50, 50);
	
}


}

public void drawRenderSouthWest(Graphics g, int r){

Position p = avatarPosition;


if(r == 1){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() - 1)* 50,(p.getY() + 1) * 50, 50, 50);
}else if (r == 2){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() - 1)* 50,(p.getY() + 1) * 50, 50, 50);

//draw second layer
g.setColor(Color.BLUE);	
g.fillRect((p.getX() - 2)* 50,(p.getY() + 1) * 50, 50, 50);
g.fillRect((p.getX() - 1)* 50,(p.getY() + 2 ) * 50, 50, 50);

}
else if(r == 3){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() - 1)* 50,(p.getY() + 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() - 1)* 50,(p.getY() + 2 ) * 50, 50, 50);

	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()-3)* 50,(p.getY() + 1 ) * 50, 50, 50);
	g.fillRect((p.getX()- 3)* 50,(p.getY()  + 2) * 50, 50, 50);
	
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() - 1)* 50,(p.getY()  + 3) * 50, 50, 50);
	

}else if( r == 4){

	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() - 1)* 50,(p.getY() + 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 1) * 50, 50, 50);
	g.fillRect((p.getX() - 1)* 50,(p.getY() + 2 ) * 50, 50, 50);

	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()-3)* 50,(p.getY() + 1 ) * 50, 50, 50);
	g.fillRect((p.getX()- 3)* 50,(p.getY()  + 2) * 50, 50, 50);
	
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 2 ) * 50, 50, 50);
	g.fillRect((p.getX() - 2)* 50,(p.getY() + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() - 1)* 50,(p.getY()  + 3) * 50, 50, 50);
	
	
	//draw fourth
	g.setColor(Color.RED);	
	g.fillRect((p.getX() - 4)* 50,(p.getY()  + 1) * 50, 50, 50);
	
	g.fillRect((p.getX() - 4)* 50,(p.getY()  + 2) * 50, 50, 50);
	g.fillRect((p.getX() - 4)* 50,(p.getY()  + 3) * 50, 50, 50);
	
	g.fillRect((p.getX() - 3)* 50,(p.getY() + 3) * 50, 50, 50);
	g.fillRect((p.getX() - 3)* 50,(p.getY()  +4) * 50, 50, 50);
	
	g.fillRect((p.getX() - 2)* 50,(p.getY()  + 4) * 50, 50, 50);
	g.fillRect((p.getX() - 1)* 50,(p.getY()  + 4) * 50, 50, 50);
	

}





}

public void drawRenderNorthWest(Graphics g, int r){

Position p = avatarPosition;


if(r == 1){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() - 1)* 50,(p.getY() - 1) * 50, 50, 50);
}else if (r == 2){
//draw first layer
g.setColor(Color.BLACK);	
g.fillRect((p.getX() - 1)* 50,(p.getY() - 1) * 50, 50, 50);

//draw second layer
g.setColor(Color.BLUE);	
g.fillRect((p.getX() - 2)* 50,(p.getY() - 1) * 50, 50, 50);
g.fillRect((p.getX() - 1)* 50,(p.getY() - 2 ) * 50, 50, 50);

}
else if(r == 3){
	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() - 1)* 50,(p.getY() - 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 1) * 50, 50, 50);
	g.fillRect((p.getX() - 1)* 50,(p.getY() - 2 ) * 50, 50, 50);

	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()-3)* 50,(p.getY() -  1 ) * 50, 50, 50);
	g.fillRect((p.getX()- 3)* 50,(p.getY()  - 2) * 50, 50, 50);
	
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 2 ) * 50, 50, 50);
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 3) * 50, 50, 50);
	
	g.fillRect((p.getX() - 1)* 50,(p.getY()  - 3) * 50, 50, 50);
	

}else if( r == 4){

	//draw first layer
	g.setColor(Color.BLACK);	
	g.fillRect((p.getX() - 1)* 50,(p.getY() - 1) * 50, 50, 50);

	//draw second layer
	g.setColor(Color.BLUE);	
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 1) * 50, 50, 50);
	g.fillRect((p.getX() - 1)* 50,(p.getY() - 2 ) * 50, 50, 50);

	//draw third
	g.setColor(Color.GREEN);	
	g.fillRect((p.getX()-3)* 50,(p.getY() -  1 ) * 50, 50, 50);
	g.fillRect((p.getX()- 3)* 50,(p.getY()  - 2) * 50, 50, 50);
	
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 2 ) * 50, 50, 50);
	g.fillRect((p.getX() - 2)* 50,(p.getY() - 3) * 50, 50, 50);
	
	g.fillRect((p.getX() - 1)* 50,(p.getY()  - 3) * 50, 50, 50);
	
	
	//draw fourth
	g.setColor(Color.RED);	
	g.fillRect((p.getX() - 4)* 50,(p.getY()  - 1) * 50, 50, 50);
	
	g.fillRect((p.getX() - 4)* 50,(p.getY()  - 2) * 50, 50, 50);
	g.fillRect((p.getX() - 4)* 50,(p.getY()  - 3) * 50, 50, 50);
	
	g.fillRect((p.getX() - 3)* 50,(p.getY() - 3) * 50, 50, 50);
	g.fillRect((p.getX() - 3)* 50,(p.getY()  - 4) * 50, 50, 50);
	
	g.fillRect((p.getX() - 2)* 50,(p.getY()  - 4) * 50, 50, 50);
	g.fillRect((p.getX() - 1)* 50,(p.getY()  - 4) * 50, 50, 50);
	

}





}


		public boolean isAlive() {
			return radiusEffected >= 0.0f;
		}
}


