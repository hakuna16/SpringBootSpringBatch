package com.tesco.springBatchExample.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.client.java.Bucket;
import com.tesco.springBatchExample.db.CouchBaseConnectionManager;
import com.tesco.springBatchExample.db.CouchBaseRepository;
import com.tesco.springBatchExample.db.CouchbaseBucket;

public class CustomItemWriter<User> implements ItemWriter<User> {

	@Autowired
	CouchBaseConnectionManager couchBaseConnectionManager;

	@Autowired
	CouchBaseRepository couchBaseRepository;

	@Override
	public void write(List<? extends User> items) throws Exception {

		Bucket bucket = couchBaseConnectionManager.getBucket(CouchbaseBucket.Batch.toString());

		for (User user : items) {
			couchBaseRepository.create(user, bucket, Class.class);
		}

	}
}
