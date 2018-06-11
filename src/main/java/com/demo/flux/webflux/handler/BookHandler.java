package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.bean.PageRespEntry;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.entity.BookEntity;
import com.demo.flux.webflux.mapper.BookMapper;
import com.demo.flux.webflux.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component(value = "bookHandler")
public class BookHandler {

	private BookService bookService;
	@Autowired
	public void setBookService(BookService bookService){
		this.bookService = bookService;
	}

	@SuppressWarnings(value = "unused")
	public Mono<ServerResponse> listAllBooks(ServerRequest request){
		Mono<PageRespEntry<BookEntity>> pageRespEntryMono = bookService.listAllBooks();
		return  bookService.listAllBooks()
				.flatMap(pageBookEntity->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(fromObject(pageBookEntity)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> getBookByPid(ServerRequest request){
		String pid = request.pathVariable("pid");
		return bookService.getBookByPid(pid)
				.flatMap(bookEntry->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(bookEntry)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
