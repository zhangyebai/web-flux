package com.demo.flux.webflux.exception;

public class BookStorageException extends RuntimeException {
	public BookStorageException(){
		super("error occur when storage book");
	}

	public BookStorageException(String message){
		super(message);
	}
}
