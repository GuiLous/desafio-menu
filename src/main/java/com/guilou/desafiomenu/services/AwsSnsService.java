package com.guilou.desafiomenu.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.guilou.desafiomenu.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    @Autowired
    AmazonSNS snsClient;

    @Autowired
    @Qualifier("catalogEventsTopic")
    Topic catalogTopic;

    public void publish(MessageDTO message) {
        try {
            this.snsClient.publish(catalogTopic.getTopicArn(), message.message());
        } catch (RuntimeException error) {
            System.out.println(error.getMessage());
            throw new RuntimeException(error);
        }
    }
}
