package com.stringtojson.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stringtojson.repository.StringToJsonRepository;

@Service
public class StringToJsonServiceImpl implements StringToJsonService {

	@Autowired
	private StringToJsonRepository stringToJsonRepo;

	@Autowired
	private MongoTemplate mongoTemp;

	@Override
	public Object convertToJson(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		// String jsonString = "{ \"name\":\"Ramesh\",\"url\":\"www.capgemini.com\",\"contact\":\"4576932146\",\"address\":\"Bangalore\" }";
		
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
		
		System.out.println(jsonObject);
				// To save in MongoRepository
		stringToJsonRepo.save(jsonObject);
		// To save in MongoTemplate
		mongoTemp.insert(jsonObject, "mongoObject");

		return jsonObject.toString();
	}

}
