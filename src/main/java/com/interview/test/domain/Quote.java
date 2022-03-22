package com.interview.test.domain;

import java.util.Date;
import java.util.Map;


public class Quote {
	
	private Date date;	
	private Map<String, String> currencyValue;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Map<String, String> getCurrencyValue() {
		return currencyValue;
	}
	public void setCurrencyValue(Map<String, String> currencyValue) {
		this.currencyValue = currencyValue;
	}
		
}
