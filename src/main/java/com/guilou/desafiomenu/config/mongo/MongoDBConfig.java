package com.guilou.desafiomenu.config.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Value(value = "${spring.data.mongodb.uri}")
    private String mongoUrl;

    @Bean
    public MongoDatabaseFactory mongoConfigure() {
        System.out.println(this.mongoUrl);
        return new SimpleMongoClientDatabaseFactory(this.mongoUrl);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoConfigure());
    }
}
