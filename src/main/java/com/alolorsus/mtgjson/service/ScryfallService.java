package com.alolorsus.mtgjson.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings({"rawtypes","unchecked"})
public class ScryfallService {
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	private static HttpHeaders headers = new HttpHeaders();

	public static Map<String, String> getImagenesCarta (String scryfallId) {
		
		String url = "https://api.scryfall.com/cards/";
		
		RequestEntity<?> request = RequestEntity.get(url + scryfallId).headers(headers).build();
		
		ResponseEntity<Map> response = restTemplate.exchange(request, Map.class);
		
		Map imageUrls = (Map) response.getBody().get("image_uris");
		
		return imageUrls;
		
	}
}
