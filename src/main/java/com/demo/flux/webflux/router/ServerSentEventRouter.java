package com.demo.flux.webflux.router;

import com.demo.flux.webflux.handler.ServerSentEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ServerSentEventRouter {

	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunction(@Autowired ServerSentEventHandler serverSentEventHandler){
		return RouterFunctions.route(RequestPredicates.GET("/numbers")
				.and(RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM)),
				serverSentEventHandler::numbers);
		//return null;
	}
}
