package or.connect.reservationweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.product.ProductListDto;
import or.connect.reservationweb.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/api/products")
	public ProductListDto products(@RequestParam(value="categoryId", defaultValue="0") int categoryId, 
			@RequestParam(value="start", defaultValue="0") int start) {
		if(categoryId == 0) return productService.getProductList(start);
		else return productService.getProductList(categoryId,start);
	}
	
}