package or.connect.reservationweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import or.connect.reservationweb.dao.ProductDisplayDao;
import or.connect.reservationweb.dto.productInfo.ProductDisplayDto;

@Service
public class ProductDisplayService {
	@Autowired
	ProductDisplayDao productDisplayDao;
	
	public ProductDisplayDto getProductDisplay(int displayInfoId) {
		ProductDisplayDto productDisplayDto = new ProductDisplayDto();
		
		productDisplayDto.setAverageScore(productDisplayDao.getAverageScore(displayInfoId));
		productDisplayDto.setComments(productDisplayDao.getComment(displayInfoId));
		productDisplayDto.setDisplayInfo(productDisplayDao.getDisplayInfo(displayInfoId));
		productDisplayDto.setProductImages(productDisplayDao.getProductImage(displayInfoId));
		productDisplayDto.setProductPrices(productDisplayDao.getProductPrice(displayInfoId));
		productDisplayDto.setDisplayInfoImage(productDisplayDao.getDisplayInfoImage(displayInfoId));
		
		return productDisplayDto;
	}
}
