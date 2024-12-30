package com.mx.ApiRestAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestAgenciaAutos.model.Modelos;
import com.mx.ApiRestAgenciaAutos.repository.ModelosRepository;

@Service
public class ModelosServiceImp {

	@Autowired
	ModelosRepository modelosRepo;
	
	@Transactional
	public List<Modelos> listar() {
		return modelosRepo.findAll();
	}
}
