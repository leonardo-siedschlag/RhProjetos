
package com.example.rh.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rh.entity.CartaoPonto;

															
public interface CartaoPontoRepository extends JpaRepository<CartaoPonto, Long>{
	
	
	
//	@Query("FROM CartaoPonto c WHERE c.data BETWEEN Data AND Datas")
//	List <CartaoPonto> findByCartaoPonto(@Param("data") Date Data, @Param("data") Date Datas);
//	
	
	
   
	
}

