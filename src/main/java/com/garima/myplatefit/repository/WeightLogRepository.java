package com.garima.myplatefit.repository;

import com.garima.myplatefit.model.User;
import com.garima.myplatefit.model.WeightLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeightLogRepository extends JpaRepository<WeightLog, Long> {

    List<WeightLog> findByUserOrderByDateDesc(User user);

    List<WeightLog> findByUserOrderByDateAsc(User user);
    List<WeightLog> findByUserAndDateAfter(User user, LocalDate date);
}
