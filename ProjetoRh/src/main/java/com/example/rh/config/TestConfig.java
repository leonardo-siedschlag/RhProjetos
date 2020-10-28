package com.example.rh.config;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.rh.entity.CartaoPonto;
import com.example.rh.entity.FolhaPagamento;
import com.example.rh.entity.Funcionario;
import com.example.rh.entity.enums.StatusFuncionario;
import com.example.rh.repositories.CartaoPontoRepository;
import com.example.rh.repositories.FolhaPagamentoRepository;
import com.example.rh.repositories.FuncionarioRepository;

@Configuration
//configuracao especifica para o perfil de teste
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private CartaoPontoRepository cartaoPontoRepository;

	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;

	@Override
	public void run(String... args) throws Exception {
		Funcionario f1 = new Funcionario(null, "Joao Carlos", "423432432", Date.valueOf("2019-09-20"), 4.0, 
				StatusFuncionario.ATIVO);
		Funcionario f2 = new Funcionario(null, "Maria", "423432432", Date.valueOf("2019-09-20"), 4.0,
				StatusFuncionario.ATIVO);
		
		Funcionario f3 = new Funcionario(null, "Pedro", "423432432", Date.valueOf("2019-09-20"), 4.0,
				StatusFuncionario.ATIVO);
		
		Funcionario f4 = new Funcionario(null, "Ladir", "423432432", Date.valueOf("2019-09-20"), 4.0,
				StatusFuncionario.ATIVO);

		// cartao ponto
		Long horaInicial = Long.parseLong("1602774072256");
		Long horaFinal = Long.parseLong("1602783685208");

		FolhaPagamento folha = new FolhaPagamento(null, Instant.parse("2019-06-20T19:53:07Z"),f1);
		FolhaPagamento folha2 = new FolhaPagamento(null, Instant.parse("2019-06-20T19:53:07Z"),f2);
		FolhaPagamento folha3 = new FolhaPagamento(null, Instant.parse("2019-06-20T19:53:07Z"),f3);
		FolhaPagamento folha4 = new FolhaPagamento(null, Instant.parse("2019-06-20T19:53:07Z"),f4);
		
//		CartaoPonto p1 = new CartaoPonto(null, "23/10/2020", horaInicial, horaFinal, f1,folha);
//		CartaoPonto p2 = new CartaoPonto(null, "24/10/2020", horaInicial, horaFinal, f1,folha);
//		CartaoPonto p3 = new CartaoPonto(null, "23/10/2020", horaInicial, horaFinal, f2,folha2);
//		CartaoPonto p4 = new CartaoPonto(null, "24/10/2020", horaInicial, horaFinal, f2,folha2);
		
		CartaoPonto p1 = new CartaoPonto(null, "23/10/2020", "14:00:00","16:00:00", f1,folha);
			
		funcionarioRepository.saveAll(Arrays.asList(f1,f2,f3,f4));
		folhaPagamentoRepository.saveAll(Arrays.asList(folha,folha2,folha3,folha4));
		cartaoPontoRepository.saveAll(Arrays.asList(p1));
	}

}
