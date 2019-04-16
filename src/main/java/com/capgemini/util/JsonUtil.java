package com.capgemini.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
private static ObjectMapper mapper;
static {
	mapper=new ObjectMapper();
}
public static String convertJavaToJson(Object object) {
	String jsonResult="";
	try {
		jsonResult=mapper.writeValueAsString(object);
	} catch (JsonGenerationException e) {
		System.out.println("Json generation exception occured --> "+e.getMessage());
		e.printStackTrace();
	} catch (JsonMappingException e) {
		System.out.println("JsonMappingException occured --> "+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
	System.out.println("IOException occured --> "+e.getMessage());
		e.printStackTrace();
	}
	return jsonResult;
	
}
}
