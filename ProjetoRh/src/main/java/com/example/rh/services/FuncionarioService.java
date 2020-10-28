package com.example.rh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rh.entity.Funcionario;
import com.example.rh.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.get();
	}

	public List<Funcionario> funcionarioFindByName(String nome) {
		List<Funcionario> funcionario = repository.findByNome(nome);
		return funcionario;
	}

}
