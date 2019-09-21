package or.connect.reservationweb.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String submit(@RequestParam(value = "resrvEmail", required = true) String resrvEmail, HttpSession session, HttpServletResponse response) {
		boolean exitFlag = reservationService.existReservation(resrvEmail);
		Cookie loginCookie;
		int maxTime = 60;
		
		 if ( session.getAttribute("login") !=null ){
	            session.removeAttribute("login");
	        }
		
		if (exitFlag) {
			session.setAttribute("loginInfo", new LoginInfo(resrvEmail));
			session.setMaxInactiveInterval(maxTime);
			loginCookie = new Cookie("login", resrvEmail);
			loginCookie.setMaxAge(maxTime);
			response.addCookie(loginCookie);
			return "redirect:myreservation";
		}else{
			return "bookinglogin";
		}
	}
}
