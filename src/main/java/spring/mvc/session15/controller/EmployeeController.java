package spring.mvc.session15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.repository.EmployeeDao;

@Controller
@RequestMapping("/session15/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute Employee employee, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("action", "新增");
		model.addAttribute("employees", employeeDao.queryAll3());
		return "session15/employee";
	}
	
	
	//-------------------------------------------------------------------------------
	
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
	
	@GetMapping(value = "/query_json2", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Employee> queryJson2() {
		return employeeDao.queryAll2();
	}
	
	@GetMapping(value = "/query_json3", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Employee> queryJson3() {
		return employeeDao.queryAll3();
	}
}





