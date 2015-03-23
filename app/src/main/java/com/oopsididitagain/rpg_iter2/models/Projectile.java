package com.oopsididitagain.rpg_iter2.models;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public class Projectile {
	private Position position;
	private Position start;
	private Position end;
	private float x;
	private float y;
	private float vx = 0;
	private float vy = 0;
	private float speed;

	public Projectile(Position position) {
		this.position = position.createPositionAtDirection(position
				.getDirection());
		// this.start = new Position(position.getY(), position.getX(),
		// position.getDirection());
		this.start = position;
		this.end = position;
		this.x = 50 * start.getX() + 25;
		this.y = 50 * start.getY() + 25;
		this.speed = 20;
		switch (position.getDirection()) {
		case EAST:
			vx = 1;
			break;
		case NORTH:
			vy = -1;
			break;
		case NORTHEAST:
			vx = 1;
			vy = -1;
			break;
		case NORTHWEST:
			vx = -1;
			vy = -1;
			break;
		case SOUTH:
			vy = 1;
			break;
		case SOUTHEAST:
			vx = 1;
			vy = 1;
			break;
		case SOUTHWEST:
			vx = -1;
			vy = 1;
			break;
		case WEST:
			vx = -1;
			break;
		default:
			break;

		}
	}

	public Position getPosition() {
		return position;
	}

	public void doLogic() {
		Direction d = position.getDirection();
		end = new Position(position.getY(), position.getX(),
				position.getDirection());
		position = position.createPositionAtDirection(d);
		/*
		System.out.println(position.getX() + " " + position.getY() + " start:"
				+ start.getX() + " " + start.getY() + " end:" + end.getX()
				+ " " + +end.getY());
				*/
	}

	public void update() {
		x += speed * vx;
		y += speed * vy;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) x, (int) y, 10, 10);
	}

	public Position getStart() {
		return start;
	}

	public Position getEnd() {
		return end;
	}

	public StatBlob getStatBlob() {
		return new StatBlob(0, 0, 0, 0, 0, 0, 0, -1, 0);
	}

	public boolean inBounds(float x, float y) {

		float startx, starty, endx, endy;

		startx = start.getX() * 50 + 25;
		endx = end.getX() * 50 + 25;
		starty = start.getY() * 50 + 25;
		endy = end.getY() * 50 + 25;
		/*
		System.out.println(this.x+" "+this.y+" "+x + " " + y + " " + startx + " " + endx + " " + starty
				+ " " + endy);
				*/
		if (start.getX() > end.getX()) {
			if (start.getY() > end.getY()) {
				if ((this.x <= startx && this.x >= endx)
						&& (this.y <= starty && this.y >= endy)) {
					return true;
				}
				return false;
			} else {
				if ((this.x <= startx && this.x >= endx)
						&& (this.y <= endy && this.y >= starty)) {
					return true;
				}
				return false;
			}
		} else {
			if (start.getY() > end.getY()) {
				if ((this.x <= endx && this.x >= startx)
						&& (this.y <= starty && this.y >= endy)) {
					return true;
				}
				return false;
			} else {
				if ((this.x <= endx && this.x >= startx)
						&& (this.y <= endy && this.y >= starty)) {
					return true;
				}
				return false;
			}
		}
	}
}
