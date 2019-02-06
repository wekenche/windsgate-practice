package com.example.demo.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationContext appContext;
	
	@GetMapping("/sample")
	public String sample() {
		
		
		return "sample";
	}
	@GetMapping("/report")
	public void report(HttpServletResponse  response) throws Exception{
		//JasperReportDataManager jasper = new JasperReportDataManager();
		response.setContentType("text/html");
		System.out.println(userService.findAll().toString());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userService.findAll());
		InputStream inputStream = this.getClass().getResourceAsStream("/report/sampleReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		
		response.setHeader("Content-Disposition", "inline; filename=report.pdf; charset=UTF-16LE");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
		response.getOutputStream());
				 
	}
	
	@GetMapping("/report1")
	public ModelAndView report1(){
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/report/sampleReport.jrxml");
		view.setApplicationContext(appContext);
		Map<String,Object> map = new HashMap<>();
		map.put("datasource", userService.findAll());
		
		return new ModelAndView(view,map);		 
	}

	@GetMapping("/save")
	public String save(@ModelAttribute User user) throws Exception{
		List<User> listUser = new ArrayList<User>();
		User user1 = new User("name1","address","contact","good");
		User user2 = new User("name2","address2","contact2","bad");
		User user3 = new User("name3","address3","contact3","good");
		listUser.add(user1);
		listUser.add(user2);
		listUser.add(user3);
		userService.save(listUser);
		return "redirect:/sample";
	}
	
}
