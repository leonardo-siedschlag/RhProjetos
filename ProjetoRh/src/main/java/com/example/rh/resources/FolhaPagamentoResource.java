package com.example.rh.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rh.entity.FolhaPagamento;

import com.example.rh.services.FolhaPagamentoService;


@RestController
@RequestMapping(value="/folhaPagamento")
public class FolhaPagamentoResource {
	@Autowired
	private FolhaPagamentoService service;
		
	@GetMapping
	public ResponseEntity<List<FolhaPagamento>> findAll(){
		List<FolhaPagamento> list  = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	@GetMapping(value= "/{id}")		
	public ResponseEntity<FolhaPagamento> findById(@PathVariable Long id){
		FolhaPagamento obj  = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping(value = "/ativos")
	public ResponseEntity<List<FolhaPagamento>> findByAtivos() {
		List<FolhaPagamento> lista = service.findByAtivo();
		return ResponseEntity.ok().body(lista);

	}
	
	
	
	
	
	
	
}
