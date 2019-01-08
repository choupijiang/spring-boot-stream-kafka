package com.hongzhou.kafkastream.channel;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.hongzhou.kafkastream.domain.PageViewEvent;

public interface AnalyticsBinding {

	String PAGE_VIEW_OUT = "pageViewOut";
	String PAGE_VIEW_IN = "pageViewIn";
	String PAGE_COUNT_MV = "pageCountMv";
	String PAGE_COUNT_OUT = "pageCountOut";
	String PAGE_COUNT_IN = "pageCountIn";
	
	// page views
	@Input(PAGE_VIEW_IN)
	KStream<String, PageViewEvent> pageViewIn();
	
	@Output(PAGE_VIEW_OUT)
	MessageChannel pageViewOut();
	
	// page counts
	@Output(PAGE_COUNT_OUT)
	KStream<String, Long> pageCountOut();
	
	@Input(PAGE_COUNT_IN)
	KTable<String, Long> pageCountIn();
	
}
