package com.example.rh.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rh.entity.FolhaPagamento;
import com.example.rh.repositories.FolhaPagamentoRepository;
import com.example.rh.repositories.FuncionarioRepository;

@Service
public class FolhaPagamentoService {
	@Autowired
	private FolhaPagamentoRepository repository;

	public List<FolhaPagamento> findAll() {
		return repository.findAll();
	}

	public FolhaPagamento findById(Long id) {
		Optional<FolhaPagamento> obj = repository.findById(id);
		return obj.get();
	}

//	public List<FolhaPagamento> findByAtivo() {
//		List<Funcionario> ListaFuncionairosAtivos = repositoryFuncionario.findByCodigoFuncionarioStatus(1);
//		List<FolhaPagamento> folha = new ArrayList<FolhaPagamento>();
//		for (Funcionario f : ListaFuncionairosAtivos) {
//			folha.addAll(repository.findByOid(f.getId()));
//		}
//		return folha;
//	}
	public List<FolhaPagamento> findByAtivo() {

		List<FolhaPagamento> folha = repository.findByAtivo();

		return folha;
	}

	public List<FolhaPagamento> findByInstantNome(Instant lancamento, String nome) {

		List<FolhaPagamento> folha = repository.findByLancamento(lancamento, nome);
		return folha;

	}

	public List<FolhaPagamento> findByFolhaSetor(int setor) {
		List<FolhaPagamento> folha = repository.findBySetor(setor);
		return folha;
		
	}

}
