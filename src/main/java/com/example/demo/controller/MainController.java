package com.example.demo.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.example.demo.model.Department;
import com.example.demo.model.Supply;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;
import com.example.demo.service.SupplyService;
import com.example.demo.service.UserService;
import com.example.demo.util.View;
import com.fasterxml.jackson.annotation.JsonView;

import net.sf.jasperreports.engine.JREmptyDataSource;
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
	private SupplyService supplyService;
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService cat;
	
	@Autowired
	private ApplicationContext appContext;
	
	@GetMapping("/sample")
	public String sample() {
		
		System.out.println("sample");
		return "sample";
	}
	@GetMapping("/report/user")
	public void report(HttpServletResponse  response) throws Exception{
		//JasperReportDataManager jasper = new JasperReportDataManager();
		response.setContentType("text/html");
		System.out.println(userService.findAll().toString());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userService.findAll());
		InputStream inputStream = this.getClass().getResourceAsStream("/report/sampleReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> param = new HashMap<>();
		param.put("title","User List");
		param.put("user",new JRBeanCollectionDataSource(userService.findAll()));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JREmptyDataSource());
		
		response.setHeader("Content-Disposition", "inline; filename=report.pdf; charset=UTF-16LE");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
		response.getOutputStream());
				 
	}
	
	@GetMapping("/report/department")
	public void reportDepartment(HttpServletResponse  response)throws Exception{
		
//		response.setContentType("text/html");
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userService.getAllDepartment());
//		InputStream inputStream = this.getClass().getResourceAsStream("/report/department.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//		Map<String, Object> param = new HashMap<>();
//		param.put("title","department List");
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
//		
//		response.setHeader("Content-Disposition", "inline; filename=department"+new Date()+".pdf; charset=UTF-16LE");
//		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
//		JasperExportManager.exportReportToPdfStream(jasperPrint,
//		response.getOutputStream());
	}
	
	@GetMapping("/report/supply")
	public void generateSupply(HttpServletResponse  response)throws Exception{
		response.setContentType("text/html");
		List<Supply> supList = supplyService.findAll();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(supList);
		InputStream inputStream = this.getClass().getResourceAsStream("/report/supply/supply.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> param = new HashMap<>();
		param.put("title","department List");
		param.put("supplyParam", new JRBeanCollectionDataSource(supList));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
		
		response.setHeader("Content-Disposition", "inline; filename=department"+new Date()+".pdf; charset=UTF-16LE");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
		response.getOutputStream());
	}
	
	@GetMapping("/report/supply2")
	public ModelAndView generateSupply2(HttpServletResponse  response)throws Exception{
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/report/supply2/supply2.jrxml");
		view.setApplicationContext(appContext);
		Map<String,Object> param = new HashMap<>();
		param.put("datasource", itemService.findAll());
		return new ModelAndView(view,param);
	}
	
	@GetMapping("/item/list")
	public @ResponseBody List<?> getItems(HttpServletResponse  response)throws Exception{
		return itemService.findAll();
	}
	
	@GetMapping("/category/list")
	public @ResponseBody List<?> getCategory(HttpServletResponse  response)throws Exception{
		return cat.findAll();
	}
	
	@GetMapping("/supply/list")
	public @ResponseBody List<?> getsupply(HttpServletResponse  response)throws Exception{
		return supplyService.findAll();
	}
	
	@PutMapping("/supply/save")
	public @ResponseBody Supply saveSupply(@RequestBody Supply supply) {
		return supplyService.save(supply);
	}
	
	@PostMapping("/department/save")
	public @ResponseBody Department saveDepartment(@RequestBody Department department) {
		return userService.saveDepartment(department);
	}
	@JsonView(View.Public.class)
	@GetMapping("/department/list")
	public @ResponseBody Page<?> getDepartment(Pageable pageable){
		
		return userService.getAllDepartment(pageable);
	}
	
	@JsonView(View.Public.class)
	@GetMapping("/user/list")
	public @ResponseBody List<?> getUser(){
		/*SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		 filterProvider.addFilter("userFilter",
	             SimpleBeanPropertyFilter.filterOutAllExcept("department", "name"));*/
		List<User> department = userService.findAll();
		System.out.println(department.toString());
		return department;
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
