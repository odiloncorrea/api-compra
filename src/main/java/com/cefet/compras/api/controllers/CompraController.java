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

import com.cefet.compras.api.entities.Compra;
import com.cefet.compras.api.services.CompraService;

@RestController
@RequestMapping("/api/compra")
@Api(value = "compra", tags = "Aplicativo 06 - Compras")
@CrossOrigin(origins = "*")
public class CompraController {

	private static final Logger log = LoggerFactory.getLogger(CompraController.class);

	@Autowired
	private CompraService compraService;
	
	public CompraController() {}
	
	
	/**
     * {@code POST  /} : Create a new compra.
     *
     * @param pessoa the compra to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compra, or with status {@code 400 (Bad Request)} if the compra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Compra> createCompra(@Valid @RequestBody Compra compra) throws URISyntaxException {
        log.debug("REST request to save Compra : {}", compra);
        if (compra.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Uma nova compra não pode ter um ID");
        }
        Compra result = compraService.save(compra);
        return ResponseEntity.created(new URI("/api/contatos/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /compra} : Atualiza um compra existente Update.
     *
     * @param contato o compra a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o compra atualizado,
     * ou com status {@code 400 (Bad Request)} se o compra não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o compra não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Compra> updateCompra(@Valid @RequestBody Compra compra) throws URISyntaxException {
        log.debug("REST request to update compra : {}", compra);
        if (compra.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid compra id null");
        }
        Compra result = compraService.save(compra);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /compra/:id} : get the "id" compra.
     *
     * @param id o id do compra que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o compra, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Compra> getContato(@PathVariable Long id) {
        log.info("REST request to get Contato : {}", id);
        Optional<Compra> compra = compraService.findOne(id);
        if(compra.isPresent()) {
            return ResponseEntity.ok().body(compra.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Compra>> getCompra(){
       List<Compra> lista = compraService.findAllList();
       if(lista.size() > 0) {
           return ResponseEntity.ok().body(lista);
       }else{
           return ResponseEntity.notFound().build();
       }
    }
    
	
    /**
     * {@code DELETE  /compra/:id} : delete pelo "id" compra.
     *
     * @param id o id do compra que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
        log.info("REST request to delete compra : {}", id);
        compraService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * {@code GET  /compra/:descricao/exists} : get the "compra" item.
     *
     * @param descricao da compra que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)}, ou com status {@code 204 (Not Found)}.
     */
    @GetMapping("/{descricao}/exists")
    public ResponseEntity<Boolean> isExisting(@PathVariable String descricao){
        log.info("REST request to get Compra By Descrição : {}", descricao);

        if(compraService.findByDescricao(descricao).isPresent()) {
            return ResponseEntity.ok().body(Boolean.TRUE);
        }else{
        	return ResponseEntity.ok().body(Boolean.FALSE);
        }
    }
}
