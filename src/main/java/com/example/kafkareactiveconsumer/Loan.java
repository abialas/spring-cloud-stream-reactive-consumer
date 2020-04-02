package com.example.kafkareactiveconsumer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Value;

/**
 * Created by adam.
 */
@Value
public class Loan {
	private String status;
	private String eventId;
	private LocalDateTime eventDate;
	private BigDecimal amount;
}
