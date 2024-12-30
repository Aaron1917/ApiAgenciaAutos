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

	@Autowired
	MarcasServiceImp marcasImp;

	@Transactional
	public List<Modelos> listar() {
		return modelosRepo.findAll();
	}

	// ---GUARDAR---VALIDAR EL IDMODELO, NOMBRE NO SE REPITAN Y QUE EL IDMARCA
	// EXISTA
	/*
	 * Valores de retorno 1:
	 */
	@Transactional
	public String guardar(Modelos modelo) {
		String errMsg = "";
		boolean valorExistente = false;
		for (Modelos mo : modelosRepo.findAll()) {
			if (mo.getIdMod().equals(modelo.getIdMod())) {
				valorExistente = true;
				errMsg += "Id de modelo repetido ";
			}
			if (mo.getNombre().equalsIgnoreCase(modelo.getNombre())) {
				valorExistente = true;
				errMsg += "Nombre de modelo repetido ";
			}
			if (valorExistente)
				break;
		}
		// comprpobar que la marca exista
		if (marcasImp.buscar(modelo.getMarca().getId()) != null) {
			if (!valorExistente)
				modelosRepo.save(modelo);
		}
		else {
			errMsg += "Id de marca no existe";
		}	
		return errMsg;

	}

	// ---BUSCAR
	@Transactional(readOnly = true)
	public Modelos buscar(long id) {
		return modelosRepo.findById(id).orElse(null);
	}

	// ---EDITAR----VALIDAR QUE EL ID EXISTA PARA PODER EDITAR
	@Transactional
	public boolean editar(Modelos modelo) {
		boolean valorExistente = false;
		for (Modelos mo : modelosRepo.findAll()) {
			if (mo.getIdMod().equals(modelo.getIdMod())) {
				valorExistente = true;
				break;
			}
		}
		if (valorExistente)
			modelosRepo.save(modelo);
		return valorExistente;
	}

	// ---ELIMINAR----VALIDAR QUE EL ID EXISTA PARA PODER ELIMINAR
	@Transactional
	public boolean eliminar(long id) {
		boolean valorExistente = false;
		for (Modelos mo : modelosRepo.findAll()) {
			if (mo.getIdMod().equals(id)) {
				valorExistente = true;
				break;
			}
		}
		if (valorExistente)
			modelosRepo.deleteById(id);
		return valorExistente;
	}
}
