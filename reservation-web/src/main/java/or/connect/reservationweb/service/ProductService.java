package or.connect.reservationweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import or.connect.reservationweb.dao.ProductDao;
import or.connect.reservationweb.dto.product.ProductListDto;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	// 지정한 categoryId의 상품목록 반환
	@Transactional
	public ProductListDto getProductList(int categoryId, int start) {
		ProductListDto productListDto = new ProductListDto();
		int totalCount = productDao.getTotalCount(categoryId);

		productListDto.setItems(productDao.getProductList(categoryId, start));
		productListDto.setTotalCount(totalCount);

		return productListDto;
	}

	// 전체 상품목록 반환
	@Transactional
	public ProductListDto getProductList(int start) {
		ProductListDto productListDto = new ProductListDto();

		productListDto.setItems(productDao.getProductList(start));
		productListDto.setTotalCount(productDao.getTotalCount());

		return productListDto;
	}

}
