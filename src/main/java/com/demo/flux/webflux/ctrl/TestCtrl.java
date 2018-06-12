package com.demo.flux.webflux.ctrl;

import com.demo.flux.webflux.exception.PageFormatException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtrl {

	@GetMapping(value = "/test")
	public String test(){
		throw new PageFormatException("testy");
	}
}
