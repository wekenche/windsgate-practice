package com.example.demo.util;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperExportPdf {


	public void exportPdf(HttpServletResponse response,Map<String, Object> param,JRBeanCollectionDataSource dataSource,String classPath) throws Exception{
		response.setContentType("text/html");
		InputStream inputStream = this.getClass().getResourceAsStream(classPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource==null ? new JREmptyDataSource() : dataSource);
		
		response.setHeader("Content-Disposition", "inline; filename=report.pdf; charset=UTF-16LE");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
		response.getOutputStream());
				 
	}
	
	
}
