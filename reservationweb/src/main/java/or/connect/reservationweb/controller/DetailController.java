package or.connect.reservationweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {

	@GetMapping("/detail")
	public String ShowDetailView(@RequestParam(value = "id", required = true) int displayId, Model model) {
		return "detail";
	}
}
