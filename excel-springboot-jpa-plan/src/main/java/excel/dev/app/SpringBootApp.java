package excel.dev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "excel.dev.*")
@EntityScan(basePackages = "excel.dev.entity")
@ComponentScan(basePackages = { "excel.dev.*" })
@EnableJpaRepositories(basePackages = { "excel.dev.repository" })
public class SpringBootApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}
