package spring.mvc.session17.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session17.entity.Div;

@Controller
@RequestMapping("/session17/div")
public class DivController {
	
	@GetMapping("/")
	public String index(@ModelAttribute Div div, Model model) {
		System.out.println(model.asMap()); // 可以看到在 GlobalController 裡定義的 @ModelAttribute 全域資料
		return "session17/div";
	}

	@PostMapping("/")
	public String add(@ModelAttribute Div div, Model model) {
		int result = div.getX() / div.getY();
		div.setResult(result);
		model.addAttribute("result", div.getResult());
		return "session17/div";
	}
	
	// 捕獲使用者輸入資料格式不正確的例外
	@ExceptionHandler({BindException.class}) 
    public String fix(Exception ex, Model model, HttpServletRequest request){
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
        model.addAttribute("ex", ex);
        return "session17/error";
    }
	
}
