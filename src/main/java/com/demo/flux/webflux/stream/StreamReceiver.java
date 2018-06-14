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

	/**
	 * 注意,经测试,此函数是被Spring Cloud Stream阻塞调用的,
	 * 所以这里收到数据后要提交到线程池去处理, 不要阻塞scs
	 * 
	 * */
	@StreamListener(Sink.INPUT)
	public void receive(Message<String> message){
		String bookEntity = message.getPayload();
		log.error(bookEntity);
		Acknowledgment acknowledgment =message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
		if(null != acknowledgment){
			acknowledgment.acknowledge();
		}
	}

}
