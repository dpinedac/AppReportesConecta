package com.appreportesconecta.reportesView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.appreportesconecta.domain.TT_AnalisisCartera;

import pe.albatross.zelpers.file.excel.AbstractPOIExcelView;
import pe.albatross.zelpers.file.excel.ExcelHelper;
import pe.albatross.zelpers.file.excel.ExcelStyles;

@Component
public class ReporteAnalisisCarteraView extends AbstractPOIExcelView {

	@Override
	protected Workbook createWorkbook() {
		return new SXSSFWorkbook();
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TT_AnalisisCartera> citas = (List<TT_AnalisisCartera>) model.get("citas");

		this.crearExcel(workbook, citas);
		String fechaRep = " - " + new DateTime().toString("dd/MM/yyyy H:mm");

		String nombreReporte = "Reporte Analisis Cartera ";

		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreReporte + fechaRep + ".xlsx");

	}

	private void createHeader(String headers, Integer row, Sheet sheet, Workbook wb) {
		StringTokenizer st = new StringTokenizer(headers, "|");

		Integer col = 0;

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			this.createCell(row, col, token, sheet, ExcelStyles.getStyleHeader(wb));
			col++;
		}
	}

	private void crearExcel(Workbook wb, List<TT_AnalisisCartera> analisisCateras) {
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("Citas");
		SimpleDateFormat dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES"));
		this.createHeader(
				"Documento|Nro de operación|Codigo de Cliente|Jefe de Canal|Canal|Supervisor|Gestor|Nivel|AñoMes|Cosecha|Entidad|Fecha de Cosecha|Nombres|Dirección|Departamento|Provincia|Distrito|Moneda de Deuda|Capital Inicial|Capital Inicial Soles |Capital Actual|Capital Actual Soles|Acción|Reacción|Contacto|Tipo de Contacto|Fecha de Gestión|Usuario Gestión|Calificación|Pagos del Mes|Intensidad|Frecuencia|Cobertura|Fecha de Creación Negociacion|Monto de Negociación|Situación de Negociación|Gestor de negociación|Fecha de vencimiento de Negociacion",
				2, sheet, wb);
		Integer cont = 3;
		for (TT_AnalisisCartera analisis : analisisCateras) {
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getDOCUMENTO());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getVOPERACION());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTM08NCLIID());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getVUSUJEFE());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getVCANALACT());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getVSUPERVISOR());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getVGESTOR());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getNIVEL());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getANOMES());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTM07SCOSDES());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTM01SENTABV());
			ExcelHelper.replaceVal(sheet, cont, 0, dateTime.format(this.asDate(analisis.getTM07DCOSVTA())));
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getNOMBRES());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getDIRECCION());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getDEPARTAMENTO());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getPROVINCIA());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getDISTRITO());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getMONEDA_DEUDA());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTM15NDEUCAPINI());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTM15NDEUCAPINI_SOL());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getDEUCAPACT());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getDEUCAPACTSOL());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTG01SGENDES_ACC());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTG01SGENDES_REAC());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTG01SGENDES_CONT());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTG01SGENDES_TIPCONT_ABR());
			ExcelHelper.replaceVal(sheet, cont, 0, dateTime.format(this.asDate(analisis.getTT01DGESFEC())));
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTM05SUSRNAM_GEST());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getCALIFICACION());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getTOTPAGDEU_MES());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getINTENSIDAD());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getFRECUENCIA());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getCOBERTURA());
			ExcelHelper.replaceVal(sheet, cont, 0, dateTime.format(this.asDate(analisis.getFECHACREACION_NEG())));
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getMONTO_NEG());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getSITUACION_NEG());
			ExcelHelper.replaceVal(sheet, cont, 0, analisis.getGESTOR_NEG());
			ExcelHelper.replaceVal(sheet, cont, 0, dateTime.format(this.asDate(analisis.getFECHVENCI_NEG())));
			cont++;
		}
		for (int i = 0; i < 3; i++) {
			sheet.trackAllColumnsForAutoSizing();
			sheet.autoSizeColumn(i);
		}

	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	private void createCell(Integer cellRow, int cellNumber, String value, Sheet sheet, CellStyle style) {
		Row row = sheet.getRow(cellRow);
		if (row == null) {
			row = sheet.createRow(cellRow);
		}
		this.createCell(row, cellNumber, value, style);

	}

	private void createCell(Row row, int cellNumber, String value, CellStyle style) {
		Cell cell = row.createCell(cellNumber);
		cell.setCellValue(value + "");
		if (style != null) {
			cell.setCellStyle(style);
		}
	}

}
