package spring.mvc.session13.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session13.entity.Person;

@Controller
@RequestMapping("/session13/person")
public class PersonController {
	private List<Person> people = new CopyOnWriteArrayList<>(); // Person資料紀錄集合
	@GetMapping("/")
	public String index(Model model, @ModelAttribute Person person) {
		model.addAttribute("people", people); // 手動增加額外要給 view 呈現的資訊
		return "session13/person";
	}
	
	@PostMapping("/")
	public String add(Model model, @Valid Person person, BindingResult result) {
		if(result.hasErrors()) { // 若有錯誤發生，會自動將錯誤訊息傳導到指定 view 中
			model.addAttribute("people", people); // 手動增加額外要給 view 呈現的資訊
			return "session13/person";
		}
		people.add(person);
		return "redirect:./";
	}
}
