package nagi.starter.SpringRest.study;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

	@MessageMapping("/echo")
	@SendTo("/subscribe/echo")
	public Map test(Map input) throws InterruptedException {
		//Thread.sleep(2000);
		Map result = new HashMap<>();
		result.put("sum", Integer.parseInt(input.get("num1").toString()) + Integer.parseInt(input.get("num2").toString()));
		return result;
	}

	@MessageMapping("/message")
	@SendToUser
	public Map message(Map input) throws InterruptedException {
		//Thread.sleep(2000);
		Map result = new HashMap<>();
		result.put("sum", Integer.parseInt(input.get("num1").toString()) + Integer.parseInt(input.get("num2").toString()));
		return result;
	}
}
