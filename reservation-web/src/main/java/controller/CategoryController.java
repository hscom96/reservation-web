package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.category.CategoryListDto;
import service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/api/categories")
	public CategoryListDto categories() {
		return categoryService.getCategoryList();
	}
	
}
