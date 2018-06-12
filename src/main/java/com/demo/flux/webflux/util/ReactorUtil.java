package com.demo.flux.webflux.util;

import com.demo.flux.webflux.wrapper.MonoResp;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public class ReactorUtil {
	private ReactorUtil(){}

	private static <T> Mono<ServerResponse> done(T data){
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(data));
	}

	public static <T> Mono<ServerResponse> ok(T data){
		return MonoResp.resp(data).flatMap(ReactorUtil::done);
	}

	public static <T> Mono<ServerResponse> okay(List<T> data){
		return MonoResp.pageResp(data).flatMap(ReactorUtil::done);
	}

	public static <T> Mono<ServerResponse> err(String message){
		return MonoResp.err(message).flatMap(ReactorUtil::done);
	}

	public static <T> Mono<ServerResponse> error(String message){
		return MonoResp.error(message).flatMap(ReactorUtil::done);
	}
}
