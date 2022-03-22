package com.interview.test.resource.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.test.domain.Quote;
import com.interview.test.resource.ExchangeResource;
import com.interview.test.service.ExchangeService;

@RestController
@RequestMapping(path = "/exchange")
public class ExchangeResourceImpl implements ExchangeResource{

	@Autowired
	private ExchangeService exchangeService;
		
	@Override
	@GetMapping(path = "/allquotes")
	public Quote getQuote(String date) {
		
		return exchangeService.getQuote(convertToDate(date));
	}

	@Override
	@GetMapping(path = "/convert")
	public Double exchangeCurrency(String date, String sourceCurrency, String targetCurrency, Double amount) {
		return exchangeService.exchangeCurrency(convertToDate(date), sourceCurrency, targetCurrency, amount);
	}

	@Override
	@GetMapping(path = "/highest")
	public Double getHighestReference(String startDate, String endDate, String currency) {
		// TODO Auto-generated method stub
		return exchangeService.getHighestReference(convertToDate(startDate),convertToDate(endDate),currency);
	}

	@Override
	@GetMapping(path = "/average")
	public Double getAverage(String startDate, String endDate, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	private Date convertToDate(String dateStr) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return d;
	}
}
