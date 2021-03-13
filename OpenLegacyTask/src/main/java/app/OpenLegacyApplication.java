package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.beans.Item;
import app.exceptions.DuplicateException;
import app.services.ItemService;

@SpringBootApplication
public class OpenLegacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenLegacyApplication.class, args);
	}

}
