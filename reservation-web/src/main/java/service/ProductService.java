package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProductDao;
import dto.product.ProductListDto;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	//���� ī�װ��� ��ǰ ��� ��ȯ
	@Transactional
	public ProductListDto getProductList(int categoryId, int start) {
		ProductListDto productListDto = new ProductListDto();
		
		productListDto.setProductList(productDao.getProductList(categoryId, start));
		productListDto.setTotalCount(productDao.getTotalCount(categoryId));
		
		return productListDto;
	}
	
	//��ü ī�װ��� ��ǰ ��� ��ȯ
	@Transactional
	public ProductListDto getProductList(int start) {
		ProductListDto productListDto = new ProductListDto();
		
		productListDto.setProductList(productDao.getProductList(start));
		productListDto.setTotalCount(productDao.getTotalCount());
		
		return productListDto;
	}
	
}
