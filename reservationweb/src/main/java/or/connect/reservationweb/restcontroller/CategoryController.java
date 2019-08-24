package or.connect.reservationweb.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.category.CategoryListDto;
import or.connect.reservationweb.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/api/categories")
	public CategoryListDto getCategoryList() {
		return categoryService.getCategoryList();
	}

}
