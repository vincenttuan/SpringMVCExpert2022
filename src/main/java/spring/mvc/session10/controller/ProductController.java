package spring.mvc.session10.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mvc.session10.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private List<Product> products = new ArrayList<>();
	
	// 商品輸入畫面(首頁)
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
	
	// 新增/修改商品-成功
	@GetMapping(value = {"/addOk", "/updateOk"})
	public String success() {
		return "session10/success";
	}
	
	// 取得商品
	@GetMapping("/get/{index}")
	public String get(Model model, @PathVariable("index") int index) {
		Product product = products.get(index);
		System.out.println(product);
		model.addAttribute("product", product);
		model.addAttribute("index", index);
		model.addAttribute("action", "update");
		return "session10/product_update";
	}
	
	// 修改商品
	@PostMapping("/update/{index}")
	public String update(RedirectAttributes attr, Model model, @PathVariable("index") int index,  Product product) {
		// 進行修改程序...
		products.set(index, product);
		// 將 product 資訊傳遞給 /updateOk 再傳給 success.jsp 顯示，可防止二次 submit
		attr.addFlashAttribute(product);
		return "redirect:../updateOk";
	}
	
	// 刪除商品
	@GetMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index) {
		// 進行刪除程序...
		products.remove(index);
		// .../mvc/product/ <-- 首頁位置
		// .../mvc/product/delete/1 <-- 刪除位置
		return "redirect:../"; // 重導到首頁
	}
	
	
}
