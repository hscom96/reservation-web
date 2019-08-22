package or.connect.reservationweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.promotion.PromotionListDto;
import or.connect.reservationweb.service.PromotionService;

@RestController
public class PromotionController {

	@Autowired
	PromotionService promotionService;

	@GetMapping("/api/promotions")
	public PromotionListDto getPromotionList() {
		return promotionService.getPromotionList();
	}

}
