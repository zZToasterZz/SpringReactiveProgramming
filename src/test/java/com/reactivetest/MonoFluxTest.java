package com.reactivetest;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest
{
	@Test
	public void testMono()
	{
		Mono<?> monoString = Mono.just("shantanusrivastava")
				.then(Mono.error(new Throwable("Test Exception")))
				.log();
		monoString.subscribe(System.out::println, e -> System.out.println("PRINTING ERROR : "+e));
	}
	
	@Test
	public void testFlux()
	{
		Flux<?> fluxString = Flux.just("shantanu","srivastava","java","spring")
				.concatWithValues("AWS","shell")
				.concatWith(Flux.error(new Throwable("Test Exception")))
				.log();
		fluxString.subscribe(System.out::println, e -> System.out.println("PRINTING ERROR : "+e));
	}
}