package or.connect.reservationweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import or.connect.reservationweb.vo.LoginInfo;

@Controller
@RequestMapping("/myreservation")
public class MyReservationController {
	@GetMapping
	public String showView() {
			return "myreservation";
	}
}
