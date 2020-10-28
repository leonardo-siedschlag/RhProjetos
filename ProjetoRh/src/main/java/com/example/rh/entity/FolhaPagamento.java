package com.example.rh.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "table_folhaPagamento")
public class FolhaPagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@OneToMany(mappedBy = "folhaPagamento", cascade=CascadeType.ALL)
	private Set<CartaoPonto> cartaoPonto = new HashSet<>();

	public FolhaPagamento() {
	}

	public FolhaPagamento(Long oid, Instant data, Funcionario funcionario) {
		this.oid = oid;
		this.data = data;
		this.funcionario = funcionario;

	}

	public Long getId() {
		return oid;
	}

	public void setId(Long oid) {
		this.oid = oid;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Set<CartaoPonto> getCartaoPonto() {
		return cartaoPonto;
	}

	public void setCartaoPonto(Set<CartaoPonto> cartaoPonto) {
		this.cartaoPonto = cartaoPonto;
	}

	public double getSalarioMensal() {
		double s = 0.0;
		for (CartaoPonto p : cartaoPonto) {
			s += p.getSalarioDia();

		}
		return s;

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
