package com.demo.flux.webflux.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
@Slf4j
public class StreamReceiver {

	@StreamListener(Sink.INPUT)
	public void receive(Message<String> message){
		String bookEntity = message.getPayload();
		log.error(bookEntity);
		/// Acknowledgment acknowledgment =
		message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class).acknowledge();
	}

}
