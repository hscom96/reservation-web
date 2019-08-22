package or.connect.reservationweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import or.connect.reservationweb.dao.PromotionDao;
import or.connect.reservationweb.dto.promotion.PromotionListDto;

@Service
public class PromotionService {
	@Autowired
	PromotionDao promotionDao;

	public PromotionListDto getPromotionList() {

		PromotionListDto promotionListDto = new PromotionListDto();
		promotionListDto.setItems(promotionDao.getPromotionList());

		return promotionListDto;
	}
}
