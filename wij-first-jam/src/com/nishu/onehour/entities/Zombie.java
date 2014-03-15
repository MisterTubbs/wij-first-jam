package com.nishu.onehour.entities;

import java.awt.Rectangle;

import com.nishu.onehour.utilities.Constants;
import com.teama.merc.geo.Vec2;
import com.teama.merc.math.MercMath;

public class Zombie extends Entity {

	private int life = Constants.rand.nextInt(1000);	
	private int time = 0;
	
	private Vec2 force = new Vec2();
	private Rectangle rect;
	
	public Zombie(float x, float y) {
		super(x, y, false);
		rect = new Rectangle((int) x, (int) y, 48, 48);
		force.x = MercMath.nextFloat();
		force.y = MercMath.nextFloat();
	}
	
	public void update(float delta) {
		if(time % (Constants.rand.nextInt(320) + 1) == 0) {
			force.x = MercMath.nextFloat() - 0.5f;
			force.y = MercMath.nextFloat() - 0.5f;
		}
		move(delta);
		life--;
		time++;
	}
	
	private void move(float delta) {
		addPos(force.x * delta, force.y * delta);
		if(getX() < 0 || getX() >= Constants.width - 48) force.x = -force.x;
		if(getY() < 0 || getY() >= Constants.height - 48) force.y = -force.y;
		rect.setBounds((int) getX(), (int) getY(), 48, 48);
	}
 	
	public boolean isDead() {
		if(life <= 0) return true;
		return false;
	}
	
	public void setDead() {
		life = 0;
	}
	
	public Rectangle getBounds() {
		return rect;
	}
}
