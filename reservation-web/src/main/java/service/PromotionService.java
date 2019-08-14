package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PromotionDao;
import dto.promotion.PromotionListDto;

@Service
public class PromotionService {
	@Autowired
	PromotionDao promotionDao;
	
	public PromotionListDto getPromotionList() {
		return promotionDao.getPromotionList();
	}
}
