package com.garima.myplatefit.service;

import com.garima.myplatefit.model.Meal;
import com.garima.myplatefit.model.User;
import com.garima.myplatefit.model.WeightLog;
import com.garima.myplatefit.repository.MealRepository;
import com.garima.myplatefit.repository.WeightLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private WeightLogRepository weightLogRepository;

    @Autowired
    private MealRepository mealRepository;

    public List<Map<String, Object>> getWeightLogs(User user) {
        List<WeightLog> logs = weightLogRepository.findByUserOrderByDateAsc(user);
        List<Map<String, Object>> data = new ArrayList<>();

        for (WeightLog log : logs) {
            Map<String, Object> point = new HashMap<>();
            point.put("date", log.getDate().toString());
            point.put("weight", log.getWeight());
            data.add(point);
        }
        return data;
    }

    public List<Map<String, Object>> getCalorieLogs(User user) {
        List<Map<String, Object>> data = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            List<Meal> meals = mealRepository.findByUserAndDate(user, date);
            int totalCalories = meals.stream().mapToInt(Meal::getCalories).sum();

            Map<String, Object> day = new HashMap<>();
            day.put("date", date.toString());
            day.put("calories", totalCalories);
            data.add(day);
        }
        return data;
    }
}
