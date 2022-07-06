package com.reactivetest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactivetest.dao.CustomerDao;
import com.reactivetest.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService
{
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> getAllCustomers()
	{
		long start = System.currentTimeMillis();
		List<Customer> customers = customerDao.getCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Time : "+(end - start));
		return customers;
	}
	
	public Flux<Customer> getAllCustomersStream()
	{
		long start = System.currentTimeMillis();
		Flux<Customer> customers = customerDao.getCustomersStream();
		long end = System.currentTimeMillis();
		System.out.println("Time : "+(end - start));
		return customers;
	}
}