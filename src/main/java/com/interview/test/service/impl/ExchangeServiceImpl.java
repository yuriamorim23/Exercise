package com.interview.test.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.interview.test.domain.Quote;
import com.interview.test.repository.impl.ExchangeDAOImpl;
import com.interview.test.service.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService{

	@Autowired
	private ExchangeDAOImpl exchangeDAO;

	private static Predicate<Quote> isDateEqual(Date date) {
        return p -> p.getDate().compareTo(date) == 0;
    }
	

	private static Predicate<Quote> isDateBetweenDates(Date startDate, Date endDate) {
		 return quote -> quote.getDate().compareTo(endDate) <= 0 && quote.getDate().compareTo(startDate) >= 0;
	}
	
	private static Predicate<Quote> isAverage(Date startDate, Date endDate) {
		 return quote -> quote.getDate().compareTo(endDate) <= 0 && quote.getDate().compareTo(startDate) >= 0;
	}
	    
	@Override
	public Quote getQuote(Date date) {
		List<Quote> list = exchangeDAO.getAll();
		Optional<Quote> quota = null;
		try {
			quota = list.stream().filter(isDateEqual(date))
			.findFirst();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quota.get();
	}

	@Override
	public Double exchangeCurrency(Date date, String sourceCurrency, String targetCurrency, Double amount) {
		Quote quote = getQuote(date);
		String sourceC = quote.getCurrencyValue().get(sourceCurrency);
		String targetC = quote.getCurrencyValue().get(targetCurrency);
		Double result;
		if(sourceC.equals("N/A") || targetC.equals("N/A"))
			result = null;
		else
			result = Double.valueOf(sourceC)/Double.valueOf(targetC)*amount;
		return result;
	}
	

	@Override
	public Double getHighestReference(Date startDate, Date endDate, String currency) {
			Double highestCurrency = null;
			List<Quote> list = exchangeDAO.getAll();
			try {
				List<Quote> listBetween = list.stream().filter(isDateBetweenDates(startDate, endDate)).toList();
				for (Quote qt : listBetween) {
					String qt1 = qt.getCurrencyValue().get(currency);
					if (!qt1.equals("N/A")) {
						Double currentValue = Double.valueOf(qt1);
						if (highestCurrency == null || currentValue > highestCurrency) {
							highestCurrency = currentValue;
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return highestCurrency;
	}
	
	
	
	@Override
	public Double getAverage(Date startDate, Date endDate, String currency) {
			Double highestCurrency = null;
			Double averageValue = null;
			List<Quote> list = exchangeDAO.getAll();
			try {
				List<Quote> listBetween = list.stream().filter(isAverage(startDate, endDate)).toList();
				for (Quote qt : listBetween) {
					String qt1 = qt.getCurrencyValue().get(currency);
					if (!qt1.equals("N/A")) {
						Double currentValue = Double.valueOf(qt1);
						if (highestCurrency == null || currentValue > highestCurrency) {
							highestCurrency = currentValue;
						}
						if (averageValue == null || currentValue < highestCurrency) {
							highestCurrency = currentValue / averageValue;
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return highestCurrency;
	}
}