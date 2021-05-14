package com.alolorsus.mtgjson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.mtgjson.entity.DatosCarta;
import com.alolorsus.mtgjson.service.DatosCartaService;

@RestController
@RequestMapping("/album-collector/api")
@CrossOrigin(origins = { "*" })
public class DatosCartaController {
	
	@Autowired
	private DatosCartaService datosCartaService;
	
	@GetMapping("/data/{nombre}")
	public List<DatosCarta> datosCarta(@PathVariable String nombre) {
		return datosCartaService.getDatosCarta(nombre);
	}
}