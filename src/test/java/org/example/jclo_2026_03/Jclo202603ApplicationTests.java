package org.example.jclo_2026_03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import java.awt.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Jclo202603ApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	private final GenericContainer<?> authApp1 = new GenericContainer<>("appauth1")  // имя контейнера из docker-compose.yml
			.withExposedPorts(8080);
	private final GenericContainer<?> authApp2 = new GenericContainer<>("appauth1")  // имя контейнера из docker-compose.yml
			.withExposedPorts(8080);

	@BeforeEach
	void setUp(){
		authApp1.start();
		authApp2.start();
	}


	@Test
	void contextLoads() {
		Integer appPort1 = authApp1.getMappedPort(8080); // узнать, какой порт привязан к 8080 этого контейнера
		Integer appPort2 = authApp2.getMappedPort(8080);

		// authApp1.getLogs();
		// здесь можем делать все то же самое, что вручную
		// - выполнить команду внури контейнера
		// - проверить логи
		// - настроить политики ожидания состояния контейнера
		//    (например, зацепиться за то, что в логах появится нужная строчка
		//    или по определенному порту определенным ответом отвечает - тогда понимаем, что контейнер готов и можно запускать тесты)

		// Теперь запустим в контейнеры запросы
		ResponseEntity<String> entity1 = restTemplate.getForEntity("http://localhost:" + appPort1, String.class);
		ResponseEntity<String> entity2 = restTemplate.getForEntity("http://localhost:" + appPort2, String.class);

		System.out.println(entity1.getBody());
		System.out.println(entity2.getBody());
	}

}
