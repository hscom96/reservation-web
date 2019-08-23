package or.connect.reservationweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import or.connect.reservationweb.dao.ProductDao;
import or.connect.reservationweb.dto.product.ProductListDto;

@Service
public class ProductListService {
	@Autowired
	private ProductDao productDao;

	public ProductListDto getProductList(int categoryId, int start) {
		ProductListDto productListDto = new ProductListDto();
		int totalCount = productDao.getTotalCount(categoryId);

		if(categoryId == 0) {
			productListDto.setItems(productDao.getProductList(start));
			productListDto.setTotalCount(productDao.getTotalCount());
		}else {
			productListDto.setItems(productDao.getProductList(categoryId, start));
			productListDto.setTotalCount(totalCount);
		}
		
		return productListDto;
	}
	
}
