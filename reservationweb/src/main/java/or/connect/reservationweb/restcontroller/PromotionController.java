package or.connect.reservationweb.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.promotion.PromotionDto;
import or.connect.reservationweb.service.PromotionService;

@RestController
public class PromotionController {

	@Autowired
	PromotionService promotionService;

	@GetMapping("/api/promotions")
	public List<PromotionDto> getPromotionList() {
		return promotionService.getPromotionList();
	}

}
