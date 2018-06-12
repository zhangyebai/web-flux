package com.demo.flux.webflux.util;

import com.demo.flux.webflux.wrapper.CtrlRespWrapper;
import com.demo.flux.webflux.wrapper.MonoCtrlRespWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxUtil {
	private FluxUtil(){}

	public static <T> Mono<ServerResponse> done(T data){
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(data));
	}

	public static <T> Mono<ServerResponse> err(String message){
		return MonoCtrlRespWrapper.errorRespGenerator(message, null).flatMap(FluxUtil::done);
	}

	public static <T> Mono<ServerResponse> error(String message){
		return MonoCtrlRespWrapper.errorPageRespGenerator(message).flatMap(FluxUtil::done);
	}
	/**public static Mono<ServerResponse> errorServerResponse(){
		return ServerResponse.ok().body(BodyInserters.fromObject(CtrlRespWrapper.errorRespGenerator(null)));
	}

	public static Mono<ServerResponse> errorServerResponse(String message){
		return ServerResponse.ok().body(BodyInserters.fromObject(CtrlRespWrapper.errorRespGenerator(message)));
	}

	public static Mono<ServerResponse> errorServerResponse(Object data){
		return ServerResponse.ok().body(BodyInserters.fromObject(CtrlRespWrapper.errorRespGenerator(data)));
	}

	public static Mono<ServerResponse> serverResponseMono(Object data){
		return ServerResponse.ok().body(BodyInserters.fromObject(CtrlRespWrapper.respGenerator(data)));
	}

	public static <T> Mono<ServerResponse> pageServerResponseMono(List<T> data){
		return ServerResponse.ok().body(BodyInserters.fromObject(CtrlRespWrapper.pageRespGenerator(data)));
	}*/
}
