package com.example.demo.controller;


import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/events/{eventId}/calendars")
    public ResponseEntity<String> associateEventToCalendar(@PathVariable Long eventId, @RequestBody Calendar calendar) {
        try {
            //associare il calendario all'evento
            calendarService.associateCalendarToEvent(eventId, calendar);
            return ResponseEntity.ok("Calendario associato all'evento con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante l'associazione del calendario all'evento: " + e.getMessage());
        }
    }






}
