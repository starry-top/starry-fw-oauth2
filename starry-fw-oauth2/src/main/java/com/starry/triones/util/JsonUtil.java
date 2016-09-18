package com.starry.triones.util;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static <T> T fromJson(String json, Class<T> clazz) {
		T obj = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			obj = mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return obj;
	}

	public static <T> String toJson(T object) {
		String json = "";

		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> readJsonToMap(String json) {
		Map<String, Object> map = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(json, Map.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return map;
	}

}
