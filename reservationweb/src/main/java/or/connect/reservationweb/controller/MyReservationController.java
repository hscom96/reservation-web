package or.connect.reservationweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/myreservation")
public class MyReservationController {
	@GetMapping
	public String showView() {
			return "myreservation";
	}
}
