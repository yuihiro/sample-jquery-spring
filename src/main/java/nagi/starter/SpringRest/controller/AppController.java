package nagi.starter.SpringRest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	@Value("${app.message}")
	private String message;

	@RequestMapping(value = "/hi/{in}", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String echo(@PathVariable(value = "in") final String in) {
		return message + " " + in;
	}

	@ModelAttribute("common_data")
	public String commonData() {
		List<String> log_types = new ArrayList<String>();
		log_types.add("하하");
		log_types.add("호호");
		log_types.add("히히");

		Map data = new HashMap<>();
		data.put("log_types", log_types);
		return "data";
	}
}
