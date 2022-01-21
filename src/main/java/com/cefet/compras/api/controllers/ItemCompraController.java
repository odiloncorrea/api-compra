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

import com.cefet.compras.api.entities.ItemCompra;
import com.cefet.compras.api.services.ItemCompraService;

@RestController
@RequestMapping("/api/itemcompra")
@Api(value = "itemcompra", tags = "Aplicativo 07 - Compras")
@CrossOrigin(origins = "*")
public class ItemCompraController {
	
	private static final Logger log = LoggerFactory.getLogger(ItemCompraController.class);

	@Autowired
	private ItemCompraService itemCompraService;
	
	public ItemCompraController() {}
	
	/**
     * {@code POST  /} : Create a new itemcompra.
     *
     * @param itemcompra the itemcompra to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemcompra, or with status {@code 400 (Bad Request)} if the itemcompra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<ItemCompra> createItemCompra(@Valid @RequestBody ItemCompra itemCompra) throws URISyntaxException {
        log.debug("REST request to save Pessoa : {}", itemCompra);
        if (itemCompra.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo item da compra não pode ter um ID");
        }
        ItemCompra result = itemCompraService.save(itemCompra);
        return ResponseEntity.created(new URI("/api/itemcompra/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /itemcompra} : Atualiza um item da Compra existente Update.
     *
     * @param contato o itemCompra a ser atualizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o itemCompra atualizado,
     * ou com status {@code 400 (Bad Request)} se o itemCompra não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o itemCompra não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<ItemCompra> updateItemCompra(@Valid @RequestBody ItemCompra itemCompra) throws URISyntaxException {
        log.debug("REST request to update itemCompra : {}", itemCompra);
        if (itemCompra.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Pessoa id null");
        }
        ItemCompra result = itemCompraService.save(itemCompra);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /itemCompra/:id} : get the "id" itemCompra.
     *
     * @param id o id do itemCompra que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o itemCompra, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemCompra> getItemCompra(@PathVariable Long id) {
        log.info("REST request to get Contato : {}", id);
        Optional<ItemCompra> itemCompra = itemCompraService.findOne(id);
        if(itemCompra.isPresent()) {
            return ResponseEntity.ok().body(itemCompra.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<ItemCompra>> getItemCompra(){
       List<ItemCompra> lista = itemCompraService.findAllList();
       return ResponseEntity.ok().body(lista);
       /*
       if(lista.size() > 0) {
           return ResponseEntity.ok().body(lista);
       }else{
           return ResponseEntity.notFound().build();
       }
       */
    }
    
	
    /**
     * {@code DELETE  /contatos/:id} : delete pelo "id" contato.
     *
     * @param id o id do contatos que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCompra(@PathVariable Long id) {
        log.info("REST request to delete contato : {}", id);
        itemCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
