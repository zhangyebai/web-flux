package com.demo.flux.webflux.service;

import com.demo.flux.webflux.bean.PageRespEntry;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.entity.BookEntity;
import com.demo.flux.webflux.mapper.BookMapper;
import com.demo.flux.webflux.wrapper.CtrlRespWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BookService {
	private BookMapper bookMapper;
	@Autowired
	public void setBookMapper(BookMapper bookMapper){
		this.bookMapper = bookMapper;
	}

	public Mono<PageRespEntry<BookEntity>> listAllBooks(){
		PageHelper.startPage(1, 20);
		return Mono.justOrEmpty(CtrlRespWrapper.pageRespGenerator(bookMapper.listAllBooks()));
	}

	public Mono<RespEntry<BookEntity>> getBookByPid(String pid){
		return Mono.justOrEmpty(CtrlRespWrapper.respGenerator(bookMapper.getBookByPid(pid)));
	}
}
