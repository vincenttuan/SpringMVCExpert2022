package spring.mvc.session10.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ArrayDataModel;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mvc.session10.entity.Product;

@Controller
@RequestMapping("/product/rest")
public class ProductRestController {

	private List<Product> products = new ArrayList<>();

	// 查詢所有商品（商品輸入畫面首頁）
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("products", products);
		return "session10/rest/product";
	}

	// 查詢單一商品
	@GetMapping("/{index}")
	public String get(Model model, @PathVariable("index") int index) {
		Product product = products.get(index);
		System.out.println(product);
		model.addAttribute("product", product);
		model.addAttribute("index", index);
		return "session10/rest/product_update";
	}

	// 新增商品
	@PostMapping("/")
	public String add(Product product, RedirectAttributes attr) {
		// 進行新增程序...
		products.add(product);
		// 將 product 資訊傳遞給 /addOk 再傳給 success.jsp 顯示，可防止二次 submit
		attr.addFlashAttribute(product);
		return "redirect:addOk";
	}

	// 新增/修改商品-成功
	@GetMapping(value = { "/addOk", "/updateOk" })
	public String success() {
		return "session10/rest/success";
	}

	// 修改商品
	@PutMapping("/{index}")
	public String update(RedirectAttributes attr, Model model, @PathVariable("index") int index, Product product) {
		// 進行修改程序...
		products.set(index, product);
		// 將 product 資訊傳遞給 /updateOk 再傳給 success.jsp 顯示，可防止二次 submit
		attr.addFlashAttribute(product);
		return "redirect:updateOk";
	}

	// 刪除商品
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		// 進行刪除程序...
		products.remove(index);
		return "redirect:./"; // 重導到首頁
	}

}
