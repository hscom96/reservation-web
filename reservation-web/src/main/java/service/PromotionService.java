package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PromotionDao;
import dto.promotion.PromotionListDto;

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
