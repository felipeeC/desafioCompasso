package br.com.compasso.lambda.desafioCompasso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DesafioCompassoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCompassoApplication.class, args);
	}

}
