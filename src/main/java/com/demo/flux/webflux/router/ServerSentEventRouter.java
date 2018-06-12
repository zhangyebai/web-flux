package com.demo.flux.webflux.router;

import com.demo.flux.webflux.handler.ServerSentEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

public class ServerSentEventRouter {

	public RouterFunction<ServerResponse> serverResponseRouterFunction(ServerSentEventHandler serverSentEventHandler){
		/*return RouterFunctions.route(RequestPredicates.GET("/numbers")
				.and(RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM)),
				serverSentEventHandler::numbers);*/
		serverSentEventHandler.numbers().flatMap(event->ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(BodyInserters.fromObject(event)));
		return null;
	}
}
