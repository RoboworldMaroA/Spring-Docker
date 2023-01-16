package com.marekaugustyn.springThreeDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringThreeDockerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringThreeDockerApplication.class, args);
	}

	@GetMapping("/")
	public GreetResponse hi(){

		return new GreetResponse(
				"Hi Marek",
				List.of("aa","aa"),
				new Person("Maro",22));
	}

	record Person(String name, int age){}

	record GreetResponse(
			String Greet,
			List<String> favoriteProgramminLanguages,
			Person person
	){ }

}
