package com.appreportesconecta.domain;

import lombok.Data;

@Data
public class TM_Usuario {
	private String TM05SUSRNAM;
	private String TM04SROLDES;

	public Boolean isJefatura() {

		return this.TM04SROLDES.equals("ReporteCallJefatura");
	}

	public Boolean isRolGeneral() {

		return this.TM04SROLDES.equals("ReporteCallGeneral");
	}
}
