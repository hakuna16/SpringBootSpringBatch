package com.tesco.springBatchExample.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.couchbase.client.core.env.DefaultCoreEnvironment;
import com.couchbase.client.core.env.QueryServiceConfig;
import com.couchbase.client.core.env.ViewServiceConfig;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.tesco.springBatchExample.common.Constants;

public class CouchbaseClusterConnection {
	
	private static int DEFAULT_VIEW_ENDPOINT = DefaultCoreEnvironment.VIEW_ENDPOINTS;
	private static int DEFAULT_QUERY_ENDPOINT = DefaultCoreEnvironment.QUERY_ENDPOINTS;
	
	final static Properties props;
	private static String[] nodes = {};
	static InputStream input;
	private static CouchbaseCluster couchbaseCluster;

	private CouchbaseClusterConnection() {
	}

	static {
		props = new Properties();
		input = CouchbaseClusterConnection.class.getClassLoader()
				.getResourceAsStream("db.properties");
		if (input == null) {
			System.out.println("Configuration File Not Found:" + "db.properties");
		} else {
			try {
				props.load(input);
			} catch (IOException e) {
				System.out.println("Exception while loading the Configuration File:" + e);
			}
		}
		nodes = props.getProperty("couchbase.nodes", "localhost").split(",");

	}

	public static CouchbaseCluster getClusterObject() {

		if (couchbaseCluster != null) {
			return couchbaseCluster;
		}

		int maxViewEndPoints = Integer.parseInt(props.getProperty(Constants.VIEW_END_POINTS));
		int maxQueryEndPoints = Integer.parseInt(props.getProperty(Constants.QUERY_END_POINTS));
		
		// Set the maximum end point value to default, if it is lesser than the default count.
		maxViewEndPoints = Math.max(DEFAULT_VIEW_ENDPOINT, maxViewEndPoints);
		maxQueryEndPoints = Math.max(DEFAULT_QUERY_ENDPOINT, maxQueryEndPoints);

		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
				.viewServiceConfig(ViewServiceConfig.create(DEFAULT_VIEW_ENDPOINT, maxViewEndPoints))
				.queryServiceConfig(QueryServiceConfig.create(DEFAULT_QUERY_ENDPOINT, maxQueryEndPoints))
				.ioPoolSize(Integer.parseInt(props.getProperty(Constants.IO_POOL_SIZE)))
				.computationPoolSize(Integer.parseInt(props.getProperty(Constants.COMPUTATION_POOL_SIZE)))
				.connectTimeout(Integer.parseInt(props.getProperty(Constants.TIME_OUT))).build();
		
		CouchbaseCluster newCouchbaseCluster = CouchbaseCluster.create(env, nodes);
		newCouchbaseCluster.authenticate("Batch", "password");
		
		couchbaseCluster = newCouchbaseCluster;
		
		return couchbaseCluster;
	}

}
