package com.example.rh.entity;

public class CalculoSalarioFuncionario {

	public static double getTabelaCalculoInssFuncionario(double salarioBruto) {

		double salarioDInss = 0.0;
		if (salarioBruto >= 0 && salarioBruto <= 200) {
			salarioDInss = (salarioBruto * 8) / 100;
		} else if (salarioBruto > 200 && salarioBruto <= 300) {
			salarioDInss = (salarioBruto * 9) / 100;
		} else if (salarioBruto > 300 && salarioBruto <= 500) {
			salarioDInss = (salarioBruto * 11) / 100;
		}
		return salarioDInss;
	}

	public static double getTabelaCalculoIRRF_Funcionario(double salarioDescontoInss) {
		
		double licotaIrrf = 0.0;
		if (salarioDescontoInss >= 0 && salarioDescontoInss <= 100) {
			licotaIrrf = 0.0;
		} else if (salarioDescontoInss > 100 && salarioDescontoInss <= 200) {
			double perc = (salarioDescontoInss * 7.5) / 100;
			licotaIrrf = perc - 2.09;
		} else if (salarioDescontoInss > 200 && salarioDescontoInss <= 300) {
			double perc = (salarioDescontoInss * 15) / 100;
			licotaIrrf = perc - 4.80;
		} else if (salarioDescontoInss > 300 && salarioDescontoInss <= 400) {
			double perc = (salarioDescontoInss * 22.5) / 100;
			licotaIrrf = perc - 6.13;
		} else if (salarioDescontoInss > 400 && salarioDescontoInss <= 500) {
			double perc = (salarioDescontoInss * 27.5) / 100;
			licotaIrrf = perc - 9.36;
		}

		return licotaIrrf;

	}

}
