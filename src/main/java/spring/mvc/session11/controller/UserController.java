package spring.mvc.session11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session11.entity.User;

@Controller
@RequestMapping("/session11/user")
public class UserController {
	
	@GetMapping("/")
	public String userForm(Model model, @ModelAttribute User user) {
		model.addAttribute("_method", "POST");
		model.addAttribute("submitButtonName", "新增");
		return "session11/user";
	}
}
