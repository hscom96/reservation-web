package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.CategoryController;
import controller.MainPageController;
import controller.ProductController;
import controller.PromotionController;

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
	
	@Bean
	PromotionController promotionController() {
		return new PromotionController();
	}
	
	@Bean
	ProductController productController() {
		return new ProductController();
	}
}
