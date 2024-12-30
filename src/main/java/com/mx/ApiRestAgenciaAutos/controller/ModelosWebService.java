package com.mx.ApiRestAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Modelos> listar(){
		return modelosImp.listar();
	}
}
