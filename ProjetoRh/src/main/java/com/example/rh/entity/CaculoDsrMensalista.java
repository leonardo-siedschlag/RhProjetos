package com.example.rh.entity;

import java.text.NumberFormat;

import com.example.rh.entity.enums.SemanaM;

public class CaculoDsrMensalista {

	public static int getCalculoVerificacao(double minutosTrabalhadosSemanal, Funcionario funcionario, int tolerance) {
		
		double horaSemanal = funcionario.getHorasSemanais();
		double conversaoMinutosHoras = (minutosTrabalhadosSemanal + tolerance) / 60;
		if (conversaoMinutosHoras >= horaSemanal) {
			return 1;
		}

		return 0;
	}

	public static double getMinutosTrabalhados(SemanaM semana, SemanaM codigo, CartaoPonto ponto,
			Funcionario funcionario) {

		double minutosTrabalhadosSemanal = 0.0;
		if (ponto.getDataFeriados() == 1 && semana == codigo) {
			minutosTrabalhadosSemanal = funcionario.getJornada() * 60;
		} else if (semana == codigo && ponto.getPresenca().getCode() == 1) {
			minutosTrabalhadosSemanal = ponto.getMinutosTrabalhados02();
		} else if (semana == codigo && ponto.getPresenca().getCode() == 2) {
			minutosTrabalhadosSemanal = funcionario.getJornada() * 60;
		}
		
		return minutosTrabalhadosSemanal;
	}

	public static double FormatacaoSalario(double salario) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		double d = Double.parseDouble(nf.format(salario).replace(',', '.'));
		return d;
	}
	
	public static int getSemanaFeriado(SemanaM semana, SemanaM semanaPonto, CartaoPonto ponto) {
		
		if(semana==semanaPonto && ponto.getDataFeriados()==1 ) {
			return 1;
		}
		return 0;
	}
	

}
