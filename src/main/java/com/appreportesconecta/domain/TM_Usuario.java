package com.appreportesconecta.domain;

import lombok.Data;

@Data
public class TM_Usuario {
	private String TM05SUSRNAM;
	private String TM04SROLDES;

	public Boolean isRolJefatura() {

		return this.TM04SROLDES.equals("ReporteCallJefatura");
	}

	public Boolean isRolGeneral() {

		return this.TM04SROLDES.equals("ReporteCallGeneral");
	}
	
	public Boolean isRolSupervisor() {

		return this.TM04SROLDES.equals("ReporteCallSupervisor");
	}
	
	public Boolean isRolAgente() {

		return this.TM04SROLDES.equals("ReporteCallAgente");
	}
	
	public String getTipoRol() {
		String tipoRol = "";
		if (this.isRolJefatura()) {
			tipoRol = "J";
		}else if (this.isRolGeneral()) {
			tipoRol = "GE";
		}else if (this.isRolAgente()) {
			tipoRol  ="G";
		} else if (this.isRolSupervisor()) {
			tipoRol = "S";
		}
		return tipoRol;
	}
}
