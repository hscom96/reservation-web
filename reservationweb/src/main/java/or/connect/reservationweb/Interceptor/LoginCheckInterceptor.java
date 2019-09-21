package or.connect.reservationweb.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Object login = session.getAttribute("loginInfo");
			if(login != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/bookinglogin");
		return false;
	}
}
