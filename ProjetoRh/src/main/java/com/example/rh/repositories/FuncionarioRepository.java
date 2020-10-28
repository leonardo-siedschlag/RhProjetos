
package com.example.rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.rh.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	
	 
	 List<Funcionario> findByNome(String nome);
	 
	 List<Funcionario>findByCodigoFuncionarioStatus(int cod);
	
	 //List<Funcionario> findByNome(@Param(value="nome") String nome);
	      
//	 @Query("SELECT nome FROM Funcionario where nome="+ nome)
//     List<Funcionario> findByNome(String nome);
	
	


}
