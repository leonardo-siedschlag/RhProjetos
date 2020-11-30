
package com.example.rh.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.rh.entity.enums.Feriados;
import com.example.rh.entity.enums.SemanaM;
import com.example.rh.entity.enums.VericacaoPresenca;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;
	private String instantInicial;
	private String instantFinal;
	private Integer semana;
	private Integer presenca;

	public CartaoPonto() {
	}

	public CartaoPonto(Long id, Date data, String instantInicial, String instantFinal, SemanaM semana,
			VericacaoPresenca presenca, Funcionario funcionario, FolhaPagamento folhaPagamento) {
		super();
		this.id = id;
		this.data = data;
		this.instantInicial = instantInicial;
		this.instantFinal = instantFinal;
		setSemanaM(semana);
		setVerificacaoPresenca(presenca);
		this.funcionario = funcionario;
		this.folhaPagamento = folhaPagamento;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getDataFeriados() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(data);
		return Feriados.feriados(dataFormatada);
	}

	// nao trabalhou mas tem direito ao dia feriado
	public double getRemuneracaoDiaFeriado() {
		double salarioFeriado = funcionario.getJornada() * funcionario.getValorHora();
		return salarioFeriado;
	}

	public String getInstantInicial() {
		return instantInicial;
	}

	public void setInstantInicial(String instantInicial) {
		this.instantInicial = instantInicial;
	}

	public String getInstantFinal() {
		return instantFinal;
	}

	public void setInstantFinal(String instantFinal) {
		this.instantFinal = instantFinal;
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

	public SemanaM getSemana() {
		return SemanaM.getSemana(semana);
	}

	public void setSemanaM(SemanaM semana) {
		if (semana != null) {
			this.semana = semana.getCodigo();
		}
	}

	public VericacaoPresenca getPresenca() {
		return VericacaoPresenca.getPresenca(presenca);
	}

	public void setVerificacaoPresenca(VericacaoPresenca presenca) {
		if (presenca != null) {
			this.presenca = presenca.getCode();
		}
	}

	public double getCalculoAdicionalNoturno() {
		double calculoAdicionalNoturno = ((funcionario.getValorHora() * getHorasDiurnas())
				* funcionario.getPercentualAdicionalNoturno()) / 100;
		System.out.println("calculo adicional " + calculoAdicionalNoturno);
		return calculoAdicionalNoturno;
	}

	public int getHorasDiurnas() {

		if (instantInicial.equalsIgnoreCase("null") || instantFinal.equalsIgnoreCase("null")) {
			return 0;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime horaIni = LocalDateTime.parse(instantInicial, formatter);
		LocalDateTime horaFim = LocalDateTime.parse(instantFinal, formatter);
		long horaI = horaIni.getHour();
		long horaF = horaFim.getHour();
		long minutoInicial = horaIni.getMinute();
		long minutoFinal = horaFim.getMinute();
		double retorno = 0;
		int verificacaoChaveI = HorariosCalculo.getChaveVerificacao(horaI);
		int verificacaoChaveF = HorariosCalculo.getChaveVerificacao(horaF);
		int somaMinutos = (int) (minutoFinal - minutoInicial);

		int resposta = 0;
		if (verificacaoChaveI >= 22 && verificacaoChaveI <= 30) {
			retorno = HorariosCalculo.getAdicionalChaves(horaI, horaF);
			resposta = 1;
		} else if (verificacaoChaveF >= 22 && verificacaoChaveF <= 30) {
			retorno = HorariosCalculo.getAdicionalChaves(horaI, horaF);
			resposta = 1;
		}

		double minutesNight = 0.0;
		if (resposta == 1) {
			minutesNight = ((retorno * 60) + somaMinutos);
		}

		int horaDiurna = 0;
		if (resposta == 1) {
			int aux = 0;
			for (int x = 1; x <= minutesNight; x++) {
				aux++;
				if (aux == 52) {
					horaDiurna++;
					aux = 0;
				}
			}
		}
		return horaDiurna;

	}

	public long getMinutosTrabalhados02() {

		if (instantInicial.equalsIgnoreCase("null") || instantFinal.equalsIgnoreCase("null")) {
			return 0;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime1 = LocalDateTime.parse(instantInicial, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(instantFinal, formatter);
		long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();
		return diffInMinutes;
	}

	public double getCalculoHorasExtras() {

		int j = funcionario.getJornada() * 60;
		double minutosTrabalhados = getMinutosTrabalhados02();
		if (minutosTrabalhados > j) {
			double calculo = minutosTrabalhados - j;
			double valorPer = (funcionario.getPorcentagemHorasExtras() * funcionario.getValorHora()) / 100;
			double salarioExtra = (((funcionario.getValorHora() + valorPer) / 60) * calculo) / 2;
			return salarioExtra;
		}
		return 0;
	}

	public double FormatacaoSalario(double salario) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		double d = Double.parseDouble(nf.format(salario).replace(',', '.'));
		return d;
	}

	public double getSalarioDia() {

		double salarioMinuto = 0.0;
		int feriado = getDataFeriados();
		if (feriado == 1 && !instantInicial.equalsIgnoreCase("null")) {
			double perc = ((funcionario.getPercentualFeriados() / 100) * funcionario.getValorHora());
			salarioMinuto = (funcionario.getValorHora() + perc) / 60;
		} else if (feriado == 1 && instantInicial.equalsIgnoreCase("null")) {
			return getRemuneracaoDiaFeriado();
		} else {
			salarioMinuto = (funcionario.getValorHora()) / 60;
		}
		double salarioDia = FormatacaoSalario(((getMinutosTrabalhados02()) * salarioMinuto) + 
				getCalculoHorasExtras() + getCalculoAdicionalNoturno());

		return salarioDia;
	}

	public void updateHorario(String horaInicial, String horaFinal) {

		// verificar hora formato correto e se inicial menor final
		if (instantInicial.equals("null") && instantFinal.equals("null")) {
			instantInicial = horaInicial;
			instantFinal = horaFinal;
		}
		if (!instantInicial.equals("null") && instantFinal.equals("null")) {
			instantFinal = horaFinal;

		}
	}

	public void updateFaltas(VericacaoPresenca v) {
		if (getPresenca().getCode() == 3 && v.getCode() != 1) {
			setVerificacaoPresenca(v);
		}
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
