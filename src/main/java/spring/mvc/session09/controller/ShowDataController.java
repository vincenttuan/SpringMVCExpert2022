package spring.mvc.session09.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/showdata")
public class ShowDataController {

	@RequestMapping("/case1")
	public ModelAndView case1() {
		String data = "Hello ModelAndView 1"; // 資料 (Model)
		String view = "/show_data.jsp"; // 渲染地 (View)
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", data);
		mav.setViewName(view);
		return mav;
	}

	@RequestMapping("/case2")
	public ModelAndView case2() {
		String data = "Hello ModelAndView 2"; // 資料 (Model)
		String view = "/show_data.jsp"; // 渲染地 (View)
		return new ModelAndView(view, "data", data);
	}

	@RequestMapping("/case3")
	public ModelAndView case3() {
		String data1 = "Hello ModelAndView 1"; // 資料 (Model)
		String data2 = "Hello ModelAndView 2"; // 資料 (Model)
		String view = "/show_data.jsp"; // 渲染地 (View)
		return new ModelAndView(view, "data1", data1).addObject("data2", data2);
	}

	// 指派到 /WEB-INF 下的 view
	@RequestMapping("/case4")
	public ModelAndView case4() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", "Hello ModelAndView");
		mav.addObject("data1", "Hello ModelAndView 1");
		mav.addObject("data2", "Hello ModelAndView 2");
		mav.setViewName("/WEB-INF/views/session09/show_data.jsp");
		return mav;
	}

	// 利用 InternalResourceViewResolver 配置簡化
	@RequestMapping("/case5")
	public ModelAndView case5() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", "Hello ModelAndView");
		mav.addObject("data1", "Hello ModelAndView 1");
		mav.addObject("data2", "Hello ModelAndView 2");
		mav.setViewName("session09/show_data");
		return mav;
	}

	// SpringMVC Controller 預設的字串返回會去匹配 view 的位置
	// 但是不可加上 @ResponseBody
	@RequestMapping("/case6")
	public String case6() {
		return "session09/show_data";
	}

	// 宣告 Model 參數，調用 addAttribute() 方法即可帶入資料
	@RequestMapping("/case7")
	public String case7(Model model) {
		model.addAttribute("data", "Hello ModelAndView");
		model.addAttribute("data1", "Hello ModelAndView 1");
		model.addAttribute("data2", "Hello ModelAndView 2");
		return "session09/show_data";
	}

	// 返回數組/集合資料：List、Map
	@RequestMapping("/case8")
	public String case8(Model model) {
		List<String> names = Arrays.asList("John", "Mary", "Helen");
		Map<String, Integer> fruits = new LinkedHashMap<>();
		fruits.put("apple", 50);
		fruits.put("orange", 30);

		model.addAttribute("data", "我是字串");
		model.addAttribute("data1", names);
		model.addAttribute("data2", fruits);

		return "session09/show_data";
	}

	// 重定向 redirect:
	// Client網址會改變，重定向是由 server 發出重定向命令，並由 client 去執行
	// 重定向可以指向內網與外網位置
	@RequestMapping("/case9")
	public String case9() {
		return "redirect:/index.jsp"; // 指向內網
	}

	@RequestMapping("/case10")
	public String case10() {
		return "redirect:http://tw.yahoo.com"; // 指向外網
	}

	// 重定向 redirect: 帶參數 I
	@RequestMapping("/case11")
	public String case11() {
		return "redirect:/hello.jsp?username=Vincent&age=18";
	}

	// 重定向 redirect: 帶參數 II
	@RequestMapping("/case12")
	public String case12(RedirectAttributes attr) {
		attr.addAttribute("username", "Vincent");
		attr.addAttribute("age", 18);
		return "redirect:/hello.jsp";
	}
	
	// 重定向 redirect: 帶參數 II
	@PostMapping("/case13")
	public String case13(RedirectAttributes attr) {
		attr.addAttribute("username", "Vincent");
		attr.addFlashAttribute("age", 18); // addFlashAttribute 不會加到網址列後面而是放在 Spring 的 Session 物件中 
		return "redirect:case14";
	}
	
}
