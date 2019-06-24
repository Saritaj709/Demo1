package com.stringtojson.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface StringToJsonService {
 public Object convertToJson(String jsonString) throws JsonParseException, JsonMappingException, IOException;
}
