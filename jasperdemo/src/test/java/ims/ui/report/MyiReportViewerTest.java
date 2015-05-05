package ims.ui.report;

import org.junit.Test;

public class MyiReportViewerTest {

	@Test
	public void testFillReport() {
		String jasperXMLFileName = MyiReportViewerTest.class.getResource(
				"/reports/ireports37/chapter10/MyListOfProductWithImage.jrxml").getPath();
		MyiReportViewer myiReportViewer = new MyiReportViewer(jasperXMLFileName);
		myiReportViewer.setVisible(true);
	}
	
}
