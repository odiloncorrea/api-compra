package com.cefet.compras.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.compras.api.entities.ItemCompra;
import com.cefet.compras.api.repositories.ItemCompraRepository;

@Service
public class ItemCompraService {
	
	private static final Logger log = LoggerFactory.getLogger(ItemCompraService.class);
	
	@Autowired
	private ItemCompraRepository itemCompraRepository;
	
	public ItemCompra save(ItemCompra itemCompra) {
		log.info("Persistindo item da compra: {}", itemCompra);
		return itemCompraRepository.save(itemCompra);
	}
	
	public Optional<ItemCompra> findOne(Long id) {
		log.info("Buscando item da compra por id: {}", id);
		return itemCompraRepository.findById(id);
	}
	
	public List<ItemCompra> findAllList() {
		log.info("Listando os itens da compra");
		return itemCompraRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo item da compra: {}", id);
		itemCompraRepository.deleteById(id);
	}

}
