package com.nishu.onehour.entities;

import com.teama.merc.env.MercEntity;
import com.teama.merc.geo.Rectangle;
import com.teama.merc.geo.Vec2;
import com.teama.merc.gfx.Color;
import com.teama.merc.gfx.Graphics;

public class Entity implements MercEntity {
	
	private Vec2 pos; 
	private boolean type;
	
	public Entity(float x, float y, boolean type){
		this.pos = new Vec2(x, y);
		this.type = type;
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(Graphics g) {
		if(!type)
			g.setColor(Color.red);
		else if(type)
			g.setColor(Color.cyan);
		g.drawRect(new Rectangle(pos.x, pos.y, 48, 48));
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public void setX(float x) {
		pos.x = x;
	}
	
	public void setY(float y) {
		pos.y = y;
	}
	
	public void setPos(float x, float y){
		this.pos.set(x, y);
	}
	
	public void addPos(float x, float y) {
		this.setPos(this.getX() + x, this.getY() + y);
	}
}
