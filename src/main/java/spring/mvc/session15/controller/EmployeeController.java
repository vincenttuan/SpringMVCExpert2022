package spring.mvc.session15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.repository.EmployeeDao;

@Controller
@RequestMapping("/jdbc/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/create_table")
	@ResponseBody
	public String createTable() {
		return "create employee table：" + employeeDao.createTable();
	}
	
	@GetMapping(value = "/query_json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Employee> queryJson() {
		return employeeDao.queryAll();
	}
}





