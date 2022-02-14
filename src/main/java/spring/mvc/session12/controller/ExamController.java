package spring.mvc.session12.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session12.entity.Exam;

@Controller
@RequestMapping("/exam")
public class ExamController {
	// CopyOnWriteArrayList 是執行緒安全的集合-適合多執行緒操作
	// ArrayList 是非執行緒安全的集合-適合單緒操作
	// 因為預設的情況下 Spring 會將 ExamController 定義為 Singleton（單一實體）
	// 所以可以使用 CopyOnWriteArrayList 來作為多執行緒資料操作的集合類
	private List<Exam> exams = new CopyOnWriteArrayList<>(); // 註冊考試的紀錄集合
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute Exam exam) {
		model.addAttribute("_method", "POST");
		model.addAttribute("exams", exams); // 目前所有註冊考試的紀錄
		model.addAttribute("action", "新增"); // 設定 form submit button 上的文字
		return "session12/exam";
	}
	
	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") String id) {
		Optional<Exam> optExam = exams.stream()
							.parallel()
							.filter(e -> e.getId().equals(id)).findFirst();
		if(optExam.isPresent()) {
			Exam exam = optExam.get();
			model.addAttribute("_method", "PUT");
			model.addAttribute("exam", exam); // 手動設定將指定的 exam 傳給 view
			model.addAttribute("exams", exams); // 目前所有註冊考試的紀錄
			model.addAttribute("action", "修改"); // 設定 form submit button 上的文字
			return "session12/exam";
		}
		// 若上述 if 不成立，則重導到首頁
		return "redirect:./";
		
	}
	
	@PostMapping("/")
	public String add(Exam exam) {
		exams.add(exam);
		return "redirect:./";
	}
	
	
	
}
