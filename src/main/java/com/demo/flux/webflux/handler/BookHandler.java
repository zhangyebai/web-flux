package com.demo.flux.webflux.handler;

import com.demo.flux.webflux.entity.BookEntity;
import com.demo.flux.webflux.exception.BookStorageException;
import com.demo.flux.webflux.exception.PageFormatException;
import com.demo.flux.webflux.mapper.BookMapper;
import com.demo.flux.webflux.util.FluxUtil;
import com.demo.flux.webflux.wrapper.MonoCtrlRespWrapper;
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
			return FluxUtil.error(String.format("request parameter is illegal, page=%s, size=%s", pageString, sizeString));
		}
		return MonoCtrlRespWrapper.pageRespGenerator(bookMapper.listAllBooks()).flatMap(FluxUtil::done);
	}

	public Mono<ServerResponse> getBookByPid(ServerRequest request){
		String pid = request.pathVariable("pid");
		return MonoCtrlRespWrapper.respGenerator(bookMapper.getBookByPid(pid)).flatMap(FluxUtil::done);
	}

	@Transactional(rollbackFor = BookStorageException.class)
	public Mono<ServerResponse> createBook(ServerRequest request){
		return request.bodyToMono(BookEntity.class)
				.flatMap(bookEntity -> MonoCtrlRespWrapper.respGenerator(bookMapper.insertBook(bookEntity)))
				.switchIfEmpty(MonoCtrlRespWrapper.errorRespGenerator("Error Occurs In Post Body", null))
				.flatMap(FluxUtil::done);
	}

	public Mono<ServerResponse> updateBook(ServerRequest request){
		return request.bodyToMono(BookEntity.class)
				.flatMap(bookEntity -> MonoCtrlRespWrapper.respGenerator(bookMapper.updateBookByPid(bookEntity)))
				.switchIfEmpty(MonoCtrlRespWrapper.errorRespGenerator("No Put Body Found.", null))
				.flatMap(FluxUtil::done);
	}

	public Mono<ServerResponse> deleteBookByPid(ServerRequest request){
		final String pid = request.pathVariable("pid");
		return MonoCtrlRespWrapper.respGenerator(bookMapper.deleteBookByPid(pid)).flatMap(FluxUtil::done);
	}
}
