package com.reactivetest.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.reactivetest.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao
{
	public List<Customer> getCustomers()
	{
		return IntStream.rangeClosed(1, 50)
				.peek(CustomerDao::sleep)
				.peek(i -> System.out.println("processing count : "+i))
				.mapToObj(i -> new Customer(i, "customer_"+i))
				.collect(Collectors.toList());
	}
	
	public Flux<Customer> getCustomersStream()
	{
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count in stream : "+i))
				.map(i -> new Customer(i, "customer_"+i));
	}
	
	public Flux<Customer> getCustomersList()
	{
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("processing count for router : "+i))
				.map(i -> new Customer(i, "customer_"+i));
	}
	
	private static void sleep(int i)
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}