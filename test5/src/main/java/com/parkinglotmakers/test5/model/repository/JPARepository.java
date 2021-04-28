package com.parkinglotmakers.test5.model.repository;

import com.parkinglotmakers.test5.model.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepository extends JpaRepository<TestEntity, Long> {
}