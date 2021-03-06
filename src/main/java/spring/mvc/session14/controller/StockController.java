package spring.mvc.session14.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mvc.session14.entity.Stock;
import spring.mvc.session14.validator.StockValidator;

@Controller
@RequestMapping("/session14/stock")
public class StockController {
	private List<Stock> stocks = new CopyOnWriteArrayList<>(); // Stock資料紀錄集合
	
	@Autowired
	private StockValidator stockValidator;
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute Stock stock) {
		model.addAttribute("stocks", stocks); // 手動增加額外要給 view 呈現的資訊
		return "session14/stock";
	}
	
	@PostMapping("/")
	public String add(Model model, @Valid Stock stock, BindingResult result) {
		// 自主驗證錯誤
		stockValidator.validate(stock, result);
		if(result.hasErrors()) { // 若有錯誤發生，會自動將錯誤訊息傳導到指定 view 中
			model.addAttribute("stocks", stocks); // 手動增加額外要給 view 呈現的資訊
			return "session14/stock";
		}
		stocks.add(stock);
		return "redirect:./";
	}
}
