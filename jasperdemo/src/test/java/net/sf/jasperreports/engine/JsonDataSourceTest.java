package net.sf.jasperreports.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;

import org.junit.Test;

public class JsonDataSourceTest {

	private String inputFileNameSubReport = "/reports/demo/jsondatasource/JsonOrdersReport.jrxml";
	private String inputFileNameReport = "/reports/demo/jsondatasource/JsonCustomersReport.jrxml";
	private String inputJsonData = "/data/northwind.json";

	@Test
	public void testFill() throws JRException, IOException, Exception {

		long start = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(JsonQueryExecuterFactory.JSON_DATE_PATTERN, "yyyy-MM-dd");
		params.put(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, "#,##0.##");
		params.put(JsonQueryExecuterFactory.JSON_LOCALE, Locale.ENGLISH);
		params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, getInputStreamData());
		params.put(JRParameter.REPORT_LOCALE, Locale.US);

		JasperCompileManager
				.compileReportToFile(getPath(JsonDataSourceTest.class
						.getResource(inputFileNameSubReport)));

		String masterReport = JasperCompileManager
				.compileReportToFile(getPath(JsonDataSourceTest.class
						.getResource(inputFileNameReport)));
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport,
				params);

		String rootDirectory = "/";
		rootDirectory = JsonDataSourceTest.class.getResource(rootDirectory)
				.getPath();
		File output = new File(rootDirectory, "output");
		output.mkdir();
		String outputFileName = "JsonCustomersReport.pdf";
		FileOutputStream out = new FileOutputStream(new File(output, outputFileName));
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		out.close();

		System.err.println("Filling time : "
				+ (System.currentTimeMillis() - start));

	}

	private Object getInputStreamData() {
		return JsonDataSourceTest.class.getResourceAsStream(inputJsonData);
	}

	private String getPath(URL url) throws URISyntaxException {
		URI uri = url.toURI();
		File file = new File(uri);
		return file.getPath();
	}

}
