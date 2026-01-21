package project.HomeWork9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class HomeWork9Application {

	public static void main(String[] args) {
		SpringApplication.run(HomeWork9Application.class, args);
	}

}
