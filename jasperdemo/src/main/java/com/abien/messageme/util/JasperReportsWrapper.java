package com.abien.messageme.util;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class JasperReportsWrapper {

	private static File file = new File("MonthlyCustomerInvoices.jrxml");
	public static String path2JRXMLFile = file.getAbsolutePath();
	public static String pdfFileName = "MonthlyCustomerInvoices.pdf";
	public static String dbServerAdd = "localhost";
	public static int dbServerPort = 5432;
	public static String dbName = "jasperdb9";
	public static String dbUser = "postgres";
	public static String dbPass = "postgres";

	private Connection connection = null;
	private ConnectionManager conManager = null;

	public JasperReportsWrapper() {
	}

	public Connection connect2DB(String host, int port, String dbname,
			String username, String password) {
		conManager = new ConnectionManager(host, port, dbname, username,
				password);
		connection = conManager.getConnection();
		return connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public JasperReport compileJRXMLFile(String jasperXMLFileName) {
		try {
			return JasperCompileManager.compileReport(jasperXMLFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JasperPrint fillReport(JasperReport jr, Map params, Connection con) {
		try {
			JasperPrint jp = JasperFillManager.fillReport(jr, params, con);
			return jp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JasperPrint fillReport(JasperReport jr, Map params) {
		try {
			if (connection != null) {
				JasperPrint jp = JasperFillManager.fillReport(jr, params,
						connection);
				return jp;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveReportInPDF(JasperPrint jasperPrint, String pdfFileName) {
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveReportInXLS(JasperPrint jasperPrint, String xlsFileName) {
		try {
			JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setParameter(
			JRExporterParameter.JASPER_PRINT, jasperPrint);
			xlsExporter.setParameter(
			JRExporterParameter.OUTPUT_FILE_NAME, xlsFileName);
			xlsExporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
