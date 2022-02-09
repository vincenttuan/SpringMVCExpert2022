package spring.mvc.session09.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lotto")
public class LottoController {
	
	private List<Set<Integer>> lottos = new ArrayList<>(); // 存放歷史紀錄
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "session09/lotto";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(Model model) {
		// 樂透 539：1~39 取出 5 個不重複的數字
		Set<Integer> lotto = getRandomLotto();
		// 將最新資料存入歷史紀錄中
		lottos.add(0, lotto); // 將最新資料放入首筆
		model.addAttribute("lotto", lotto); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 電腦選號歷史紀錄
		return "session09/lotto";
	}
	
	@RequestMapping(value = "/update/{index}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable("index") int index) {
		// 樂透 539：1~39 取出 5 個不重複的數字
		Set<Integer> lotto = getRandomLotto();
		lottos.set(index, lotto); // 更新指定位置的 lotto 號碼
		model.addAttribute("lotto", lotto); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 電腦選號歷史紀錄
		return "session09/lotto";
	}
	
	@RequestMapping(value = "/delete/{index}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("index") int index) {
		lottos.remove(index); // 移除指定位置(index)的資料
		model.addAttribute("lotto", null); // 無最新電腦選號
		model.addAttribute("lottos", lottos); // 電腦選號歷史紀錄
		return "session09/lotto";
	}
	
	private Set<Integer> getRandomLotto() {
		Random r = new Random();
		// 樂透 539：1~39 取出 5 個不重複的數字
		Set<Integer> lotto = new LinkedHashSet<>();
		while (lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1); // 1~39
		}
		return lotto;
	}
	
	@RequestMapping(value = "/stat", method = RequestMethod.GET)
	public String stat(Model model) {
		Map<Integer, Long> stat = getStatistics(); // 取得分組統計資料
		model.addAttribute("stat", stat); // 分組統計資料
		model.addAttribute("lottos", lottos); // 電腦選號歷史紀錄
		return "session09/lotto";
	}
	
	// 分組統計資料
	private Map<Integer, Long> getStatistics() {
		// 將所有資料彙集 (flatMap 資料拆散、collect 資料收集)
		List<Integer> nums = lottos.stream().flatMap(lotto -> lotto.stream()).collect(Collectors.toList());
		// 將資料分組 groupingBy
		Map<Integer, Long> stat = nums.stream()
									.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		// 加上排序
		Map<Integer, Long> statAndSort = new LinkedHashMap<>();
		stat.entrySet().stream()
			.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
			.forEachOrdered(e -> statAndSort.put(e.getKey(), e.getValue()));
		return statAndSort;
	}
	
	
}
