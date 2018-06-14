package com.demo.flux.webflux.ctrl;

import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.entity.BookEntity;
import com.demo.flux.webflux.stream.StreamSender;
import com.demo.flux.webflux.wrapper.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/stream")
public class StreamCtrl {
	@Autowired
	private StreamSender streamSender;

	@PostMapping(value = "/book")
	public RespEntry<Boolean> receive(@RequestBody BookEntity bookEntity){
		streamSender.send(bookEntity.toString());
		return Resp.resp(Boolean.TRUE);
	}
}
