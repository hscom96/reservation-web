package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDao;
import dto.product.ProductListDto;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	public ProductListDto getProductList(int categoryId, int start) {
		return productDao.getProductList(categoryId, start);
	}
}
