package com.navigus.quizmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.navigus.quizmaker")
public class QuizMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMakerApplication.class, args);
	}
}
