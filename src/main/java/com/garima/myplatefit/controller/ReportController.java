package com.garima.myplatefit.controller;

import com.garima.myplatefit.model.User;
import com.garima.myplatefit.service.ReportService;
import com.garima.myplatefit.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.security.Principal;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @GetMapping("/weekly-report")  // Changed endpoint path
    public ResponseEntity<InputStreamResource> downloadWeeklyReport(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getUserById(userId);
        byte[] reportBytes = reportService.generateWeeklyReport(user);

        if (reportBytes == null) {
            return ResponseEntity.internalServerError().build();
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(reportBytes));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "weekly-report.pdf");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/download")  // Changed to POST mapping
    public ResponseEntity<byte[]> downloadWeeklyReportPost(Principal principal) {
        User user = userService.getByEmail(principal.getName());
        byte[] pdf = reportService.generateWeeklyReport(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "weekly-report.pdf");
        headers.setContentLength(pdf.length);
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}