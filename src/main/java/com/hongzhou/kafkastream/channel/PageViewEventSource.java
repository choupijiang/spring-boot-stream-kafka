package com.hongzhou.kafkastream.channel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.hongzhou.kafkastream.domain.PageViewEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PageViewEventSource implements ApplicationRunner {

	private final MessageChannel pageViewOut;

	public PageViewEventSource(AnalyticsBinding binding) {
		super();
		this.pageViewOut = binding.pageViewOut();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> names = Arrays.asList("hong", "haritha", "thaugh", "hasha", "nikhil", "akhila");
		List<String> pages = Arrays.asList("blog", "sitemap", "initializr", "news", "colopahon", "about");

		Runnable runnable = () -> {		
			String rPage = pages.get(new Random().nextInt(pages.size()));
			String rName = names.get(new Random().nextInt(names.size()));
			
			PageViewEvent pageViewEvent = new PageViewEvent(rName, rPage, Math.random() > .5 ? 10 : 1000);

			Message<PageViewEvent> message = MessageBuilder.withPayload(pageViewEvent)
					.setHeader(KafkaHeaders.MESSAGE_KEY, pageViewEvent.getUserId().getBytes()).build();
			
			try {
				this.pageViewOut.send(message);
				log.info("Sent"  + message.toString());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		};

		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
	}

}
