package com.xiaour.spring.boot.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xiaour.spring.boot.entity.UserInfo;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.mapping.GetMapping;

public class JestUtil {

	private static JestClient jestClient;
	
	private static String elasticIP = "http://127.0.0.1:9300";
	public static JestClient getClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(elasticIP).readTimeout(60000).connTimeout(60000).multiThreaded(true).build());
		return factory.getObject();
	}
	
	public static void insert() {
		
		
		
	}
	public boolean insertUtil(JestClient client,String index,String type) throws Exception {
		GetMapping build = new GetMapping.Builder().addIndex(index).addType(type).build();
		JestResult execute = client.execute(build);
		return execute.isSucceeded();
		
	}
	
}
