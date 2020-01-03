package or.connect.reservationweb.controller;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import or.connect.reservationweb.service.ReservationService;
import or.connect.reservationweb.vo.LoginInfo;

@Controller
@RequestMapping("/bookinglogin")
public class BookingLoginController {
	@Autowired
	ReservationService reservationService;

	@GetMapping
	public String showView() {
		return "bookinglogin";
	}

	@PostMapping
	public String submit(@RequestParam(value = "resrvEmail", required = true) String resrvEmail, HttpSession session, HttpServletResponse response, Model model) {
		boolean exitFlag = reservationService.existReservation(resrvEmail);
		Cookie loginCookie;
		int maxInactiveTime = 1800;
		
		 if ( session.getAttribute("login") !=null ){
	            session.removeAttribute("login");
	        }
		
		if (exitFlag) {
			session.setAttribute("loginInfo", new LoginInfo(resrvEmail));
			session.setMaxInactiveInterval(maxInactiveTime);
			loginCookie = new Cookie("loginCookie", resrvEmail);
			response.addCookie(loginCookie);
			return "redirect:myreservation";
		}else {
			model.addAttribute("rsrvNotExistError","true");
			return "bookinglogin";
		}
	}
}
