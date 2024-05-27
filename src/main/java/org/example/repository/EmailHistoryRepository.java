package org.example.repository;

import org.example.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer> {
    Long countByEmailAndCreateTimeBetween(String email, LocalDateTime from, LocalDateTime to);

}
