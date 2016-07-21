package nagi.starter.SpringRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nagi.starter.SpringRest.service.SensorService;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

	@Autowired
	public SensorService service;

	@RequestMapping("/sensor_list")
	public List selectSensorList() {
		return service.selectSensorList(-1);
	}

	@RequestMapping("/firmware_list")
	public List list() {
		return service.selectFirmwareList(-1);
	}
}
