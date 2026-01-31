package com.tgroup.fastscore.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic tournamentEventsTopic() {
        return TopicBuilder.name("tournament-match-update")
                .partitions(3) // Good for scaling consumers later
                .replicas(1)   // Since you only have 1 broker in Docker
                .build();
    }
}
