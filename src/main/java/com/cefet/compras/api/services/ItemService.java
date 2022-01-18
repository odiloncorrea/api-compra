package com.cefet.compras.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.compras.api.entities.Item;
import com.cefet.compras.api.repositories.ItemRepository;

@Service
public class ItemService {
	
	private static final Logger log = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item save(Item item) {
		log.info("Persistindo item: {}", item);
		return itemRepository.save(item);
	}
	
	public Optional<Item> findOne(Long id) {
		log.info("Buscando item por id: {}", id);
		return itemRepository.findById(id);
	}
	
	public List<Item> findAllList() {
		log.info("Listando os itens");
		return itemRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo item: {}", id);
		itemRepository.deleteById(id);
	}

}
