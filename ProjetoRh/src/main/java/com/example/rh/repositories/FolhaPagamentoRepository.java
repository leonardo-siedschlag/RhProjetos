
package com.example.rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rh.entity.FolhaPagamento;


public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long> {
	
	List<FolhaPagamento> findByOid(Long oid);
}
