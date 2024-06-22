package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.WelcomeUserDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    ObjectMapper objectMapper;

    public EmailService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "sendEmail", groupId = "emailServer")
    public void sendEmailHandler(String message) throws JsonProcessingException {
        WelcomeUserDto welcomeUserDto = objectMapper.readValue(message, WelcomeUserDto.class);
        System.out.println(welcomeUserDto);
    }

}
