package com.hongzhou.kafkastream.channel;

import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PageCountSink {

	@StreamListener
	public void process(@Input(AnalyticsBinding.PAGE_COUNT_IN) KTable<String, Long> counts) {
		counts.toStream().foreach((key, value) -> log.info(key + "=" + value));
	}
}
