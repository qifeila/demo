package goct.query.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PublicQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicQueryApplication.class, args);
	}

}
