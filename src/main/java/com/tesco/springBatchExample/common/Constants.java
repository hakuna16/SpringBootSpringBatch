package com.tesco.springBatchExample.common;

public interface Constants {
	
	//couchbase constants
		String NODES_KEY = "couchbase.nodes";
		String NODES_DEFAULT_VALUE = "localhost";
		String VIEW_END_POINTS="couchbase.connection.viewEndPoints";
		String QUERY_END_POINTS="couchbase.connection.queryEndpoints";
		String IO_POOL_SIZE="couchbase.connection.ioPoolSize";
		String COMPUTATION_POOL_SIZE="couchbase.connection.computationPoolSize";
		String TIME_OUT="couchbase.connection.connectTimeout";

}
