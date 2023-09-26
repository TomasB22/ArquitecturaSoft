package cl.arqui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cl.arqui")

public class ArquiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquiApplication.class, args);
	}

}
