package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PromotionDao;
import dto.promotion.PromotionsDto;

@Service
public class PromotionService {
	@Autowired
	PromotionDao promotionDao;
	
	public PromotionsDto getPromotionList() {
		return promotionDao.getPromotionList();
	}
}
