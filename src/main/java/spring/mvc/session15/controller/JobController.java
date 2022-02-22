package spring.mvc.session15.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session15.entity.Job;
import spring.mvc.session15.repository.IEmployeeDao;
import spring.mvc.session15.repository.IJobDao;

@Controller
@RequestMapping("/session15/job")
public class JobController {
	
	@Autowired
	private IJobDao jobDao;
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute Job job, Model model, HttpSession httpSession) {
		model.addAttribute("_method", "POST");
		model.addAttribute("jobs", jobDao.query(httpSession.getAttribute("offset_job")));
		model.addAttribute("pageCount", jobDao.getCount()/IJobDao.LIMIT+1);
		model.addAttribute("employees", employeeDao.query(null));
		return "session15/job";
	}
	
	@GetMapping("/page/{num}")
	public String page(@PathVariable("num") int num, HttpSession httpSession) {
		if(num >= 0) {
			httpSession.setAttribute("offset_job", (num-1) * IJobDao.LIMIT); // offset 要 -1, 因為 offset 是從 0 開始
		} else {
			httpSession.removeAttribute("offset_job");
		}
		return "redirect:../";
	}
	
	@GetMapping("/{jid}")
	public String get(@PathVariable("jid") Integer jid, Model model, HttpSession httpSession) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("job", jobDao.get(jid));
		model.addAttribute("jobs", jobDao.query(httpSession.getAttribute("offset_job")));
		model.addAttribute("pageCount", jobDao.getCount()/IJobDao.LIMIT+1);
		model.addAttribute("employees", employeeDao.query(null));
		return "session15/job";
	}
	
	@PostMapping("/")
	public String add(@Valid Job job, BindingResult result, Model model, HttpSession httpSession) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("job", job);
			model.addAttribute("jobs", jobDao.query(httpSession.getAttribute("offset_job")));
			model.addAttribute("pageCount", jobDao.getCount()/IJobDao.LIMIT+1);
			model.addAttribute("employees", employeeDao.query(null));
			return "session15/job";
		}
		jobDao.add(job);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid Job job, BindingResult result, Model model, HttpSession httpSession) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("job", job);
			model.addAttribute("jobs", jobDao.query(httpSession.getAttribute("offset_job")));
			model.addAttribute("pageCount", jobDao.getCount()/IJobDao.LIMIT+1);
			model.addAttribute("employees", employeeDao.query(null));
			return "session15/job";
		}
		jobDao.update(job);
		return "redirect:./";
	}
	
	@DeleteMapping("/")
	public String delete(Job job) {
		jobDao.delete(job.getJid());
		return "redirect:./";
	}
	
	//-------------------------------------------------------------------------------
	
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





