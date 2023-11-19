package com.example.demo.controller;


import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendars")
    public List<Calendar> getCalendars() {
        return calendarService.viewCalendar();
    }

    @PostMapping("/insert-calendar")
    public ResponseEntity<?> insertCalendar(@RequestBody Calendar calendar) {
        calendarService.insertCalendar(calendar);
        return ResponseEntity.ok(calendar);
    }



}
