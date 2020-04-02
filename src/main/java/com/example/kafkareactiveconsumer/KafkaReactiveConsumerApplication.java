package com.example.kafkareactiveconsumer;

import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@Slf4j
@SpringBootApplication
public class KafkaReactiveConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaReactiveConsumerApplication.class, args);
	}

	@Bean
	public Consumer<Flux<Message<Loan>>> loanProcess() {
		return loanMessages ->
				loanMessages
						.flatMap(loanMessage -> Mono.fromCallable(() -> {
							if (loanMessage.getPayload().getStatus() == null) {
								log.error("Empty status");
								throw new RuntimeException("Loan status is empty");
							}
							log.info("Correct message");
							return "Correct message received";
						}))
						.doOnError(throwable -> log.error("Exception occurred: {}", throwable.getClass()))
						.subscribe(status -> log.info("Message processed correctly: {}", status));
	}

}
