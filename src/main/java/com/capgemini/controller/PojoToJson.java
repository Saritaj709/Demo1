package com.capgemini.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Employee;
import com.capgemini.util.JsonSchemaGenerator;
import com.capgemini.util.JsonUtil;

@RestController
@RequestMapping("/api")
public class PojoToJson {
	
	@PostMapping("/getJson")
	public ResponseEntity<?>pojoToJson(@RequestBody Employee employee){
		String jsonString=JsonUtil.convertJavaToJson(employee);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString,HttpStatus.OK);
	}
	
	@GetMapping("/getJsonSchema")
	public ResponseEntity<?>pojoToJsonSchema() throws IOException{
		String jsonString=JsonSchemaGenerator.getJsonSchema(Employee.class);
		return new ResponseEntity<>(jsonString,HttpStatus.OK);
	}
	
}
