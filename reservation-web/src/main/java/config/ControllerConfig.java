package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.CategoryController;
import controller.MainPageController;

@Configuration
public class ControllerConfig {
	@Bean
	MainPageController mainPageController() {
		return new MainPageController();
	}
	
	@Bean
	CategoryController categoryController() {
		return new CategoryController();
	}
}
