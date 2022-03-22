package com.interview.test.repository.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.interview.test.domain.Quote;
import com.interview.test.repository.ExchangeDAO;

@Repository
public class ExchangeDAOImpl implements ExchangeDAO{

	private final Logger logger = LoggerFactory.getLogger(ExchangeDAOImpl.class);
	
	public List<Quote> getAll(){
		List<Quote> result = new ArrayList<Quote>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("C:\\Users\\yurim\\Desktop\\JavaProject\\eurofxref-hist.csv")));
	    	String line;
	        String[] currencyTitle = br.readLine().split(",");
	        while ((line = br.readLine()) != null) {
	            String[] items = line.split(",");
	            try {
	                Quote quote = new Quote();
	                quote.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(items[0]));
	                Map<String, String> list = new HashMap<String, String>();
	                for(int i = 1; i<items.length; i++) {
	                	list.put(currencyTitle[i], items[i]);
	                }
	                quote.setCurrencyValue(list);
	                result.add(quote);
	            } catch (ArrayIndexOutOfBoundsException|NumberFormatException|NullPointerException | ParseException e) {  
	                System.out.println("Invalid line: "+ line);
	            }
	          
	        }
	        br.close();
	        return result;
	    } catch (IOException e1) {
			e1.printStackTrace();
	    }
		return result;
	}

}
