package com.mx.ApiRestAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Marcas> listar(){
		return marcasImp.listar();
	}
}
