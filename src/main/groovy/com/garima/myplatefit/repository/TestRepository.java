package com.garima.myplatefit.repository;

import com.garima.myplatefit.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
