package com.garima.myplatefit.repository;

import com.garima.myplatefit.model.Meal;
import com.garima.myplatefit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndDate(User user, LocalDate date);
    List<Meal> findByUserAndDateAfter(User user, LocalDate date);
}
