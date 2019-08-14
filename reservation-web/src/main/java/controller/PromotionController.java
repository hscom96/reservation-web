package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.promotion.PromotionListDto;
import service.PromotionService;

@RestController
public class PromotionController {

	@Autowired
	PromotionService promotionService;
	
	@GetMapping("/api/promotions")
	public PromotionListDto promotions() {
		return promotionService.getPromotionList();
	}
	
}
