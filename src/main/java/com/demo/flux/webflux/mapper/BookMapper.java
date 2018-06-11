package com.demo.flux.webflux.mapper;

import com.demo.flux.webflux.entity.BookEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface BookMapper {

	@ResultType(java.lang.Integer.class)
	@Insert({"INSERT INTO flux_web.book (name, pid, price, author) VALUES",
			"(#{bookEntity.name}, #{bookEntity.pid}, #{bookEntity.price}, #{bookEntity.author})"})
	Integer insertBook(@Param("bookEntity") BookEntity bookEntity);

	@ResultType(java.lang.Integer.class)
	@Delete({"DELETE FROM flux_web.book WHERE pid=#{pid}"})
	Integer deleteBookByPid(@Param("pid") String pid);

	@ResultType(java.lang.Integer.class)
	@Update({"UPDATE flux_web.book SET name=#{bookEntity.name}, price=#{bookEntity.price}, author=#{bookEntity.author}",
			"WHERE pid=#{bookEntity.pid}"})
	Integer updateBookByPid(@Param("bookEntity") BookEntity bookEntity);

	@ResultType(com.demo.flux.webflux.entity.BookEntity.class)
	@Select({"SELECT name, pid, price, author",
			"FROM flux_web.book",
			"WHERE pid=#{pid} LIMIT 1"})
	BookEntity getBookByPid(@Param("pid") String pid);

	@ResultType(com.demo.flux.webflux.entity.BookEntity.class)
	@Select({"SELECT name, pid, price, author FROM flux_web.book"})
	List<BookEntity> listAllBooks();
}
