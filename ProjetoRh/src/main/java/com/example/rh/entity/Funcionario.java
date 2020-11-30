package com.example.rh.entity;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.rh.entity.enums.DiaDescanso;
import com.example.rh.entity.enums.DsrTipos;
import com.example.rh.entity.enums.Setor;
import com.example.rh.entity.enums.StatusFuncionario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateAdmissao;
	private Double valorHora;
	private int horasSemanais;
	private Integer codigoFuncionarioStatus;
	private double percentualFeriados;
	private double percentualAdicionalNoturno;
	private Integer setor;
	private Integer dsrTipos;
	private Integer diaDescanso;
	private Integer jornada;
	private Integer porcentagemHorasExtras;
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private Set<CartaoPonto> cartaoPonto = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private List<FolhaPagamento> folha = new ArrayList<>();

	public Funcionario() {
	}

	public Funcionario(Long id, String nome, String cpf, Date dateAdmissao, Double valorHora, int horasSemanais,
			StatusFuncionario status, double percentualFeriados, double percentualAdicionalNight, Setor setor,
			DsrTipos dsr, DiaDescanso diaDescanso, Integer jornada, Integer porcentagemHorasExtras) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dateAdmissao = dateAdmissao;
		this.valorHora = valorHora;
		this.horasSemanais = horasSemanais;
		setFuncionarioStatus(status);
		setSetor(setor);
		setDsrTipos(dsr);
		setDiaDescanso(diaDescanso);
		this.percentualFeriados = percentualFeriados;
		this.percentualAdicionalNoturno = percentualAdicionalNight;
		this.jornada = jornada;
		this.porcentagemHorasExtras = porcentagemHorasExtras;
	}

	public StatusFuncionario getStatusFuncionario() {

		return StatusFuncionario.funcFuncionarioStatus(codigoFuncionarioStatus);// converte um numero value para ordem
	}

	public void setFuncionarioStatus(StatusFuncionario statusFuncionario) {
		if (statusFuncionario != null) {
			this.codigoFuncionarioStatus = statusFuncionario.getCode();
		}

	}

	public Setor getSetor() {
		return Setor.getSetor(setor);
	}

	public void setSetor(Setor setor) {
		if (setor != null) {
			this.setor = setor.getCodigo();
		}
	}

	public DsrTipos getDsrTipos() {
		return DsrTipos.getDsrTipos(dsrTipos);
	}

	public void setDsrTipos(DsrTipos dsr) {
		if (dsr != null) {
			this.dsrTipos = dsr.getCodigo();
		}
	}

	public DiaDescanso getDiaDescanso() {
		return DiaDescanso.getDiaDescanso(diaDescanso);
	}

	public void setDiaDescanso(DiaDescanso diaDescanso) {
		if (diaDescanso != null) {
			this.diaDescanso = diaDescanso.getCode();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDateAdmissao() {
		return dateAdmissao;
	}

	public void setDateAdmissao(Date dateAdmissao) {
		this.dateAdmissao = dateAdmissao;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public int getHorasSemanais() {
		return horasSemanais;
	}

	public void setHorasSemanais(int horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	public Set<CartaoPonto> getCartaoPonto() {
		return cartaoPonto;
	}

	public void setCartaoPonto(Set<CartaoPonto> cartaoPonto) {
		this.cartaoPonto = cartaoPonto;
	}

	public List<FolhaPagamento> getFolha() {
		return folha;
	}

	public void setFolha(List<FolhaPagamento> folha) {
		this.folha = folha;
	}

	public double getPercentualFeriados() {
		return percentualFeriados;
	}

	public void setPercentualFeriados(double percentualFeriados) {
		this.percentualFeriados = percentualFeriados;
	}

	public double getPercentualAdicionalNoturno() {
		return percentualAdicionalNoturno;
	}

	public void setPercentualAdicionalNoturno(double percentualAdicionalNoturno) {
		this.percentualAdicionalNoturno = percentualAdicionalNoturno;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	public Integer getPorcentagemHorasExtras() {
		return porcentagemHorasExtras;
	}

	public void setPorcentagemHorasExtras(Integer porcentagemHorasExtras) {
		this.porcentagemHorasExtras = porcentagemHorasExtras;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 23 * hash + Objects.hashCode(this.id);
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
		final Funcionario other = (Funcionario) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
