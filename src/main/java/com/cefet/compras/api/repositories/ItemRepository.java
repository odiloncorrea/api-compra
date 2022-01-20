package com.cefet.compras.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.compras.api.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	Optional<Item> findByDescricao(String descricao); 
}
