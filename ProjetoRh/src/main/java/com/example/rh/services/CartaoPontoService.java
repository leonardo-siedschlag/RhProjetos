package com.example.rh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rh.entity.CartaoPonto;
import com.example.rh.repositories.CartaoPontoRepository;

@Service
public class CartaoPontoService {
	@Autowired
	private CartaoPontoRepository repository;

	public List<CartaoPonto> findAll() {
		return repository.findAll();
	}

	public CartaoPonto findById(Long id) {
		Optional<CartaoPonto> obj = repository.findById(id);
		return obj.get();
	}

}
