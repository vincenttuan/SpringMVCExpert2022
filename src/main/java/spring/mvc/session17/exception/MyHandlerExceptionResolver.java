package spring.mvc.session17.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

// 自訂全局例外異常處理
@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {
		System.out.println("MyHandlerExceptionResolver: 發生全局例外異常");
		String referer = request.getHeader("Referer");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/global_error");
		mv.addObject("referer", referer);
		mv.addObject("ex", ex);
		return mv;
	}
}
