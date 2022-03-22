package com.interview.test.repository;

import java.util.List;

import com.interview.test.domain.Quote;

public interface ExchangeDAO {
	public List<Quote> getAll();
}
