package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.WebFluxApplication;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.exception.PageFormatException;
import com.demo.flux.webflux.wrapper.MonoCtrlRespWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * spring boot2.0 webflux 的global exception handler 无法以目前的形式处理web flux的异常
 * */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
	@ExceptionHandler(value = PageFormatException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handler(Exception ex){
		log.error(ex.getMessage());
		return ex.getMessage();
	}
}
