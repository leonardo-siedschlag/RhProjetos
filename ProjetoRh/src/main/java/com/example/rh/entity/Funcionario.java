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
	private Integer codigoFuncionarioStatus;
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private Set<CartaoPonto> cartaoPonto = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<FolhaPagamento> folha = new ArrayList<>();

	

	public Funcionario() {
	}

	public Funcionario(Long id, String nome, String cpf, Date dateAdmissao, Double valorHora,
			StatusFuncionario status) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dateAdmissao = dateAdmissao;
		this.valorHora = valorHora;
		setFuncionarioStatus(status);
	}

	public StatusFuncionario getStatusFuncionario() {

		return StatusFuncionario.funcFuncionarioStatus(codigoFuncionarioStatus);// converte um numero value para ordem
																				// status

	}

	public void setFuncionarioStatus(StatusFuncionario statusFuncionario) {
		if (statusFuncionario != null) {
			this.codigoFuncionarioStatus = statusFuncionario.getCode();
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
