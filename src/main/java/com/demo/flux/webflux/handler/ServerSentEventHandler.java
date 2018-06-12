package com.demo.flux.webflux.handler;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

//@Component
@RestController
public class ServerSentEventHandler {

	@GetMapping(value = "/numbers" )
	public Flux<ServerSentEvent<Integer>> numbers(){
		return Flux.interval(Duration.ofSeconds(1))
				.map(seq->Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
				.map(data->ServerSentEvent.<Integer>builder()
						.event("random")
						.id(Long.toString(data.getT1()))
						.data(data.getT2()).build()
				);
	}
}
