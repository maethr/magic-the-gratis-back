package com.alolorsus.collector.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.collector.service.CartaService;

@RestController
@RequestMapping("/album-collector/api")
@CrossOrigin(origins = { "*" })
public class CartaController {
	
	@Autowired
	private CartaService cartaService;
	
	@GetMapping("/album/carta/{nombre}")
	public List<?> listaCartas(@PathVariable String nombre) {
		return cartaService.getTituloCarta(nombre);
	}

	@GetMapping("/album/carta/{nombre}/img")
	public Map<?, ?> imagenCarta(@PathVariable String nombre) {
		return cartaService.getImagenCarta(nombre);
	}
}
