package com.stringtojson.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stringtojson.service.StringToJsonService;

@RestController
@RequestMapping("/api")
public class StringToJsonController {

	@Autowired
	private StringToJsonService stringToJsonService;
	
	@PostMapping("/convertToJson")
	public ResponseEntity<?> convertToJson(@RequestBody String jsonString) throws JsonParseException, JsonMappingException, IOException{

		return new ResponseEntity<>(stringToJsonService.convertToJson(jsonString),HttpStatus.OK);
	}
}
