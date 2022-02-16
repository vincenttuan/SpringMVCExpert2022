package spring.mvc.session15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.entity.Job;
import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/session15/job")
public class JobController {
	
	@Autowired
	private JobDao jobDao;
	
	@GetMapping("/create_table")
	@ResponseBody
	public String createTable() {
		return "create job table：" + jobDao.createTable();
	}
	
	@GetMapping(value = "/query_json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Job> queryJson() {
		return jobDao.queryAll();
	}
	
	@GetMapping(value = "/query_json2", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Job> queryJson2() {
		return jobDao.queryAll2();
	}
	
	@GetMapping(value = "/query_json3", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Job> queryJson3() {
		return jobDao.queryAll3();
	}
}





