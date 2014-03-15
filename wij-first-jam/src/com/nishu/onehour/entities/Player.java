package com.nishu.onehour.entities;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.teama.merc.fmwk.Runner;
import com.teama.merc.in.Input;

public class Player extends Entity {

	private Runner rnr;
	private ArrayList<Zombie> zombies;
	Input in;
	
	private Rectangle rect;
	
	private float moveSpeed = 0.3f;
	private int coolDown = 0, score = 0;
	
	public Player(float x, float y, Runner rnr, ArrayList<Zombie> zombies) {
		super(x, y, true);
		this.rnr = rnr;
		this.zombies = zombies;
		rect = new Rectangle((int) x, (int) y, 48, 48);
	}
	
	public void update(float delta) {
		in = rnr.getInput();
		if(in.keyDown(Keyboard.KEY_W)) addPos(0, -moveSpeed * delta);
		if(in.keyDown(Keyboard.KEY_S)) addPos(0, moveSpeed * delta);
		if(in.keyDown(Keyboard.KEY_A)) addPos(-moveSpeed * delta, 0);
		if(in.keyDown(Keyboard.KEY_D)) addPos(moveSpeed * delta, 0);
		rect.setBounds((int) getX(), (int) getY(), 48, 48);
		
		for(int i = 0; i < zombies.size(); i++) {
			if(getBounds().intersects(zombies.get(i).getBounds()) && coolDown == 0) {
				zombies.add(new Zombie(getX(), getY()));
				coolDown = 50;
				score++;
			}
		}
		if(coolDown > 0) coolDown--;
	}
	
	public Rectangle getBounds() {
		return rect;
	}
	
	public int getScore() {
		return score;
	}
}
