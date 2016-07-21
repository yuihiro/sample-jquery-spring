package nagi.starter.SpringRest.common.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import nagi.starter.SpringRest.common.bean.LogBean;
import nagi.starter.SpringRest.common.bean.ResourceBean;

@Controller
public class CustomErrorController implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = PATH)
	ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
		Map info = getErrorAttributes(request, ResourceBean.debug_mode);
		LogBean.log.info("에러 : " + response.getStatus() + "/" + request.getRequestURL() + "/" + info.get("error"));
		ModelAndView model = new ModelAndView();
		model.addObject("status", response.getStatus());
		model.addObject("message", info.get("error"));
		if (response.getStatus() == 404) {
			model.setViewName("/error/404");
		} else {
			model.setViewName("/error/error");
		}
		return model;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}
}
