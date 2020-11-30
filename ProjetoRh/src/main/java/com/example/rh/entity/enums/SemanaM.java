package com.example.rh.entity.enums;

public enum SemanaM {

	PrimeiraSemana(1), SegundaSemana(2), TerceiraSemana(3), QuartaSemana(4);

	private int codigo;
	

	private SemanaM(int codigo) {
		this.codigo = codigo;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public static SemanaM getSemana(int codigo) {

		for (SemanaM value : SemanaM.values()) {
			if (value.getCodigo() == codigo) {
				return value;

			}
		}
		throw new IllegalArgumentException("Codigo semana invalido!");
	}

}
