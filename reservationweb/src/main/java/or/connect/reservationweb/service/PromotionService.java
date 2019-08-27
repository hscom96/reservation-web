package or.connect.reservationweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import or.connect.reservationweb.dao.PromotionDao;
import or.connect.reservationweb.dto.promotion.PromotionDto;

@Service
public class PromotionService {
	@Autowired
	PromotionDao promotionDao;

	public List<PromotionDto> getPromotionList() {
		return promotionDao.getPromotionList();
	}
}
