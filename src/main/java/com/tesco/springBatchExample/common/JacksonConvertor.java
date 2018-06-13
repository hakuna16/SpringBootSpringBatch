package com.tesco.springBatchExample.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonParseException;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.DeserializationFeature;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonMappingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.SerializationFeature;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.type.TypeFactory;

public class JacksonConvertor {

	private final ObjectMapper objectMapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.setSerializationInclusion(Include.NON_NULL).enable(SerializationFeature.INDENT_OUTPUT)
			.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));

	public <T> T fromJson(String source, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(source, type);
	}

	public <T> String toJson(T source) throws JsonProcessingException {
		return objectMapper.writeValueAsString(source);
	}

	public <T> List<T> fromJsonToList(String jsonString, Class<? extends T> type)
			throws JsonMappingException, IOException {

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		return objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, type));

	}
}
