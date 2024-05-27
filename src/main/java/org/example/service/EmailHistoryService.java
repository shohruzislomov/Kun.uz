package org.example.service;

import org.example.entity.EmailHistoryEntity;
import org.example.exception.AppBadException;
import org.example.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    public void create(String toEmail,String text){
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setEmail(toEmail);
        entity.setMessage(text);
        emailHistoryRepository.save(entity);
    }
    public void checkEmailLimit(String email) { // 1 -musut 3 attempt
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusMinutes(1);

        long count = emailHistoryRepository.countByEmailAndCreateTimeBetween(email, from, to);
        if (count >= 3) {
            throw new AppBadException("Sms limit reached. Please try after some time");
        }
    }
}
