package spring.mvc.session14.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.mvc.session14.entity.Stock;
import yahoofinance.YahooFinance;

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
		// 基礎驗證
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		// 進階驗證
		yahoofinance.Stock yStock = null;
		try {
			yStock = YahooFinance.get(stock.getSymbol());
			double previousCose = yStock.getQuote().getPreviousClose().doubleValue(); // 昨日收盤價
			// 買進價格必須是昨日收盤價的±10%之間
			if(stock.getPrice() < previousCose * 0.9 || stock.getPrice() > previousCose * 1.1) {
				errors.rejectValue("price", "stock.price.range");
			}
			// 買進股數必須大於或等於1000
			if(stock.getAmount() < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			// 買進股數必須是1000的倍數(1000股 = 1張)
			if(stock.getAmount() % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(yStock == null) {
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}
	
}
