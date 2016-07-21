package nagi.starter.SpringRest.common.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import nagi.starter.SpringRest.common.bean.LogBean;
import nagi.starter.SpringRest.common.exception.ResourceNotFoundException;

//@Controller
public class ExceptionHandlingControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
		System.out.println("오메");
		LogBean.log.error("Exception : " + request.getRequestURL() + " raised " + ex.getMessage());

		if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
			throw ex;

		response.setHeader("Refresh", "10; URL=/");

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("/error/error");
		return mav;
	}

	@ModelAttribute("app_info")
	public Map getAppInfo(HttpServletRequest request) {
		String session_id = request.getSession().getId();
		String referer = request.getHeader("Referer");
		Map app_info = new HashMap<>();

		if (StringUtils.hasText(referer) == false) {
			referer = "/";
		}

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String originalURL = urlPathHelper.getOriginatingRequestUri(request);

		app_info.put("session_id", session_id);
		app_info.put("referer", referer);
		return app_info;
	}
}