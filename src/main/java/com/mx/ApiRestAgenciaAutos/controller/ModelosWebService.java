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

import com.mx.ApiRestAgenciaAutos.model.Modelos;
import com.mx.ApiRestAgenciaAutos.service.ModelosServiceImp;

@RestController
@RequestMapping(path = "ModelosWebService")
@CrossOrigin
public class ModelosWebService {

	@Autowired
	ModelosServiceImp modelosImp;

	// http://localhost:9000/ModelosWebService/listar
	@GetMapping(path = "listar")
	public List<Modelos> listar() {
		return modelosImp.listar();
	}

	// http://localhost:9000/ModelosWebService/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelo) {
		String resultado = modelosImp.guardar(modelo);
		if (resultado == null) {
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	// http://localhost:9000/ModelosWebService/buscar
	@PostMapping(path = "buscar")
	public Modelos buscar(@RequestBody Modelos modelo) {
		return modelosImp.buscar(modelo.getIdMod());
	}

	// http://localhost:9000/ModelosWebService/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo) {
		boolean res = modelosImp.editar(modelo);
		if (res)
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
		return new ResponseEntity<>("El Id del modelo no existe", HttpStatus.OK);
	}

	// http://localhost:9000/ModelosWebService/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Modelos modelo) {
		boolean res = modelosImp.eliminar(modelo.getIdMod());
		if (res)
			return new ResponseEntity<>("Modelo eliminado", HttpStatus.OK);
		return new ResponseEntity<>("El Id del modelo no existe", HttpStatus.OK);
	}
}