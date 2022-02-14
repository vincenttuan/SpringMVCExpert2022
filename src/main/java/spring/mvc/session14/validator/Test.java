package spring.mvc.session14.validator;

import yahoofinance.YahooFinance;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println("OK");
		yahoofinance.Stock yStock = YahooFinance.get("2330.TW");
		System.out.println(yStock.getQuote().getPrice());
		System.out.println(yStock.getQuote().getPrice().doubleValue());
		System.out.println(yStock.getQuote().getPreviousClose());
		System.out.println(yStock.getQuote().getPreviousClose().doubleValue());
	}

}
