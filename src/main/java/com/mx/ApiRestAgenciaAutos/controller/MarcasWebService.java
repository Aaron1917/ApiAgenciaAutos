package com.mx.ApiRestAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestAgenciaAutos.model.Marcas;
import com.mx.ApiRestAgenciaAutos.service.MarcasServiceImp;

@RestController
@RequestMapping(path = "MarcasWebService")
@CrossOrigin
public class MarcasWebService {
	@Autowired
	MarcasServiceImp marcasImp;

	// http://localhost:9000/MarcasWebService/listar
	@GetMapping(path = "listar")
	public List<Marcas> listar() {
		return marcasImp.listar();
	}

	// http://localhost:9000/MarcasWebService/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca) {
		byte res = marcasImp.guardar(marca);
		switch (res) {
		case 0:
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("Id repetido", HttpStatus.OK);
		case 2:
			return new ResponseEntity<>("Nombre de marca repetida", HttpStatus.OK);
		case 3:
			return new ResponseEntity<>("Id y Nombre repetidos", HttpStatus.OK);
		default:
			return new ResponseEntity<>("Error desconocido", HttpStatus.NO_CONTENT);
		}
	}

	// http://localhost:9000/MarcasWebService/buscar
	@PostMapping(path = "buscar")
	public Marcas buscar(@RequestBody Marcas marca) {
		return marcasImp.buscar(marca.getId());
	}

	// http://localhost:9000/MarcasWebService/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca) {
		boolean validar = marcasImp.editar(marca);
		if (validar)
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Id no encontrado", HttpStatus.NOT_FOUND);
	}

	// http://localhost:9000/MarcasWebService/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Marcas marca) {
		boolean validar = marcasImp.eliminar(marca.getId());
		if (validar)
			return new ResponseEntity<>("Marca eliminada", HttpStatus.OK);
		else
			return new ResponseEntity<>("Id no encontrado", HttpStatus.NOT_FOUND);
	}
}
