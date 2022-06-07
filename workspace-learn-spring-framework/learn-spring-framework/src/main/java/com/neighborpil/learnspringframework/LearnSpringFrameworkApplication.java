package com.neighborpil.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.neighborpil.learnspringframework.game.GameRunner;

@SpringBootApplication
@ComponentScan("com.neighborpil.learnspringframework")
public class LearnSpringFrameworkApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args);
		// context에서 bean들을 등록한다
		// MarioGame, GameRunner
		GameRunner runner = context.getBean(GameRunner.class);
		
//		GamingConsole game = new MarioGame();
//		GamingConsole game = new SuperControlGame();
//		GameRunner runner = new GameRunner(game);
		
		runner.runGame();
	}

}
