package com.reactivetest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactivetest.dao.CustomerDao;
import com.reactivetest.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler
{
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request)
	{
		Flux<Customer> customerList = customerDao.getCustomersList();
		return ServerResponse.ok().body(customerList, Customer.class);
	}
	
	public Mono<ServerResponse> findCustomer(ServerRequest request)
	{
		int customerId = Integer.valueOf(request.pathVariable("customerid"));
		//Mono<Customer> customerList = customerDao.getCustomersList().filter(i -> i.getId()==customerId).take(1).single();
		Mono<Customer> customerList = customerDao.getCustomersList().filter(i -> i.getId()==customerId).next();
		return ServerResponse.ok().body(customerList, Customer.class);
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request)
	{
		Mono<Customer> customerMono = request.bodyToMono(Customer.class);
		Mono<String> saveResponse = customerMono.map(i -> i.getId()+" : "+i.getName());
		return ServerResponse.ok().body(saveResponse, String.class);
	}
}