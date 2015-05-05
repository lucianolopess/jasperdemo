package com.abien.messageme.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.junit.BeforeClass;
import org.junit.Test;

public class JasperReportsWrapperTest {

	private static JasperReport jasperReport;
	private static JasperPrint jasperPrint;
	private static Connection connection;
	
	@BeforeClass
	public static void init() {
		String host = "localhost";
		int port = 5432;
		String dbname = "jasperdb9";
		String username = "postgres";
		String password = "postgres";
		ConnectionManager connectionManager = new ConnectionManager(host, port, dbname, username, password);
		connection = connectionManager.getConnection();
	}
	
	@Test
	public void testCompileJRXMLFile() throws Exception {
		JasperReportsWrapper wrapper = new JasperReportsWrapper();
		String jasperXMLFileName = JasperReportsWrapperTest.class.getResource(
				"/reports/cookbook/chapter08/Task1/MonthlyCustomerInvoices.jrxml").getPath();
		jasperReport = wrapper.compileJRXMLFile(jasperXMLFileName);
		assertNotNull(jasperReport);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testFillReport() throws Exception {
		JasperReportsWrapper wrapper = new JasperReportsWrapper();
		Map params = new HashMap();
		jasperPrint = wrapper.fillReport(jasperReport, params, connection);
		assertNotNull(jasperPrint);
//		JasperViewer.viewReport(jasperPrint);
//		JasperPrintManager.printReport(jasperPrint, true);
	}

	@Test
	public void testSaveReportInPDF() throws Exception {
		JasperReportsWrapper wrapper = new JasperReportsWrapper();
		String pdfFileName = "D:\\ProjetosBpms\\wizard\\src\\test\\resources\\reports\\"
				+ "cookbook\\chapter08\\Task1\\MonthlyCustomerInvoices.pdf";
		wrapper.saveReportInPDF(jasperPrint, pdfFileName);
	}
	
	@Test
	public void testSaveReportInXLS() throws Exception {
		JasperReportsWrapper wrapper = new JasperReportsWrapper();
		String pdfFileName = "D:\\ProjetosBpms\\wizard\\src\\test\\resources\\reports\\"
				+ "cookbook\\chapter08\\Task4\\MonthlyCustomerInvoices.xls";
		wrapper.saveReportInXLS(jasperPrint, pdfFileName);
	}
}
