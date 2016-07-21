package nagi.starter.SpringRest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nagi.starter.SpringRest.repository.MainRepository;

@RestController
@RequestMapping("/api/main")
public class MainController {

	@Autowired
	public MainRepository repository;

	@RequestMapping("/status")
	public Map status() {
		return repository.loadStatus();
	}
}
