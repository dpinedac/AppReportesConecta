
package com.appreportesconecta.bean;

import java.time.LocalDate;
import lombok.Data;


@Data
public class TT_Citas {

  LocalDate fechaFinGest;
  LocalDate fechaIniGest;
  String usuario;
  String moneda;
}
