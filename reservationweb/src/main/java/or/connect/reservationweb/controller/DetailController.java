package or.connect.reservationweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {
	
	@GetMapping("/detail")
	public String detailView(@RequestParam(value="id", required=true) int displayId, Model model) {
		model.addAttribute("displayId",displayId);
		return "detail";
	}
}
