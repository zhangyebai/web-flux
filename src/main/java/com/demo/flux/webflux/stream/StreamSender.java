package com.demo.flux.webflux.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class StreamSender {

	@Autowired
	private Source source;

	public void send(String message){
		source.output().send(MessageBuilder.withPayload(message).build());
	}
}
