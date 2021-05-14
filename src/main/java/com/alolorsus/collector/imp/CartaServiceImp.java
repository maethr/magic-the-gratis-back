package com.alolorsus.collector.imp;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alolorsus.collector.service.CartaService;
import com.alolorsus.mtgjson.entity.DatosCarta;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CartaServiceImp implements CartaService {

	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Override
	public List<String> getTituloCarta(String nombre) {
		String url = "https://api.scryfall.com/cards/autocomplete?q=";

		RequestEntity<?> request = RequestEntity.get(url + nombre).headers(headers).build();
		ResponseEntity<Map> response = restTemplate.exchange(request, Map.class);
		List<String> output = (List<String>) response.getBody().get("data");
		return output;
	}

	@Override
	public Map<String, String> getImagenCarta(String nombreCompleto) {
		String url = "https://api.scryfall.com/cards/named?exact=";
		
		RequestEntity<?> request = RequestEntity.get(url + nombreCompleto).headers(headers).build();
		ResponseEntity<Map> response = restTemplate.exchange(request, Map.class);
		
		Map imageUrls = (Map) response.getBody().get("image_uris");
		return imageUrls;
	}

	@Override
	public DatosCarta getDatosCarta(String nombreCompleto) {
		String url = "https://api.scryfall.com/cards/named?exact=";
		
		RequestEntity<?> request = RequestEntity.get(url + nombreCompleto).headers(headers).build();
		ResponseEntity<Map> response = restTemplate.exchange(request, Map.class);
		
		Map imageUrls = (Map) response.getBody().get("image_uris");
		DatosCarta datosCarta = new DatosCarta();
		
		datosCarta.setName(nombreCompleto);
		datosCarta.setImageUrls((Map) response.getBody().get("image_uris"));
		datosCarta.setManaCost((String) response.getBody().get("mana_cost"));
		return null;
		
	}

	
}
