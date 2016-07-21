package nagi.starter.SpringRest.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import nagi.starter.SpringRest.common.bean.LogBean;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LogBean.log.info("preHandle : " + request.getRequestURI());

		if (request.getSession().getAttribute("auth_info") == null) {
			savePrevURI(request);
		}

		return super.preHandle(request, response, handler);

		/*
		NoAuthCheck auth = ((HandlerMethod) handler).getMethodAnnotation(NoAuthCheck.class);
		HttpSession session = request.getSession(false);
		
		if (auth != null) {
			return super.preHandle(request, response, handler);
		} else if (session != null) {
			Object authInfo = session.getAttribute("auth_info");
			if (authInfo != null) {
				return super.preHandle(request, response, handler);
			}
		}
		
		response.sendRedirect("/login");
		return false;
		*/
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	public void savePrevURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if (query == null) {
			query = "";
		} else {
			query = "?" + query;
		}

		if (request.getMethod().equals("GET")) {
			request.getSession().setAttribute("prev_uri", uri + query);
		}
	}
}
