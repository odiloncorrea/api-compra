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

import com.cefet.compras.api.entities.Item;
import com.cefet.compras.api.services.ItemService;


@RestController
@RequestMapping("/api/item")
@Api(value = "item", tags = "Aplicativo 06 - Compras")
@CrossOrigin(origins = "*")
public class ItemController {

	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;
	
	public ItemController() {}
	
	/**
     * {@code POST  /} : Create a new item.
     *
     * @param item the item to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new item, or with status {@code 400 (Bad Request)} if the item has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) throws URISyntaxException {
        log.debug("REST request to save Item : {}", item);
        if (item.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo item não pode ter um ID");
        }
        Item result = itemService.save(item);
        return ResponseEntity.created(new URI("/api/itens/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /item} : Atualiza um item existente Update.
     *
     * @param item o item a ser atualizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o item atualizado,
     * ou com status {@code 400 (Bad Request)} se o item não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o item não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) throws URISyntaxException {
        log.debug("REST request to update Item : {}", item);
        if (item.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Pessoa id null");
        }
        Item result = itemService.save(item);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /item/:id} : get the "id" item.
     *
     * @param id o id do item que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o item, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        log.info("REST request to get Item : {}", id);
        Optional<Item> item = itemService.findOne(id);
        if(item.isPresent()) {
            return ResponseEntity.ok().body(item.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Item>> getItem(){
       List<Item> lista = itemService.findAllList();
       if(lista.size() > 0) {
           return ResponseEntity.ok().body(lista);
       }else{
           return ResponseEntity.notFound().build();
       }
    }
    
	
    /**
     * {@code DELETE  /item/:id} : delete pelo "id" item.
     *
     * @param id o id do item que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        log.info("REST request to delete item : {}", id);
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * {@code GET  /item/:descricao/exists} : get the "descricao" item.
     *
     * @param descricao do item que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)}, ou com status {@code 204 (Not Found)}.
     */
    @GetMapping("/{descricao}/exists")
    public ResponseEntity<Boolean> isExisting(@PathVariable String descricao){
        log.info("REST request to get Item By Descrição : {}", descricao);

        if(itemService.findByDescricao(descricao).isPresent()) {
            return ResponseEntity.ok().body(Boolean.TRUE);
        }else{
        	return ResponseEntity.ok().body(Boolean.FALSE);
        }
    }

}
