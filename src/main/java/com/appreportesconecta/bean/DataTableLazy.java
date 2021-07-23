package com.appreportesconecta.bean;

import lombok.Data;

@Data
public class DataTableLazy {

	Integer rows;
	Integer page;
	Integer totalRecords;
	String search;
	
	
}
