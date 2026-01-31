package com.tgroup.fastscore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@EmbeddedKafka(partitions = 1)
@Testcontainers
class FastScoreApplicationTests {
	@Container
	@ServiceConnection // This tells Spring to grab the URL/Username/Password from the container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

	@Test
	void contextLoads() {
		// Your test will now wait for Kafka to start in Docker
	}
}
