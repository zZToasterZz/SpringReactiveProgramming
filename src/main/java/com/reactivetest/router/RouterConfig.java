package com.reactivetest.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactivetest.handler.CustomerHandler;
import com.reactivetest.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig
{
	@Autowired
	CustomerHandler customerHandler;
	
	@Autowired
	CustomerStreamHandler customerStreamHandler;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction()
	{
		return RouterFunctions.route()
				.GET("/router/customers", i -> { return customerHandler.loadCustomers(i); })
				.GET("/router/customers/stream", customerStreamHandler::getCustomers )
				.GET("/router/customers/{customerid}", customerHandler::findCustomer )
				.POST("/router/customers/save",customerHandler::saveCustomer)
				.build();
	}
}