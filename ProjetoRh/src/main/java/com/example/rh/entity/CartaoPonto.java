package com.example.rh.entity;

import java.io.Serializable;
import java.sql.Time;
import java.text.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_cartaoPonto")
public class CartaoPonto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "folha_id")
	private FolhaPagamento folhaPagamento;

	private String Data;
	private String minutoInicial;
	private String minutoFinal;

	public CartaoPonto() {
	}

	public CartaoPonto(Long id, String data, String minutoinicial, String minutofinal, Funcionario funcionario,
			FolhaPagamento folha) {
		super();
		this.id = id;
		this.Data = data;
		this.minutoInicial = minutoinicial;
		this.minutoFinal = minutofinal;
		this.funcionario = funcionario;
		this.folhaPagamento = folha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return Data;
	}

	public void setDia(String dia) {
		Data = dia;
	}

	public String getMinutoInicial() {
		return minutoInicial;
	}

	public void setMinutoInicial(String minutoInicial) {
		this.minutoInicial = minutoInicial;
	}

	public String getMinutoFinal() {
		return minutoFinal;
	}

	public void setMinutoFinal(String minutoFinal) {
		this.minutoFinal = minutoFinal;
	}

	public FolhaPagamento getFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(FolhaPagamento folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public int horaInicial(){
		Time tempoInicial =  Time.valueOf(minutoInicial);
	    int horasInicial= tempoInicial.getHours();
	    int minutoInicial = tempoInicial.getMinutes();
		int conversorInicial = (horasInicial * 60) + minutoInicial;
		return conversorInicial;
	}
	
	public int horaFinal(){
		Time tempoInicial =  Time.valueOf(minutoFinal);
	    int horasFinal= tempoInicial.getHours();
	    int minutoFinal = tempoInicial.getMinutes();
		int conversorInicial = (horasFinal * 60) + minutoFinal;
		return conversorInicial;
	}

	public int getMinutosTrabalhados() {
		
	    int intervaloMinutos =Math.abs(horaInicial() - horaFinal());
		return intervaloMinutos;
	}

	public double getSalarioDia() {

		double salarioMinuto = funcionario.getValorHora() / 60;
		double salarioDia = getMinutosTrabalhados() * salarioMinuto;
		NumberFormat nf  = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		double d = Double.parseDouble(nf.format(salarioDia).replace(',', '.'));
		return d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoPonto other = (CartaoPonto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
