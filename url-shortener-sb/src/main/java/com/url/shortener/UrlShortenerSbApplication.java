package com.url.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class UrlShortenerSbApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().filename(".env.prod").load();
		System.setProperty("SPRING_PROFILES_ACTIVE", dotenv.get("SPRING_PROFILES_ACTIVE"));
		SpringApplication.run(UrlShortenerSbApplication.class, args);
	}

}
