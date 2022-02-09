package spring.mvc.session10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mvc.session10.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	// 商品輸入畫面
	@GetMapping("/")  // 相當於 @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("action", "add");
		return "session10/product";
	}
	
	// 新增商品
	@PostMapping("/add")
	public String add(Product product, RedirectAttributes attr) {
		// 進行新增程序...
		
		attr.addFlashAttribute(product);
		return "redirect:addOk";
	}
	
	// 新增商品-成功
	@GetMapping("/addOk")
	public String addOk() {
		return "session10/success";
	}
}
