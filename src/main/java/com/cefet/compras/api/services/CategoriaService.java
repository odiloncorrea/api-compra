package com.cefet.compras.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.compras.api.repositories.CategoriaRepository;
import com.cefet.compras.api.entities.Categoria;

@Service
public class CategoriaService {
	private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria save(Categoria categoria) {
		log.info("Persistindo categoria: {}", categoria);
		return categoriaRepository.save(categoria);
	}
	
	public Optional<Categoria> findOne(Long id) {
		log.info("Buscando categoria por id: {}", id);
		return categoriaRepository.findById(id);
	}
	
	public List<Categoria> findAllList() {
		log.info("Listando as categorias");
		return categoriaRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo categoria: {}", id);
		categoriaRepository.deleteById(id);
	}
	
	public Optional<Categoria> findByDescricao(String descricao) {
		log.info("Buscando uma categoria para a descrição {}", descricao);
		return categoriaRepository.findByDescricao(descricao);
	}
}
