package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.util.ReactorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class ServerSentEventHandler {

	@SuppressWarnings(value = "unused")
	public Mono<ServerResponse> numbers(ServerRequest request){
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(
				Flux.interval(Duration.ofSeconds(1))
						.map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
						.map(data -> ServerSentEvent.<Integer>builder()
								.event("random")
								.id(Long.toString(data.getT1()))
								.data(data.getT2()).build()
						)
						, ServerSentEvent.class
		).doOnCancel(()->log.error("stream http is closed"))
				.doOnError(err->log.error(err.getMessage()))
				.doOnTerminate(()-> log.error("terminal."))
				.onErrorResume(err ->ReactorUtil.err("web socket closed" + err.getMessage()))
				.onTerminateDetach();
	}
}
