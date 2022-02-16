package spring.mvc.session15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/jdbc/job")
public class JobController {
	
	@Autowired
	private JobDao jobDao;
	
	@GetMapping("/create_table")
	@ResponseBody
	public String createTable() {
		return "create job table：" + jobDao.createTable();
	}
}





