package com.demo.flux.webflux.router;


import com.demo.flux.webflux.handler.BookHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {

	@Bean
	public RouterFunction<ServerResponse> bookRouterFunction(@Autowired BookHandler bookHandler){
		return RouterFunctions.route(GET("/books/all").and(accept(MediaType.APPLICATION_JSON_UTF8)), bookHandler::listAllBooks)
				.andRoute(GET("/book/{pid}").and(accept(MediaType.APPLICATION_JSON_UTF8)), bookHandler::getBookByPid)
				.andRoute(POST("/book").and(accept(MediaType.APPLICATION_JSON_UTF8)), bookHandler::createBook)
				.andRoute(PUT("/book").and(accept(MediaType.APPLICATION_JSON_UTF8)), bookHandler::updateBook)
				.andRoute(DELETE("/book").and(accept(MediaType.APPLICATION_JSON_UTF8)), bookHandler::deleteBookByPid);
	}
}
