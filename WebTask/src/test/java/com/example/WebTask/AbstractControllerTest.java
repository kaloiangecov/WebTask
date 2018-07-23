//package com.example.WebTask;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
//public abstract class AbstractControllerTest {
//
//    protected String mapToJson(Object object) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(object);
//    }
//
//    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException,
//        JsonParseException, JsonMappingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(json, clazz);
//    }
//}
