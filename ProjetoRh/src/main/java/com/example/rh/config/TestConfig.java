package com.example.rh.config;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.rh.entity.CartaoPonto;
import com.example.rh.entity.FolhaPagamento;
import com.example.rh.entity.Funcionario;
import com.example.rh.entity.enums.DiaDescanso;
import com.example.rh.entity.enums.DsrTipos;
import com.example.rh.entity.enums.VericacaoPresenca;
import com.example.rh.entity.enums.SemanaM;
import com.example.rh.entity.enums.Setor;
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
		Funcionario f1 = new Funcionario(null, "Joao Carlos", "423432432", Date.valueOf("2019-09-20"), 50.0, 10,
				StatusFuncionario.ATIVO, 100.0, 30.0, Setor.Administração, DsrTipos.Mensalista, DiaDescanso.domingo, 5, 100);

		Funcionario f2 = new Funcionario(null, "Maria", "423432432", Date.valueOf("2019-09-20"), 4.0, 10,
				StatusFuncionario.ATIVO, 150.0, 15.0, Setor.Produção, DsrTipos.Mensalista, DiaDescanso.domingo, 5, 50);

		FolhaPagamento folha = new FolhaPagamento(null, Instant.parse("2019-06-20T19:53:07Z"),
				Date.valueOf("2020-10-23"), Date.valueOf("2020-10-26"), f1, 22, 10, 50);

		CartaoPonto p1 = new CartaoPonto(null, Date.valueOf("2020-10-25"), "2020-11-26 19:00:00", "2020-11-26 21:52:00",
				SemanaM.PrimeiraSemana, VericacaoPresenca.Presente, f1, folha);
		
		
		funcionarioRepository.saveAll(Arrays.asList(f1,f2));
		folhaPagamentoRepository.saveAll(Arrays.asList(folha));
		cartaoPontoRepository.saveAll(Arrays.asList(p1));

//		p2.updateFaltas(VericacaoPresenca.FaltaJustificada);
//		cartaoPontoRepository.saveAll(Arrays.asList(p2));

	}

}
