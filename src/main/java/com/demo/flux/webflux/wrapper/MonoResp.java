package com.demo.flux.webflux.wrapper;

import com.demo.flux.webflux.bean.Page;
import com.demo.flux.webflux.bean.PageRespEntry;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.property.Property;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoResp {
	private MonoResp(){}

	public static <T> Mono<RespEntry<T>> resp(T data){
		return resp(Property.Resp.SUCCESS_CODE, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> resp(int code, T data){
		return resp(code, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> resp(String message, T data){
		return resp(Property.Resp.SUCCESS_CODE, message, data);
	}

	public static <T> Mono<RespEntry<T>> err(String message){
		return resp(Property.Resp.ERROR_CODE, message, null);
	}

	public static <T> Mono<RespEntry<T>> err(int code, T data){
		return resp(code, Property.Resp.ERROR_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> err(String message, T data){
		return resp(Property.Resp.ERROR_CODE, message, data);
	}

	public static <T> Mono<RespEntry<T>> err(T data){
		return resp(Property.Resp.ERROR_CODE, Property.Resp.ERROR_MESSAGE, data);
	}

	public static <T> Mono<RespEntry<T>> resp(int code, String message, T data){
		return Mono.just(new RespEntry<>(code, message, data));
	}

	public static <T> Mono<PageRespEntry<T>> pageResp(List<T> data){
		return pageResp(Property.Resp.SUCCESS_CODE, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<PageRespEntry<T>> pageResp(String message, List<T> data){
		return pageResp(Property.Resp.SUCCESS_CODE, message, data);
	}

	public static <T> Mono<PageRespEntry<T>> pageResp(int code, List<T> data){
		return pageResp(code, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> Mono<PageRespEntry<T>> error(String message){
		return pageResp(Property.Resp.ERROR_CODE, message, null);
	}

	public static <T> Mono<PageRespEntry<T>> pageResp(int code, String message, List<T> data){
		return Mono.just(new PageRespEntry<T>().setCode(code).setMessage(message).setPage(new Page<>(data)).setData(data));
	}

}
