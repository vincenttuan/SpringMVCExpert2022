package spring.mvc.session14.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.mvc.session14.entity.Stock;

@Component
public class StockValidator implements Validator {
	
	// 判斷當前的類是否是要驗證的類別
	@Override
	public boolean supports(Class<?> clazz) {
		// 因為 StockValidator 只會判定/驗證 Stock 類
		// 所以要先判斷傳進來的 clazz 是否是 Stock 類別
		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stock stock = (Stock)target;
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
	}
	
}
