package com.cefet.compras.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.compras.api.entities.Compra;
import com.cefet.compras.api.repositories.CompraRepository;

@Service
public class CompraService {

	private static final Logger log = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private CompraRepository compraRepository;
	
	public Compra save(Compra compra) {
		log.info("Persistindo compra: {}", compra);
		return compraRepository.save(compra);
	}
	
	public Optional<Compra> findOne(Long id) {
		log.info("Buscando compra por id: {}", id);
		return compraRepository.findById(id);
	}
	
	public List<Compra> findAllList() {
		log.info("Listando as compras");
		return compraRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo compra: {}", id);
		compraRepository.deleteById(id);
	}	
	
	public Optional<Compra> findByDescricao(String descricao) {
		log.info("Buscando um Compra para a descrição {}", descricao);
		return compraRepository.findByDescricao(descricao);
	}
	
}
