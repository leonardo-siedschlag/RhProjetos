package com.example.rh.entity.enums;

public enum DiaDescanso {
	
	segunda(1), terça(2) , quarta(3) , quinta(4) , sexta(5) , sabado(6) , domingo(7);
	
	
	private int   codigo;
	
	private DiaDescanso(int  codigo) {
		this.codigo = codigo;
		
	}
	public int  getCode() {
		return codigo;
	}
	
	public static DiaDescanso getDiaDescanso(int cod) {
		
		for(DiaDescanso  value: DiaDescanso.values()) {
			if(value.getCode()==(cod)) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo dia folga invalido!");
		
	}
	

}
