package com.example.rh.entity.enums;

public enum DsrTipos {

	Mensalista(1), Horistas(2), Comissionista(3);

	private int codigo;

	private DsrTipos(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static DsrTipos getDsrTipos(int codigo) {

		for (DsrTipos  value : DsrTipos.values()) {
			if (value.getCodigo() == codigo) {
				return value;

			}
		}
		throw new IllegalArgumentException("Codigo descanço semanal remunerado invalido!");
	}

}
