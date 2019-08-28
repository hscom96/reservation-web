package or.connect.reservationweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import or.connect.reservationweb.dao.ProductDisplayDao;
import or.connect.reservationweb.dao.ProductImageDao;
import or.connect.reservationweb.dao.ProductPriceDao;
import or.connect.reservationweb.dao.ReservationCommentDao;
import or.connect.reservationweb.dao.ReservationCommentImageDao;
import or.connect.reservationweb.dto.comment.CommentDto;
import or.connect.reservationweb.dto.comment.CommentImageDto;
import or.connect.reservationweb.dto.productInfo.ProductDisplayDto;

@Service
public class ProductDisplayService {
	@Autowired
	ProductDisplayDao productDisplayDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	ProductPriceDao productPriceDao;
	@Autowired
	ReservationCommentDao reservationCommentDao;
	@Autowired
	ReservationCommentImageDao reservationCommentImageDao;

	public ProductDisplayDto getProductDisplay(int displayInfoId) {
		ProductDisplayDto productDisplayDto = new ProductDisplayDto();

		productDisplayDto.setAverageScore(reservationCommentDao.getAverageScore(displayInfoId));
		productDisplayDto.setDisplayInfo(productDisplayDao.getDisplayInfo(displayInfoId));
		productDisplayDto.setProductImages(productImageDao.getProductImage(displayInfoId));
		productDisplayDto.setProductPrices(productPriceDao.getProductPrice(displayInfoId));
		productDisplayDto.setDisplayInfoImage(productImageDao.getDisplayInfoImage(displayInfoId));
		
		List<CommentDto> commentList = reservationCommentDao.getComment(displayInfoId);
		commentList.forEach(item -> {
			List<CommentImageDto> commentImageList = reservationCommentImageDao.getCommentImage(item.getCommentId());
			item.setCommentImages(commentImageList);
		});
		productDisplayDto.setComments(commentList);
		return productDisplayDto;
	}
}
