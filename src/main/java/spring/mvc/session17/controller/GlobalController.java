package spring.mvc.session17.controller;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

// 在Spring裡，我們可以使用@ControllerAdvice來聲明一些全局性的東西
// @ExceptionHandler 註解用於全局異常的處理。
// @ModelAttribute 可用於全局使用特定資料時使用
// @InitBinder 初始化繫結器，用於資料繫結、設定資料轉換器等
@ControllerAdvice
public class GlobalController {
	
    @ExceptionHandler({RuntimeException.class, SQLException.class})
    public String fix(Exception ex, Model model, HttpServletRequest request){
        System.out.println("全局例外處理");
        String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
        model.addAttribute("ex", ex);
        return "error/global_error";
    }
   
    @ModelAttribute(name = "globalMapData")
    public Map<String,Object> mydata() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("language", "Java");
        map.put("version", 11);
        map.put("framework", "Spring");
        return map;
    }
    
    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
    
    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }
    
}
