package com.example.rh.entity;
import java.util.HashMap;
import java.util.Map;

public class HorariosCalculo {
	public static Map<Integer, String> horarioNoturno() {

		Map<Integer, String> horarios = new HashMap<Integer, String>();
		horarios.put(12, new String("12"));
		horarios.put(13, new String("13"));
		horarios.put(14, new String("14"));
		horarios.put(15, new String("15"));
		horarios.put(16, new String("16"));
		horarios.put(17, new String("17"));
		horarios.put(18, new String("18"));
		horarios.put(19, new String("19"));
		horarios.put(20, new String("20"));
		horarios.put(21, new String("21"));
		horarios.put(22, new String("22"));
		horarios.put(23, new String("23"));
		horarios.put(24, new String("0"));
		horarios.put(25, new String("1"));
		horarios.put(26, new String("2"));
		horarios.put(27, new String("3"));
		horarios.put(28, new String("4"));
		horarios.put(29, new String("5"));
		horarios.put(30, new String("6"));

		return horarios;
	}

	public static int funcao(int chave) {
		int c = 0;

		for (int i = chave; i < 22; i++) {
			c++;
		}

		return c;
	}

	public static int getAdicionalChaves(long horaIni, long horaFim) {
		
		HashMap<Integer, String> Horario = new HashMap<Integer, String>();
		Horario.putAll(horarioNoturno());
		String HoraI = Long.toString(horaIni);
		String HoraF = Long.toString(horaFim);
		int chave1 = 0;
		int chave2 = 0;
		int intervaloNaoAdicional = 0;
		for (Map.Entry<Integer, String> pair : Horario.entrySet()) {
			if (pair.getValue().equalsIgnoreCase(HoraI)) {
				chave1 = pair.getKey();
				if (chave1 < 22) {
					intervaloNaoAdicional = funcao(chave1);
				}
			}
			if (pair.getValue().equalsIgnoreCase(HoraF)) {
				chave2 = pair.getKey();
			}
		}
		int intervalosHorasNormal = chave2 - chave1;
		int intervaloAdicional = Math.abs(intervalosHorasNormal - intervaloNaoAdicional);
		return intervaloAdicional;
	}

	public static int getChaveVerificacao(long hora) {
		HashMap<Integer, String> Horario = new HashMap<Integer, String>();
		Horario.putAll(horarioNoturno());
		String Hora = Long.toString(hora);
		int chave = 0;

		for (Map.Entry<Integer, String> pair : Horario.entrySet()) {
			if (pair.getValue().equalsIgnoreCase(Hora)) {
				chave = pair.getKey();
			}
		}

		return chave;

	}

}
