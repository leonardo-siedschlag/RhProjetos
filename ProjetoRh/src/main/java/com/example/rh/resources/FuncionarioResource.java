package com.example.rh.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rh.entity.Funcionario;
import com.example.rh.services.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioResource {
	@Autowired
	private FuncionarioService service;

	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {
		List<Funcionario> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
		Funcionario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	// ctrl + shift + c adiciona comentarios multiplas linhas

	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Funcionario>> findByName(@PathVariable String nome) {
		List<Funcionario> lista = service.funcionarioFindByName(nome);
		return ResponseEntity.ok().body(lista);

	}

}
