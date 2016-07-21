package nagi.starter.SpringRest.common.bean;

import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class ResourceBean implements ServletContextAware {

	public static Boolean debug_mode = false;
	public static String main_page = "/main";

	public ResourceBean() {
		LogBean.log.info(MethodHandles.lookup().lookupClass());
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		//servletContext.setAttribute("myKey", value);

	}
}
