package com.cefet.compras.api.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.compras.api.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Optional<Categoria> findByDescricao(String descricao); 

}
