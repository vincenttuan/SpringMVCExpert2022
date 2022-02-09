package spring.mvc.session10.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ArrayDataModel;

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
	
	private List<Product> products = new ArrayList<>();
	
	// 商品輸入畫面
	@GetMapping("/")  // 相當於 @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("products", products);
		return "session10/product";
	}
	
	// 新增商品
	@PostMapping("/add")
	public String add(Product product, RedirectAttributes attr) {
		// 進行新增程序...
		products.add(product);
		// 將 product 資訊傳遞給 /addOk 再傳給 success.jsp 顯示，可防止二次 submit
		attr.addFlashAttribute(product);
		return "redirect:addOk";
	}
	
	// 新增商品-成功
	@GetMapping("/addOk")
	public String addOk() {
		return "session10/success";
	}
}
