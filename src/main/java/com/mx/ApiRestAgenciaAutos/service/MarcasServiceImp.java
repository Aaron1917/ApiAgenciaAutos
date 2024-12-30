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

	// listar
	@Transactional(readOnly = true)
	public List<Marcas> listar() {
		return marcasRepo.findAll();

	}
	
	// GUARDAR--VALIDAR QUE EL ID Y NOMBRE NO SE REPITAN
	// 1: Id existente
	// 2: Nombre existente
	// 3: Nombre y Id existente
	@Transactional
	public Byte guardar(Marcas marca) {
		byte retorno = 0;
		boolean valorExistente = false;
		for (Marcas m : marcasRepo.findAll()) {
			if (m.getId().equals(marca.getId())) {
				valorExistente = true;
				retorno+=1;
			}
			if (m.getNombre().equalsIgnoreCase(marca.getNombre())) {
				valorExistente = true;
				retorno+=2;
			}
			if (valorExistente)
				break;
		}
		if (!valorExistente)
			marcasRepo.save(marca);
		return retorno;
	}

	// BUSCAR
	@Transactional(readOnly = true)
	public Marcas buscar(long id) {
		return marcasRepo.findById(id).orElse(null);
	}
	
	
	// EDITAR---VALIDAR QUE EL ID EXISTA PARA PODER EDITAR
	@Transactional
	public boolean editar(Marcas marca) {
		boolean marcaExistente = false;
		for (Marcas m : marcasRepo.findAll()) {
			if (m.getId().equals(marca.getId())) {
				marcaExistente = true;
				break;
			}
		}
		if (marcaExistente)
			marcasRepo.save(marca);
		return marcaExistente;
	}
	
	// ELIMINAR----VALIDAR QUE EL ID EXISTA PARA PODER ELIMINAR
	@Transactional
	public boolean eliminar(long id) {
		boolean marcaExistente = false;
		for (Marcas m : marcasRepo.findAll()) {
			if (m.getId().equals(id)) {
				marcaExistente = true;
				break;
			}
		}
		if (marcaExistente)
			marcasRepo.deleteById(id);;
		return marcaExistente;
	}
}
