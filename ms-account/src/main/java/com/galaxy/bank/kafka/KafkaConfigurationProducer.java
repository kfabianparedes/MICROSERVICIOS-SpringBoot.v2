package com.galaxy.bank.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.galaxy.bank.account.Account;

@Configuration
public class KafkaConfigurationProducer {
	
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	public ProducerFactory<String, Account> producerFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		config.put(ProducerConfig.ACKS_CONFIG, "all");
		
		return new DefaultKafkaProducerFactory<>(config);
		
	}
	
	@Bean
	public KafkaTemplate<String, Account> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}
