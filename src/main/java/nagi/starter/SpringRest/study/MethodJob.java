package nagi.starter.SpringRest.study;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nagi.starter.SpringRest.service.SensorService;

@Service("methodJob")
public class MethodJob {

	@Autowired
	public SensorService game_service;

	protected void task() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		System.out.println("MethodJob " + sdf.format(time));
		//System.out.println(game_service.list(-1));
	}

}
