package com.galaxy.bank.account;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.bank.kafka.KafkaProducer;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Value("${kafka.topic-name}")
	private String topicName;
	
	@Autowired
	private KafkaProducer producer;

	@GetMapping("/findAll")
	public List<Account> findAll(){
		return service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@RequestBody Account account){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Transaccion", "Guardar");
		result.put("Mensaje", "Se guardo con exito");
		Account oAccount= service.save(account);
		
		//enviar a kafka
		producer.sendMessage(topicName, oAccount);
	
		return ResponseEntity.created(URI.create("/...")).contentType(MediaType.APPLICATION_JSON).body(result);
	}
}
