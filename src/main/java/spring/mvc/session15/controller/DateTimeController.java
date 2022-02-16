package spring.mvc.session15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.repository.DateTimeDao;

@Controller
@RequestMapping("/jdbc/datetime")
public class DateTimeController {
	
	@Autowired
	private DateTimeDao dateTimeDao;
	
	@GetMapping("/now")
	@ResponseBody
	public String now() {
		return "now: " + dateTimeDao.now();
	}
	
}
