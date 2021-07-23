package com.appreportesconecta.bean;

import lombok.Data;

@Data
public class DataTableLazyResponse {

	Object data;
	boolean status;
	Integer totalRecords;
	Integer totalFilter;
}
