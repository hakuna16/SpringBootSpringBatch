package com.tesco.springBatchExample.db;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.JsonLongDocument;
import com.tesco.springBatchExample.common.JsonValidator;

public class CouchBaseRepository {

	@Autowired
	private JsonValidator jsonValidator;

	private Bucket bucket;

	public <T> T create(T entity, Bucket bucket, Class<? extends T> type) throws IOException {
		if (entity != null) {
			String id = getNextId(type, 1, 100);
			entity = (T) id;
		}
		JsonDocument docIn = jsonValidator.toJsonDocument(entity);
		JsonDocument docOut = bucket.insert(docIn);
		return jsonValidator.fromJsonDocument(docOut, type);
	}

	private <T> String getNextId(Class<T> type, long incr, long init) {
		String name = type.getSimpleName().toLowerCase();
		JsonLongDocument document = bucket.counter("counter::" + name, incr, init);
		return name + "::" + document.content().toString();
	}
}
