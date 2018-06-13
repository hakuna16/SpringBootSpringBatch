package com.tesco.springBatchExample.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class CustomItemWriter<User> implements ItemWriter<User> {

	@Autowired
	Bucket bucket;

	@Override
	public void write(List<? extends User> items) throws Exception {

			JsonDocument doc = JsonDocument.create("DocumentId", JsonObject.create().put("user", items));
			bucket.upsert(doc);
	}
}
