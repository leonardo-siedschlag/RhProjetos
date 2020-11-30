package com.example.rh.repositories;
import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.rh.entity.FolhaPagamento;
import com.example.rh.entity.enums.Setor;

public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long> {

	//List<FolhaPagamento> findByOid(Long oid);
	@Query("FROM FolhaPagamento folha,Funcionario func where folha.oid=func.id and func.codigoFuncionarioStatus=1")
    List<FolhaPagamento> findByAtivo();
	
	@Query("FROM FolhaPagamento folha,Funcionario func where folha.oid=func.id and folha.lancamento=?1 and func.nome=?2")
    List<FolhaPagamento> findByLancamento(Instant lancamento, String nome);

	@Query("FROM FolhaPagamento folha, Funcionario func where func.id=folha.oid and func.setor=?1 and func.codigoFuncionarioStatus=1")
	List<FolhaPagamento>findBySetor(int setor);
	
}
