package com.example.rh.entity.enums;

public enum VericacaoPresenca {
	
	Presente(1), FaltaJustificada(2), FaltaInjustificada(3);
	
	
	private int  cod;
	
	private  VericacaoPresenca(int cod) {
		this.cod = cod;
		
	}
	public int getCode() {
		return cod;
	}
	
	public static VericacaoPresenca getPresenca(int cod) {
		
		for(VericacaoPresenca value: VericacaoPresenca.values()) {
			if(value.getCode()==cod) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo faltas invalido");
		
	}
	

}
