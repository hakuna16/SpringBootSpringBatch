package com.tesco.springBatchExample.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonMappingException;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;

public class JsonValidator  {

	private JsonTranscoder jsonTranscoder = new JsonTranscoder();

	@Autowired
	private JacksonConvertor jacksonConvertor;

	public <T> T fromJsonDocument(JsonDocument doc, Class<T> type)
			throws JsonMappingException, IOException {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		}
		return jacksonConvertor.fromJson(content.toString(), type);

	}

	public <T> JsonDocument toJsonDocument(T source) throws JsonProcessingException {
		if (source == null) {
			throw new IllegalArgumentException("entity is null");
		}

		String id = "1001";
		if (id == null) {
			throw new IllegalStateException("entity ID is null");
		}

		JsonObject content = null;
		try {
			content = jsonTranscoder.stringToJsonObject(jacksonConvertor.toJson(source));
		} catch (Exception e) {
		}
		return JsonDocument.create(id, content);
	}

	public <T > JsonDocument toJsonDocumentWithCas(T source, long cas) throws JsonProcessingException {
		if (source == null) {
			throw new IllegalArgumentException("source  is null");
		}

		String id = "1001";
		if (id == null) {
			throw new IllegalStateException("entity ID(source ID) is null");
		}

		JsonObject jsonContent = null;
		try {
			jsonContent = jsonTranscoder.stringToJsonObject(jacksonConvertor.toJson(source));
		} catch (Exception e) {
		}
		return JsonDocument.create(id, jsonContent, cas);
	}
}
