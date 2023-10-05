package eder.desenvolve.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiecomerceApplication {
	public String PORT = System.getenv("PORT");
	public static void main(String[] args) {

		SpringApplication.run(ApiecomerceApplication.class, args);

	}

}
