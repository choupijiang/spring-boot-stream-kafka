package com.hongzhou.kafkastream.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageViewEvent {
	private String userId;
	private String page;
	private long duration;

}
