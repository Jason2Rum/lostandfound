package com.jianghuling.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootApplication
public class LostandfoundApplication {


	public static void main(String[] args) {
		SpringApplication.run(LostandfoundApplication.class, args);

	}

}
