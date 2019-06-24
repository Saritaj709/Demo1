package com.stringtojson.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;

@Repository
public interface StringToJsonRepository extends MongoRepository<JsonObject,String>{

}
