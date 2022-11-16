package com.galaxy.bank.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.galaxy.bank.account.Account;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaProducer {

	@Autowired
	KafkaTemplate<String, Account> kafkaTemplate;
	
	public void sendMessage(String topic, Account account) {
		log.info("Mensaje enviado: {}", account);
		this.kafkaTemplate.send(topic, account);
	}
}
