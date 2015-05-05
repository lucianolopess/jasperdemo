package com.abien.messageme.business.blankreport.boundary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import org.jboss.seam.reports.Report;
import org.jboss.seam.reports.ReportCompiler;
import org.jboss.seam.reports.ReportDefinition;
import org.jboss.seam.reports.ReportRenderer;
import org.jboss.seam.reports.jasper.annotations.Jasper;
import org.jboss.seam.reports.output.PDF;
import org.jboss.solder.resourceLoader.Resource;

@Named
public class LandscapeReport {

	@Inject
	@Resource("reports/LandscapeReport.jrxml")
	InputStream sourceReport;
	
	@Inject
	@Jasper
	ReportCompiler compiler;
	
	@Jasper
	JRDataSource jasperDataSource;
	
	@Inject  
    @Jasper  
    @PDF  
    ReportRenderer pdfRenderer;  
	
	public byte[] generateLandscapeReport() throws IOException {
		ReportDefinition report = compiler.compile(sourceReport);
		Report reportInstance = report.fill(new JREmptyDataSource(), null);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		pdfRenderer.render(reportInstance, os);
		return os.toByteArray();
	}

}
