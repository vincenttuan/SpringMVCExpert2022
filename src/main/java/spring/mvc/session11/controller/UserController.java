package spring.mvc.session11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session11.entity.User;

@Controller
@RequestMapping("/session11/user")
public class UserController {
	private List<User> users = new ArrayList<>();
	
	@GetMapping("/")
	public String userForm(Model model, @ModelAttribute User user) {
		model.addAttribute("_method", "POST");
		model.addAttribute("submitButtonName", "新增");
		model.addAttribute("users", users);
		return "session11/user";
	}
	
	@PostMapping("/")
	public String add(@ModelAttribute User user) {
		users.add(user);
		return "redirect:./";
	}
	
	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, Model model) {
		User user = users.get(index);
		model.addAttribute("user", user);
		model.addAttribute("index", index);
		model.addAttribute("_method", "PUT");
		model.addAttribute("submitButtonName", "修改");
		model.addAttribute("users", users);
		return "session11/user";
	}
	
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, @ModelAttribute User user) {
		users.set(index, user);
		return "redirect:./";
	}
	
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		users.remove(index);
		return "redirect:./";
	}
	
}
