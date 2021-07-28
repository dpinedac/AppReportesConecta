package com.appreportesconecta.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.Data;

@Data
public class TT_CitasTelPre {

	public String DOCUMENTO;
	public Long TM15NDEUOPE;
	public String TM07SCOSDES;
	public String MONEDADEUDA;
	public BigDecimal TM15NDEUCAPINI;
	public BigDecimal TM15NDEUCAPINI_SOL;
	public String TG01SGENDES_REACT;
	public String TG01SGENDES_CONT;
	public String TG01SGENDES_TIPCONT_ABR;
	public String CANAL;
	public String VSUPERVISOR;
	public String VGESTOR;
	public String NIVEL;
	public String GESTORGESTION;
	public String TT01SGESOBS;
	public String TM09SDTCVAL_TEL;
	public String NOMCAMPANA;
	public LocalDateTime TT01DGESFEC;
	public LocalDateTime TT01DGESCIT;
	public String CALIFICACION;
	public BigDecimal TT03NMONTOTOTPAGO_MES;
	public String TG01SGENDES_ULT_SIT_NEG;
	public Integer INTENSIDAD;
	public Integer FRECUENCIA;
	public String ACCIONRESULCIT;
	public String REACCIONRESULCIT;
	public String CONTACTORESULTCIT;
	public String OBSERVACIONRESULCIT;
	public String TELEFONORESULCIT;
	
	

}
