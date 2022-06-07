package com.neighborpil.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighborpil.learnspringframework.game.GamingConsole;

@Component
public class GameRunner {

	@Autowired
	private GamingConsole game;


	public GameRunner(GamingConsole game) {
		this.game = game;
	}


	public void runGame() {
		game.up();
		game.down();
		game.left();
		game.right();
	}
}
