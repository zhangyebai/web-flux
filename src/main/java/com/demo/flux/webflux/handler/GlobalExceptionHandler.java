package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.WebFluxApplication;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.exception.PageFormatException;
import com.demo.flux.webflux.util.FluxUtil;
import com.demo.flux.webflux.wrapper.MonoCtrlRespWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * spring boot2.0 webflux 的global exception handler 无法以目前的形式处理web flux的异常
 * */
public class GlobalExceptionHandler{
	public static Mono<ServerResponse> pathNotFound(ServerRequest request){
		return MonoCtrlRespWrapper.errorRespGenerator("path: " + request.path() + " not found.", null)
				.flatMap(FluxUtil::done);
	}
}
