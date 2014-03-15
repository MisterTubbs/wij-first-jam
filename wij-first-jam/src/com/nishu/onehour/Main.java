package com.nishu.onehour;

import java.io.IOException;
import java.util.ArrayList;

import com.nishu.onehour.entities.Player;
import com.nishu.onehour.entities.Zombie;
import com.nishu.onehour.utilities.Constants;
import com.teama.merc.exc.MERCuryException;
import com.teama.merc.fmwk.Core;
import com.teama.merc.fmwk.Runner;
import com.teama.merc.gfx.Color;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.res.ResourceManager;
import com.teama.merc.spl.SplashScreen;

public class Main extends Core {

	public enum State {
		PLAY, DEATH;
	}

	private Runner rnr = Runner.getInstance();
	private State currentState = State.PLAY;

	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	private Player player;

	private int time = 0;

	public Main() {
		super("Mate 'Em");
		rnr.init(this, 1280, 720, false, false);
		rnr.run();
	}

	@Override
	public void cleanup(ResourceManager RM) throws IOException, MERCuryException {
	}

	@Override
	public void init(ResourceManager RM) throws IOException, MERCuryException {
		rnr.addSplashScreen(SplashScreen.getMERCuryDefault());

		for (int i = 0; i < Constants.rand.nextInt(50) + 25; i++) {
			int width = Constants.rand.nextInt(Constants.width);
			int height = Constants.rand.nextInt(Constants.height);
			if (width < 48)
				width = 48;
			if (height < 48)
				height = 48;
			if (width > Constants.width - 48)
				width = Constants.width - 48;
			if (height > Constants.height - 48)
				height = Constants.height - 48;

			zombies.add(new Zombie(Constants.rand.nextInt(Constants.width), Constants.rand.nextInt(Constants.height)));
		}

		player = new Player(Constants.width / 2, Constants.height / 2, rnr, zombies);
	}

	@Override
	public void render(Graphics g) throws MERCuryException {
		if (currentState == State.PLAY) {
			if (time % (Constants.rand.nextInt(5) + 1) == 0) {
				g.setBackground(new Color(Constants.rand.nextInt(255), Constants.rand.nextInt(255), Constants.rand.nextInt(255)));
			}
			for (Zombie zom : zombies) {
				zom.render(g);
			}
			player.render(g);
			g.drawString(10, Constants.height - 30, "Score: " + player.getScore());
		} else if (currentState == State.DEATH) {
			g.drawString(Constants.width / 2 - 175, Constants.height / 2, "You have mated with: " + player.getScore() + " zombies! Good job sport ;)");
		}
	}

	@Override
	public void update(float delta) throws MERCuryException {
		if (currentState == State.PLAY) {
			player.update(delta);
			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).update(delta);
				if (zombies.get(i).isDead())
					zombies.remove(i);
			}
			if (zombies.size() <= 0) {
				currentState = State.DEATH;
			}
			time++;
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
