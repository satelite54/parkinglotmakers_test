package com.parkinglotmakers.test5.service;



import lombok.AllArgsConstructor;
import com.parkinglotmakers.test5.model.TestDTO;
import com.parkinglotmakers.test5.model.repository.JPARepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class TestService {
    private JPARepository jpaRepository;

    @Transactional
    public Long savePost(TestDTO boardDto) {
        return jpaRepository.save(boardDto.toEntity()).getId();
    }
}