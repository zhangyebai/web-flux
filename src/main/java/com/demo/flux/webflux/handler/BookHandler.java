package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.entity.BookEntity;
import com.demo.flux.webflux.exception.BookStorageException;
import com.demo.flux.webflux.mapper.BookMapper;
import com.demo.flux.webflux.wrapper.CtrlRespWrapper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component(value = "bookHandler")
public class BookHandler {

	private BookMapper bookMapper;
	@Autowired
	public void setBookMapper(BookMapper bookMapper){
		this.bookMapper = bookMapper;
	}

	@SuppressWarnings(value = "unused")
	public Mono<ServerResponse> listAllBooks(ServerRequest request){
		int page = Integer.valueOf(request.queryParam("page").orElse("1"));
		int size = Integer.valueOf(request.queryParam("size").orElse("20"));
		PageHelper.startPage(page, size);
		return  Mono.justOrEmpty(CtrlRespWrapper.pageRespGenerator(bookMapper.listAllBooks()))
				.flatMap(pageBookEntity->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(fromObject(pageBookEntity)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> getBookByPid(ServerRequest request){
		String pid = request.pathVariable("pid");
		return Mono.justOrEmpty(CtrlRespWrapper.respGenerator(bookMapper.getBookByPid(pid)))
				.flatMap(bookEntry->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(bookEntry)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	@Transactional(rollbackFor = BookStorageException.class)
	public Mono<ServerResponse> createBook(ServerRequest request){
		return request.bodyToMono(BookEntity.class).flatMap(bookEntity ->
			Mono.justOrEmpty(CtrlRespWrapper.respGenerator(bookMapper.insertBook(bookEntity)))
					.flatMap(result->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
							.body(fromObject(result)))
		).switchIfEmpty(
				ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(fromObject(CtrlRespWrapper.errorRespGenerator("POST BODY ERROR")))
		);
	}

	public Mono<ServerResponse> updateBook(ServerRequest request){
		return request.bodyToMono(BookEntity.class)
				.flatMap(bookEntity ->
						Mono.justOrEmpty(CtrlRespWrapper.respGenerator(bookMapper.updateBookByPid(bookEntity)))
								.flatMap(result->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
										.body(fromObject(result)))
				).switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> deleteBookByPid(ServerRequest request){
		return Mono.justOrEmpty(request.queryParam("pid"))
				.filter(StringUtils::isNotBlank)
				.flatMap(pid->
						Mono.justOrEmpty(CtrlRespWrapper.respGenerator(bookMapper.deleteBookByPid(pid)))
								.flatMap(result->ServerResponse.ok()
										.contentType(MediaType.APPLICATION_JSON_UTF8)
										.body(fromObject(result))))
				.switchIfEmpty(ServerResponse.notFound().build());

	}
}
