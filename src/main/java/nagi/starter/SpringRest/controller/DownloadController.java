package nagi.starter.SpringRest.controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import nagi.starter.SpringRest.common.bean.LogBean;
import nagi.starter.SpringRest.controller.view.DownloadView;

@Controller
@RequestMapping(value = "/download")
public class DownloadController implements ApplicationContextAware {

	private WebApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = (WebApplicationContext) applicationContext;
	}

	@RequestMapping(value = "/{file_name}", method = RequestMethod.GET)
	public ModelAndView downloadFile(@PathVariable String file_name, HttpServletResponse response) throws FileNotFoundException {

		String dir = context.getServletContext().getRealPath("/WEB-INF/file");
		File file = new File(dir, file_name);

		if (file == null) {
			LogBean.log.info(file_name);
			throw new FileNotFoundException();
			//response.sendError(HttpServletResponse.SC_NOT_FOUND);
			//return null;
		}

		return new ModelAndView(new DownloadView(), "file", file);
	}
}
