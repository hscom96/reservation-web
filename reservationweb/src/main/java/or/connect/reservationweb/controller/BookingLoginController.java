package or.connect.reservationweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter out;
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
		}else{
			
			try {
				out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>alert('로그인 정보를 확인해주세요.'); </script>");
				out.flush(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "bookinglogin";
		}
	}
}
