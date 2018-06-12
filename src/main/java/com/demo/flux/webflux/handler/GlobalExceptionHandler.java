package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.util.ReactorUtil;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * spring boot2.0 webflux 的global exception handler 无法以目前的形式处理web flux的异常
 * */
public class GlobalExceptionHandler{
	public static Mono<ServerResponse> pathNotFound(ServerRequest request){
		return ReactorUtil.err("path: " + request.path() + " not found.");
	}
}
