package spring.mvc.session09.controller;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lotto")
public class LottoController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "session09/lotto";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(Model model) {
		Random r = new Random();
		// 樂透 539：1~39 取出 5 個不重複的數字
		Set<Integer> lotto = new LinkedHashSet<>();
		while (lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1); // 1~39
		}
		model.addAttribute("lotto", lotto);
		return "session09/lotto";
	}
	
}
