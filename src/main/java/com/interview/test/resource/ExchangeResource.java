package com.interview.test.resource;

import com.interview.test.domain.Quote;

public interface ExchangeResource {
	
	Quote getQuote(String date);
	Double exchangeCurrency(String date, String sourceCurrency, String targetCurrency, Double amount);
	Double getHighestReference(String startDate, String endDate, String currency);
	Double getAverage(String startDate, String endDate, String currency);
}
