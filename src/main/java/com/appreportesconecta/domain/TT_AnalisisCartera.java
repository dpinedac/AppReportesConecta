package com.appreportesconecta.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TT_AnalisisCartera {
	
	public String DOCUMENTO;
	public Integer VOPERACION;
	public Integer TM08NCLIID;
	public String VUSUJEFE;
	public String VCANALACT;
	public String VSUPERVISOR;
	public String VGESTOR;
	public String NIVEL;
	public Integer ANOMES;
	public String TM07SCOSDES;
	public String TM01SENTABV;
	public LocalDate TM07DCOSVTA;
	public String NOMBRES;
	public String DIRECCION;
	public String DEPARTAMENTO;
	public String PROVINCIA;
	public String DISTRITO;
	public String MONEDA_DEUDA;
	public BigDecimal TM15NDEUCAPINI;
	public BigDecimal TM15NDEUCAPINI_SOL;
	public BigDecimal DEUCAPACT;
	public BigDecimal DEUCAPACTSOL;
	public String TG01SGENDES_ACC;
	public String TG01SGENDES_REAC;
	public String TG01SGENDES_CONT;
	public String TG01SGENDES_TIPCONT_ABR;
	public LocalDateTime TT01DGESFEC;
	public String TM05SUSRNAM_GEST;
	public Integer CALIFICACION;
	public BigDecimal TOTPAGDEU_MES;
	public Integer INTENSIDAD;
	public Integer FRECUENCIA;
	public Integer COBERTURA;
	public LocalDateTime FECHACREACION_NEG;
	public BigDecimal MONTO_NEG;
	public String SITUACION_NEG;
	public String GESTOR_NEG;
	public LocalDate FECHVENCI_NEG;

}
