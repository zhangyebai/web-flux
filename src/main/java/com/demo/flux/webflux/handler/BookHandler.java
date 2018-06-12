package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.entity.BookEntity;
import com.demo.flux.webflux.exception.BookStorageException;
import com.demo.flux.webflux.mapper.BookMapper;
import com.demo.flux.webflux.util.ReactorUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component(value = "bookHandler")
public class BookHandler {

	private BookMapper bookMapper;
	@Autowired
	public void setBookMapper(BookMapper bookMapper){
		this.bookMapper = bookMapper;
	}

	@SuppressWarnings(value = "unused")
	public Mono<ServerResponse> listAllBooks(ServerRequest request){
		final String pageString = request.queryParam("page").orElse("1");
		final String sizeString = request.queryParam("size").orElse("20");
		try {
			int page = Integer.valueOf(pageString);
			int size = Integer.valueOf(sizeString);
			PageHelper.startPage(page, size);
		}catch (NumberFormatException ex){
			return ReactorUtil.error(String.format("request parameter is illegal, page=%s, size=%s", pageString, sizeString));
		}
		return ReactorUtil.okay(bookMapper.listAllBooks());
	}

	public Mono<ServerResponse> getBookByPid(ServerRequest request){
		String pid = request.pathVariable("pid");
		return ReactorUtil.ok(bookMapper.getBookByPid(pid));
	}

	@Transactional(rollbackFor = BookStorageException.class)
	public Mono<ServerResponse> createBook(ServerRequest request){
		return request.bodyToMono(BookEntity.class)
				.flatMap(bookEntity -> ReactorUtil.ok(bookMapper.insertBook(bookEntity)))
				.switchIfEmpty(ReactorUtil.err("Error Occurs In Post Body"));
	}

	public Mono<ServerResponse> updateBook(ServerRequest request){
		return request.bodyToMono(BookEntity.class)
				.flatMap(bookEntity -> ReactorUtil.ok(bookMapper.updateBookByPid(bookEntity)))
				.switchIfEmpty(ReactorUtil.err("No Put Body Found."));
	}

	public Mono<ServerResponse> deleteBookByPid(ServerRequest request){
		final String pid = request.pathVariable("pid");
		return ReactorUtil.ok(bookMapper.deleteBookByPid(pid));
	}
}
