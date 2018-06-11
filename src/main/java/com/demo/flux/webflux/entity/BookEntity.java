package com.demo.flux.webflux.entity;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class BookEntity {
	private String name;
	private String pid;
	private int price;
	private String author;
}
