package com.documents.web;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DetailService {

	private static final Logger log = LoggerFactory.getLogger(DetailService.class);
	
	private final RestTemplate restTemplate;
	
	public DetailService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
	/*
	@Async
	public Future<ResultInput> getProfileAsynch(String ctn) throws InterruptedException {
		log.info("getProfileAsynch(" + ctn + ")");
		if (ctn == null || ctn.isEmpty())
			throw new IllegalArgumentException();
		
		String url = String.format("https://randomuser.me/api/?phone=%s&inc=name,email", ctn);
		ResultInput resultInput = restTemplate.getForObject(url, ResultInput.class);
		
		return new AsyncResult<>(resultInput);
	}
	*/
}