package spring.mvc.session15.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.repository.IEmployeeDao;

@Controller
@RequestMapping("/session15/employee")
public class EmployeeController {
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute Employee employee, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("action", "新增");
		model.addAttribute("employees", employeeDao.queryAll3());
		return "session15/employee";
	}
	
	@GetMapping("/{eid}")
	public String get(@PathVariable("eid") Integer eid, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("action", "修改");
		model.addAttribute("employee", employeeDao.get(eid));
		model.addAttribute("employees", employeeDao.queryAll3());
		return "session15/employee";
	}
	
	@PostMapping("/")
	public String add(@Valid Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("action", "新增");
			model.addAttribute("employee", employee);
			model.addAttribute("employees", employeeDao.queryAll3());
			return "session15/employee";
		}
		employeeDao.add(employee);
		return "redirect:./";
		
	}
	
	@PutMapping("/")
	public String update(@Valid Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("action", "修改");
			model.addAttribute("Employee", employee);
			model.addAttribute("employees", employeeDao.queryAll3());
			return "session15/employee";
		}
		employeeDao.update(employee);
		return "redirect:./";
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





