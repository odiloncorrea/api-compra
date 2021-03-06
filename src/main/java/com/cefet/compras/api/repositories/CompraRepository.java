package com.cefet.compras.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.compras.api.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

	Optional<Compra> findByDescricao(String descricao);
}
