package com.example.rh.entity.enums;

public enum StatusFuncionario {
	
	ATIVO(1), DEMITIDO(2);
	
	private int cod;
	
	private  StatusFuncionario(int cod) {
		this.cod = cod;
		
	}
	public int getCode() {
		return cod;
	}
	
	public static StatusFuncionario funcFuncionarioStatus(int cod) {
		
		for(StatusFuncionario value: StatusFuncionario.values()) {
			if(value.getCode() ==cod) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo invalido!");
		
	}
	

}
