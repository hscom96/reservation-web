package or.connect.reservationweb.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import or.connect.reservationweb.vo.LoginInfo;

public class CorrectUserCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession(false);
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		String loginEmail = getCookieValue(cookies,"loginCookie");
		
		if(loginEmail == null) {
			response.sendRedirect(request.getContextPath() + "/bookinglogin");
			return false;
		}
		
		if (loginEmail.equals(loginInfo.getEmail())) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/bookinglogin");
			return false;
		}
	}

	public String getCookieValue(Cookie[] cookies, String name) {
		String cookieValue = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				cookieValue = cookies[i].getValue();
			}
		}
		return cookieValue;
	}
}
