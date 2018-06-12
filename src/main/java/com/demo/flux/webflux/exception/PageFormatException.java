package com.demo.flux.webflux.exception;

public class PageFormatException extends RuntimeException {
	public PageFormatException(){
		super("un-support number.");
	}

	public PageFormatException(String message){
		super(message);
	}
}
