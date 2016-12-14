package cht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class IntellCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntellCenterApplication.class, args);
	}
}
