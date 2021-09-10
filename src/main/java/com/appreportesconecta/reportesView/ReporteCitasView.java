package com.appreportesconecta.reportesView;

import java.text.SimpleDateFormat;
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

import com.appreportesconecta.domain.TT_CitasTelPre;

import pe.albatross.zelpers.file.excel.AbstractPOIExcelView;
import pe.albatross.zelpers.file.excel.ExcelHelper;
import pe.albatross.zelpers.file.excel.ExcelStyles;

@Component
public class ReporteCitasView extends AbstractPOIExcelView {

	@Override
	protected Workbook createWorkbook() {
		return new SXSSFWorkbook();
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TT_CitasTelPre> citas = (List<TT_CitasTelPre>) model.get("citas");

		this.crearExcel(workbook, citas);
		String fechaRep = " - " + new DateTime().toString("dd/MM/yyyy H:mm");

		String nombreReporte = "Reporte Citas ";

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

	private void crearExcel(Workbook wb, List<TT_CitasTelPre> citas) {
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("Citas");
		SimpleDateFormat dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES"));
		this.createHeader(
				"Documento|Operación|Cosecha|Moneda|Capital Inicial|Capital Inicial Soles|Reacción|Contacto|Tipo Contacto|Canal|Jefe de Canal|Supervisor|Gestor|Nivel|Gestion Gestión|Observación|Teléfono|Campaña|Fecha de Gestión|Fecha de Cita|Calificación|Monto Pago Mes|Ultima Situación de Negociación|Intesidad|Frecuencia|Resultado Acción Cita|Resultado Reacción Cita|Resultado Contacto Cita|Resultado Observación Cita|Resultado Telefono Cita",
				2, sheet, wb);
		Integer cont = 3;
		for (TT_CitasTelPre cita : citas) {
			ExcelHelper.replaceVal(sheet, cont, 0, cita.getDOCUMENTO());
			ExcelHelper.replaceVal(sheet, cont, 1, cita.getTM15NDEUOPE());
			ExcelHelper.replaceVal(sheet, cont, 2, cita.getTM07SCOSDES());
			ExcelHelper.replaceVal(sheet, cont, 3, cita.getMONEDADEUDA());
			ExcelHelper.replaceVal(sheet, cont, 4, cita.getTM15NDEUCAPINI());
			ExcelHelper.replaceVal(sheet, cont, 5, cita.getTM15NDEUCAPINI_SOL());
			ExcelHelper.replaceVal(sheet, cont, 6, cita.getTG01SGENDES_REACT());
			ExcelHelper.replaceVal(sheet, cont, 7, cita.getTG01SGENDES_CONT());
			ExcelHelper.replaceVal(sheet, cont, 8, cita.getTG01SGENDES_TIPCONT_ABR());
			ExcelHelper.replaceVal(sheet, cont, 9, cita.getCANAL());
			ExcelHelper.replaceVal(sheet, cont, 10, cita.getVUSUJEFE());
			ExcelHelper.replaceVal(sheet, cont, 11, cita.getVSUPERVISOR());
			ExcelHelper.replaceVal(sheet, cont, 12, cita.getVGESTOR());
			ExcelHelper.replaceVal(sheet, cont, 13, cita.getNIVEL());
			ExcelHelper.replaceVal(sheet, cont, 14, cita.getGESTORGESTION());
			ExcelHelper.replaceVal(sheet, cont, 15, cita.getTT01SGESOBS());
			ExcelHelper.replaceVal(sheet, cont, 16, cita.getTM09SDTCVAL_TEL());
			ExcelHelper.replaceVal(sheet, cont, 17, cita.getNOMCAMPANA());
			ExcelHelper.replaceVal(sheet, cont, 18, dateTime.format(this.asDate(cita.getTT01DGESFEC())));
			ExcelHelper.replaceVal(sheet, cont, 19, dateTime.format(this.asDate(cita.getTT01DGESCIT())));
			ExcelHelper.replaceVal(sheet, cont, 20, cita.getCALIFICACION());
			ExcelHelper.replaceVal(sheet, cont, 21, cita.getTT03NMONTOTOTPAGO_MES());
			ExcelHelper.replaceVal(sheet, cont, 22, cita.getTG01SGENDES_ULT_SIT_NEG());
			ExcelHelper.replaceVal(sheet, cont, 23, cita.getINTENSIDAD());
			ExcelHelper.replaceVal(sheet, cont, 24, cita.getFRECUENCIA());
			ExcelHelper.replaceVal(sheet, cont, 25, cita.getACCIONRESULCIT());
			ExcelHelper.replaceVal(sheet, cont, 26, cita.getREACCIONRESULCIT());
			ExcelHelper.replaceVal(sheet, cont, 27, cita.getCONTACTORESULCIT());
			ExcelHelper.replaceVal(sheet, cont, 28, cita.getOBSERVACIONRESULCIT());
			ExcelHelper.replaceVal(sheet, cont, 29, cita.getTELEFONORESULCIT());
			cont++;
		}
		for (int i = 0; i < 29; i++) {
			sheet.trackAllColumnsForAutoSizing();
			sheet.autoSizeColumn(i);
		}

	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
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
