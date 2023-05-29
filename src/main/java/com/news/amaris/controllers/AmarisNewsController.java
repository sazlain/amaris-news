package com.news.amaris.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AmarisNewsController {

	@GetMapping("healthcheck")
	public ResponseEntity<String> healthcheck(){
		return new ResponseEntity<String>("Status Success", HttpStatus.OK);
	}
}
