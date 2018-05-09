package org.fullstack5.pacmanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PacmanApiApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PacmanApiApplication.class).headless(false).run(args);
	}
}
