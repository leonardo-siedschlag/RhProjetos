package com.example.rh.resources;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rh.entity.CartaoPonto;
import com.example.rh.services.CartaoPontoService;


@RestController
@RequestMapping(value="/cartaoPonto")
public class CartaoPontoResource {
	@Autowired
	private CartaoPontoService service;
		
	@GetMapping
	public ResponseEntity<List<CartaoPonto>> findAll(){
		List<CartaoPonto> list  = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	@GetMapping(value= "/{id}")		
	public ResponseEntity<CartaoPonto> findById(@PathVariable Long id){
		CartaoPonto obj  = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
}
