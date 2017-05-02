package com.stelligent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(
				basePackageClasses = { BananaApplication.class, Jsr310JpaConverters.class }
)
public class BananaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BananaApplication.class, args);
	}
}
