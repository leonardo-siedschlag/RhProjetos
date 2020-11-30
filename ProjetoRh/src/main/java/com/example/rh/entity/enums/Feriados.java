package com.example.rh.entity.enums;

public enum Feriados {
	
	Natal("2020-10-23"), AnoNovoChines("2020-10-28");
	
	
	private String  cod;
	
	private  Feriados(String cod) {
		this.cod = cod;
		
	}
	public String getCode() {
		return cod;
	}
	
	public static int feriados(String cod) {
		
		for(Feriados value: Feriados.values()) {
			if(value.getCode().equals(cod)) {
				return 1;
			}
		}
		return 2;
		
	}
	

}
