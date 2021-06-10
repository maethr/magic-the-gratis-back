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
	public MtgDBCarta getCarta(@PathVariable String id) {
		return service.getCarta(id);
	}

	@GetMapping("/carta/nombre/{nombre}/oracle")
	public Page<MtgDBCarta> getByNombreGroupByOracle(@PathVariable String nombre, @Nullable @RequestParam String set,
			@Nullable @RequestParam Integer page, @Nullable @RequestParam Integer size) {
		if (set != null) {
			return service.getByNombreGroupByOracle(nombre, set, page);
		}
		return service.getByNombreGroupByOracle(nombre, page, size);
	}

	@GetMapping("/carta/nombre/{nombre}/ilust")
	public Page<MtgDBCarta> getByNombreGroupByIlustracion(@PathVariable String nombre,
			@Nullable @RequestParam String set, @Nullable @RequestParam Integer page, @Nullable @RequestParam Integer size) {
		if (set != null) {
			return service.getByNombreGroupByIlustracion(nombre, set, page);
		}
		return service.getByNombreGroupByIlustracion(nombre, page, size);
	}

	@GetMapping("/carta/nombre/{nombre}")
	public Page<MtgDBCarta> getByNombreGroupById(@PathVariable String nombre, @Nullable @RequestParam String set,
			@Nullable @RequestParam Integer page, @Nullable @RequestParam Integer size) {
		if (set != null) {
			return service.getByNombreGroupById(nombre, set, page);
		}
		return service.getByNombreGroupById(nombre, page, size);
	}

	@GetMapping("/carta/oracle/{oracle_id}/ilust")
	public Page<MtgDBCarta> getByOracleGroupByIlustracion(@PathVariable String oracle_id,
			@Nullable @RequestParam Integer page) {
		return service.getByOracleGroupByIlustracion(oracle_id, page);
	}

	@GetMapping("/carta/ilust/{ilust_id}")
	public Page<MtgDBCarta> getByIlustracionGroupById(@PathVariable String ilust_id,
			@Nullable @RequestParam Integer page) {
		return service.getByIlustracionGroupById(ilust_id, page);
	}
}
