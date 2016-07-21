package nagi.starter.SpringRest.common.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import nagi.starter.SpringRest.Application;

@Component
public class LogBean {

	public static final Log log = LogFactory.getLog(Application.class);
}
