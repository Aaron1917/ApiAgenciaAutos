package com.mx.ApiRestAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestAgenciaAutos.model.Marcas;
import com.mx.ApiRestAgenciaAutos.repository.MarcasRepository;

@Service
public class MarcasServiceImp {
	
	@Autowired
	MarcasRepository marcasRepo;
	
	@Transactional(readOnly = true)
	public List<Marcas> listar(){
		return marcasRepo.findAll();
	}
}
