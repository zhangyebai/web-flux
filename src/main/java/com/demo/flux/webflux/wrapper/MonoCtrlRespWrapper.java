package com.demo.flux.webflux.wrapper;

import com.demo.flux.webflux.bean.Page;
import com.demo.flux.webflux.bean.PageRespEntry;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.property.Property;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoCtrlRespWrapper {
	private MonoCtrlRespWrapper(){}

	public static <T> Mono<RespEntry<T>> respGenerator(T data){
		return respGenerator(Property.Resp.SUCCESS_CODE, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> respGenerator(int code, T data){
		return respGenerator(code, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> respGenerator(String message, T data){
		return respGenerator(Property.Resp.SUCCESS_CODE, message, data);
	}

	public static <T> Mono<RespEntry<T>> errorRespGenerator(int code, T data){
		return respGenerator(code, Property.Resp.ERROR_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> errorRespGenerator(String message, T data){
		return respGenerator(Property.Resp.ERROR_CODE, message, data);
	}

	public static <T> Mono<RespEntry<T>> errorRespGenerator(T data){
		return respGenerator(Property.Resp.ERROR_CODE, Property.Resp.ERROR_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> respGenerator(int code, String message, T data){
		return Mono.just(new RespEntry<>(code, message, data));
	}

	public static <T> Mono<PageRespEntry<T>> pageRespGenerator(List<T> data){
		return pageRespGenerator(Property.Resp.SUCCESS_CODE, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<PageRespEntry<T>> pageRespGenerator(String message, List<T> data){
		return pageRespGenerator(Property.Resp.SUCCESS_CODE, message, data);
	}

	public static <T> Mono<PageRespEntry<T>> pageRespGenerator(int code, List<T> data){
		return pageRespGenerator(code, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<PageRespEntry<T>> pageRespGenerator(int code, String message, List<T> data){
		return Mono.just(new PageRespEntry<T>().setCode(code).setMessage(message).setPage(new Page<>(data)).setData(data));
	}
}
