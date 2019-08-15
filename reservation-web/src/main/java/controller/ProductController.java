package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.product.ProductListDto;
import service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/api/products")
	public ProductListDto promotions(@RequestParam(value="categoryId", defaultValue="0") int categoryId, 
			@RequestParam(value="start", defaultValue="0") int start) {
		return productService.getProductList(categoryId,start);
	}
	
}