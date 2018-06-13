package com.tesco.springBatchExample.db;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public class CouchBaseConnectionManager {

	private static Bucket rangeserviceBucket;

	 static {
		CouchbaseCluster cb = CouchbaseClusterConnection.getClusterObject();
		rangeserviceBucket = cb.openBucket(CouchbaseBucket.Batch.toString());
	}

	public Bucket getBucket(String bucketName) {
		if (CouchbaseBucket.Batch.toString().equals(bucketName)) {
			return rangeserviceBucket;
		}
		return null;

	}

	public static void couchBaseConnectionDestroy() {
		if (rangeserviceBucket != null) {
			rangeserviceBucket.close();
		}
	}
}
