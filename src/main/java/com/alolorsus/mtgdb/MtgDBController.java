package com.alolorsus.mtgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.mtgdb.entity.MtgDBCarta;
import com.alolorsus.mtgdb.service.MtgDBServiceImp;

@RestController
@RequestMapping("/mtgdb")
@CrossOrigin(origins = { "*" })
public class MtgDBController {
	
	@Autowired
	private MtgDBServiceImp service;
	
	@GetMapping("/carta/{id}")
	public MtgDBCarta getCarta (@PathVariable String id) {
		return service.getCarta(id);
	}
	
	@GetMapping("/carta/nombre/{nombre}")
	public Page<MtgDBCarta> getOraclesPorNombre (@PathVariable String nombre, @Nullable @RequestParam Integer page) {
		return service.getCartasGroupByOracle(nombre, page);
	}
	
	@GetMapping("/carta/oracle/{oracle_id}")
	public Page<MtgDBCarta> getIlustracionesPorOracle (@PathVariable String oracle_id, @Nullable @RequestParam Integer page) {
		return service.getCartasGroupByIlustracion(oracle_id, page);
	}
	
	@GetMapping("/carta/illustration/{illust_id}")
	public Page<MtgDBCarta> getCartasPorIlustracion (@PathVariable String illust_id, @Nullable @RequestParam Integer page) {
		return service.getCartas(illust_id, page);
	}
}
