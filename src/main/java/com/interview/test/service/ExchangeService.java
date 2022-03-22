package com.interview.test.service;

import java.util.Date;

import com.interview.test.domain.Quote;

public interface ExchangeService {
	Quote getQuote(Date date);
	Double exchangeCurrency(Date date, String sourceCurrency, String targetCurrency, Double amount);
	Double getHighestReference(Date startDate, Date endDate, String currency);
	Double getAverage(Date startDate, Date endDate, String currency);
}
