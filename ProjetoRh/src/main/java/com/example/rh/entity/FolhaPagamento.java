package com.example.rh.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.rh.entity.enums.SemanaM;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "table_folhaPagamento")
public class FolhaPagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant lancamento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateAbertura;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateFechamento;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@OneToMany(mappedBy = "folhaPagamento", cascade = CascadeType.ALL)
	private Set<CartaoPonto> cartaoPonto = new HashSet<>();

	private int diasUteisMes;
	private double descontoFeriado;
	private double salarioMensalBruto;
	private int tolerance;
	private double sindicato;

	public FolhaPagamento() {
	}

	public FolhaPagamento(Long oid, Instant lancamento, Date abertura, Date fechamento, Funcionario funcionario,
			int diasUteisMes, int tolerance, double sindicato) {
		this.oid = oid;
		this.lancamento = lancamento;
		this.dateAbertura = abertura;
		this.dateFechamento = fechamento;
		this.funcionario = funcionario;
		this.diasUteisMes = diasUteisMes;
		this.tolerance = tolerance;
		this.sindicato = sindicato;
	}

	public Long getId() {
		return oid;
	}

	public void setId(Long oid) {
		this.oid = oid;
	}

	public Instant getLancamento() {
		return lancamento;
	}

	public void setLancamento(Instant lancamento) {
		this.lancamento = lancamento;
	}

	public Date getDateAbertura() {
		return dateAbertura;
	}

	public void setDateAbertura(Date dateAbertura) {
		this.dateAbertura = dateAbertura;
	}

	public Date getDateFechamento() {
		return dateFechamento;
	}

	public void setDateFechamento(Date dateFechamento) {
		this.dateFechamento = dateFechamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setCartaoPonto(Set<CartaoPonto> cartaoPonto) {
		this.cartaoPonto = cartaoPonto;
	}

	public int getDiasUteisMes() {
		return diasUteisMes;
	}

	public void setDiasUteisMes(int diasUteisMes) {
		this.diasUteisMes = diasUteisMes;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public double getSindicato() {
		return sindicato;
	}

	public void setSindicato(double sindicato) {
		this.sindicato = sindicato;
	}

	public List<CartaoPonto> getIntervaloAF() {
		// GET DATAS ENTRE ABERTURA E FECHAMENTO
		List<CartaoPonto> cartaPonto = new ArrayList<>();
		for (CartaoPonto c : cartaoPonto) {
			Date cal = c.getData();
			if (cal.before(dateAbertura) || cal.after(dateFechamento)) {
				System.out.println();
			} else {
				cartaPonto.add(c);
			}
		}
		return cartaPonto;
	}

	public double getSalarioMensalBruto() {
		double salario = 0.0;
		for (CartaoPonto p : getIntervaloAF()) {
			salario += p.getSalarioDia();
		}
		salarioMensalBruto = salario;
		return salarioMensalBruto;
	}

	public double getChamaDsrMensalistaSoma() {

		double soma = 0;
		soma += getDsrMensalistaSoma(SemanaM.PrimeiraSemana);
		soma += getDsrMensalistaSoma(SemanaM.SegundaSemana);
		soma += getDsrMensalistaSoma(SemanaM.TerceiraSemana);
		soma += getDsrMensalistaSoma(SemanaM.QuartaSemana);
		double dsrCalculo = CaculoDsrMensalista.FormatacaoSalario((getSalarioMensalBruto() * soma) / diasUteisMes);

		return dsrCalculo;
	}

	public int getDsrMensalistaSoma(SemanaM semana) {

		double retornoMinutos = 0;
		int verificacaoSemanaFeriado = 0;
		for (CartaoPonto ponto : getIntervaloAF()) {
			retornoMinutos += CaculoDsrMensalista.getMinutosTrabalhados(semana, ponto.getSemana(), ponto, funcionario);
			verificacaoSemanaFeriado += CaculoDsrMensalista.getSemanaFeriado(semana, ponto.getSemana(), ponto);
		}
		int verificacao = CaculoDsrMensalista.getCalculoVerificacao(retornoMinutos, funcionario, tolerance);
		
		if (verificacao == 0 && verificacaoSemanaFeriado == 1) {
			this.descontoFeriado += this.funcionario.getJornada() * this.funcionario.getValorHora();
		}

		return verificacao;
	}

	public double getSalarioLiquido() {

		double descontoInss = CalculoSalarioFuncionario.getTabelaCalculoInssFuncionario(salarioMensalBruto);
		double salarioDInss = salarioMensalBruto - descontoInss;
		double irrf = CalculoSalarioFuncionario.getTabelaCalculoIRRF_Funcionario(salarioDInss);
		double salarioLiquidoMensal = Math.round(
				(salarioMensalBruto + getChamaDsrMensalistaSoma()) - descontoInss - irrf - sindicato - descontoFeriado);

		return salarioLiquidoMensal;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + Objects.hashCode(this.oid);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final FolhaPagamento other = (FolhaPagamento) obj;
		if (!Objects.equals(this.oid, other.oid)) {
			return false;
		}
		return true;
	}

}
