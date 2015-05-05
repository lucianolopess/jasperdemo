package com.abien.messageme.presentation.blankreport;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import com.abien.messageme.business.blankreport.boundary.LandscapeReport;

@Named 
public class LandscapeReportPresenter {

	@Inject
	LandscapeReport landscapeReport;
	
	public void createReport() throws IOException {
		byte[] pdfData = landscapeReport.generateLandscapeReport();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extenalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) extenalContext.getResponse();
		
		response.reset();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=\"landscapeReport.pdf\"");
		
		OutputStream output = response.getOutputStream();
		output.write(pdfData);
		output.close();
		
		facesContext.responseComplete();
	}

}
