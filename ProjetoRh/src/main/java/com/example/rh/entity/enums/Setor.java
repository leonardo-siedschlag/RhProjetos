package com.example.rh.entity.enums;

public enum Setor {

	Administração(1), Produção(2);

	private int codigo;
	

	private Setor(int codigo) {
		this.codigo = codigo;
		
	}

	public int getCodigo() {

		return codigo;
	}

	public static Setor getSetor(int codigo) {

		for (Setor value : Setor.values()) {
			if (value.getCodigo() == codigo) {
				return value;

			}
		}
		throw new IllegalArgumentException("Codigo setor invalido!");
	}

}
