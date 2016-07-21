package nagi.starter.SpringRest.study;

import java.text.SimpleDateFormat;

public class SimpleProcessor {

	//@Scheduled(fixedRate = 1000)
	public void task() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		System.out.println("SimpleProcessor " + sdf.format(time));
	}

}
