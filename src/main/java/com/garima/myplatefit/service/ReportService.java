package com.garima.myplatefit.service;


import com.garima.myplatefit.model.Meal;
import com.garima.myplatefit.model.User;
import com.garima.myplatefit.model.WeightLog;
import com.garima.myplatefit.repository.MealRepository;
import com.garima.myplatefit.repository.WeightLogRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;


@Service
public class ReportService {


    @Autowired
    private MealRepository mealRepository;


    @Autowired
    private WeightLogRepository weightLogRepository;


    public byte[] generateWeeklyReport(User user) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();


            // Title
            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            document.add(new Paragraph("Weekly Health Report", titleFont));
            document.add(new Paragraph("User: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Generated on: " + LocalDate.now()));
            document.add(new Paragraph(" "));


            // Weight Logs
            document.add(new Paragraph("Weight Logs:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            List<WeightLog> weights = weightLogRepository.findByUserAndDateAfter(user, LocalDate.now().minusDays(7));
            for (WeightLog w : weights) {
                document.add(new Paragraph(w.getDate() + ": " + w.getWeight() + " kg"));
            }
            document.add(new Paragraph(" "));


            // Meal Logs
            document.add(new Paragraph("Meal Logs:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            List<Meal> meals = mealRepository.findByUserAndDateAfter(user, LocalDate.now().minusDays(7));
            for (Meal m : meals) {
                document.add(new Paragraph(m.getDate() + ": " + m.getFoodName() + " - " + m.getCalories() + " kcal"));
            }


            document.close();
            return out.toByteArray();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

