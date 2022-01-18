package com.cefet.compras.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;

import com.cefet.compras.api.entities.Categoria;
import com.cefet.compras.api.services.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@Api(value = "categoria", tags = "Aplicativo 07 - Compras")
@CrossOrigin(origins = "*")
public class CategoriaController {
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	@Autowired
	private CategoriaService categoriaService;
	
	public CategoriaController() {
	}


	/**
     * {@code POST  /} : Create a new categoria.
     *
     * @param categoria the categoria to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoria, or with status {@code 400 (Bad Request)} if the categoria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria) throws URISyntaxException {
        log.debug("REST request to save Categoria : {}", categoria);
        if (categoria.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Uma nova categoria não pode ter um ID");
        }
        Categoria result = categoriaService.save(categoria);
        return ResponseEntity.created(new URI("/api/categorias/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /categorias} : Atualiza uma categoria existente Update.
     *
     * @param categoria o categoria a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o categoria atualizado,
     * ou com status {@code 400 (Bad Request)} se o categoria não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o categoria não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria) throws URISyntaxException {
        log.debug("REST request to update Categoria : {}", categoria);
        if (categoria.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Pessoa id null");
        }
        Categoria result = categoriaService.save(categoria);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /categorias/:id} : get the "id" categoria.
     *
     * @param id o id da categoria que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o categoria, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
        log.info("REST request to get Contato : {}", id);
        Optional<Categoria> categoria = categoriaService.findOne(id);
        if(categoria.isPresent()) {
            return ResponseEntity.ok().body(categoria.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Categoria>> getCategoria(){
       List<Categoria> lista = categoriaService.findAllList();
       if(lista.size() > 0) {
           return ResponseEntity.ok().body(lista);
       }else{
           return ResponseEntity.notFound().build();
       }
    }
    
	
    /**
     * {@code DELETE  /categorias/:id} : delete pelo "id" categoria.
     *
     * @param id o id do categoria que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        log.info("REST request to delete categoria : {}", id);
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
  
}
