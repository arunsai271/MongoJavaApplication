package com.tul.utility.Mongoutility;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

@Configuration
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
public class BeanCofiguration {

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("10.10.177.75");
	}

}
