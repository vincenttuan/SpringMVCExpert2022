package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.entity.User;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	// Base 路徑：http://localhost:8080/spring.mvc/mvc
	/*
	 * 1. 取得字串資料
	 * 子路徑：/hello/welcome
	 * 完整路徑 = Base 路徑 + 子路徑
	 */
	@RequestMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome String MVC !";
	}
	
	/*
	 * 2. ?後帶入參數 @RequestParam
	 * 子路徑：/hello/sayhi?name=Vincent&age=18
	 */
	@RequestMapping(value = "/sayhi")
	@ResponseBody
	public String sayhi(@RequestParam(value = "name") String name,
						@RequestParam(value = "age") Integer age) {
		
		return String.format("Hi %s %d", name, age);
	}
	
	/*
	 * 3. ?後帶入參數並計算 (Lab 練習)
	 * 子路徑：/hello/bmi?h=170.0&w=60.0
	 */
	// 請設計方法 api, 結果會得到 bmi = 20.76
	@RequestMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h") Double h, 
					  @RequestParam(value = "w") Double w) {
		Double bmi = w / Math.pow(h/100, 2);
		return String.format("bmi = %.2f", bmi);
	}
	
	/*
	 * 4. 路徑參數並計算 @PathVariable
	 * 子路徑：/hello/exam/75 => 結果 75 pass
	 * 子路徑：/hello/exam/45 => 結果 45 fail
	 */
	@RequestMapping(value = "/exam/{score}")
	@ResponseBody
	public String examScore(@PathVariable("score") Integer score) {
		return String.format("%d %s", score, (score>=60)?"pass":"fail");
	}
	
	/*
	 * 5. @RequestParam + @PathVariable (Lab 練習)
	 * 子路徑：/calc/add?x=30&y=20  -> Result：50
	 * 子路徑：/calc/sub?x=30&y=20  -> Result：10
	 * 子路徑：/calc/sub?y=20       -> Result：-20
	 * 子路徑：/calc/add            -> Result：0
	 */
	// 請設計方法 api
	@RequestMapping(value = "/calc/{exp}")
	@ResponseBody
	public String calcExp(@PathVariable("exp") String exp,
						  @RequestParam(value = "x", required=false, defaultValue = "0") Integer x,
						  @RequestParam(value = "y", required=false, defaultValue = "0") Integer y) {
		int result = 0;
		switch (exp) {
			case "add":
				result = x + y;
				break;
			case "sub":
				result = x - y;
				break;	
			default:
				return "exp value error";
		}
		return String.format("Result: %d", result);
	}
	
	/*
	 * 6. @PathVariable (萬用字元： * 任意多字、? 任意一字)
	 * 子路徑：/any/aabbcc/java8
	 * 子路徑：/any/abcdefghijk/java7
	 * 子路徑：/any/a/java6
	 */
	@RequestMapping(value = "/any/*/java?")
	@ResponseBody
	public String any() {
		return "Any";
	}
	
	/*
	 * 7. 多筆參數資料
	 * 子路徑：/age?age=18&age=19&age=20
	 * 並計算總和與平均
	 */
	@RequestMapping("/age")
	@ResponseBody
	public String age(@RequestParam("age") List<Integer> ageList) {
		// int 統計物件
		IntSummaryStatistics stat = ageList.stream()
										   .mapToInt(Integer::intValue)
										   .summaryStatistics();
		long sum = stat.getSum(); // 總和
		double avg = stat.getAverage(); // 平均
		return String.format("%s sum: %d avg: %.1f", ageList, sum, avg);
	}
	
	/* 
	 * 8. 得到多筆 score 資料 (Lab 練習)
     * 子路徑：/max?score=80&score=100&score=50
     * 結果得到：max score = 100
	 * 子路徑：/min?score=80&score=100&score=50
     * 結果得到：min score = 80
	 * 建議使用：IntSummaryStatistics
	 * */
	@RequestMapping("{maxOrMin}")
	@ResponseBody
	public String maxOrMin(@PathVariable("maxOrMin") String maxOrMin,
						   @RequestParam("score") List<Integer> scores) {
		String str = "%s score = %d";
		IntSummaryStatistics stat = scores.stream()
									.mapToInt(Integer::intValue)
									.summaryStatistics();
		switch (maxOrMin) {
			case "max":
				str = String.format(str, maxOrMin, stat.getMax());
				break;
			case "min":
				str = String.format(str, maxOrMin, stat.getMin());
				break;	
			default:
				str = "None";
				break;
		}
		return str;
	}
	
	/*
	 * Map 參數
	 * 子路徑：/person?name=John&score=100&age=18&pass=true
	 * 子路徑：/person?name=Mary&score=90&age=20&level=2
	 * 常與於 form 表單傳來的參數
	 * */
	@RequestMapping("/person")
	@ResponseBody
	public String getPerson(@RequestParam Map<String, String> person) {
		return person.toString();
	}
	
	/*
	 * pojo(entity) 參數自動匹配
	 * 子路徑：/user?name=John&age=18
	 * */
	@RequestMapping("/user")
	@ResponseBody
	public String getUser(User user) { // 參數會自動匹配
		return user.toString();
	}
	
	
	
}
