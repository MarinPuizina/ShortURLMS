package com.marin.urlshortenerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@EnableDiscoveryClient
public class UrlShortenerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerMsApplication.class, args);
	}

	@Bean
	public Jedis jedis() {
		return new Jedis();
	}

}
