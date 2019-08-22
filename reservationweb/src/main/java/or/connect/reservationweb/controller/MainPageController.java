package or.connect.reservationweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

	@GetMapping("/mainpage")
	public String mainView() {
		return "mainpage";
	}
}