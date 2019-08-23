package or.connect.reservationweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.product.ProductListDto;
import or.connect.reservationweb.dto.productInfo.ProductDisplayDto;
import or.connect.reservationweb.service.ProductDisplayService;
import or.connect.reservationweb.service.ProductListService;

@RestController
public class ProductController {

	@Autowired
	ProductListService productListService;
	@Autowired
	ProductDisplayService productDisplayService;
	
	@GetMapping("/api/products")
	public ProductListDto getProductList(@RequestParam(value="categoryId", defaultValue="0") int categoryId, 
			@RequestParam(value="start", defaultValue="0") int start) {
		return productListService.getProductList(categoryId,start);
	}

	@GetMapping("/api/products/{displayInfoId}")
	public ProductDisplayDto getProductinfo(@PathVariable int displayInfoId) {
		return productDisplayService.getProductDisplay(displayInfoId);
	}
}