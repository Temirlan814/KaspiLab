package project.homework5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Homework5Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework5Application.class, args);
	}

}
