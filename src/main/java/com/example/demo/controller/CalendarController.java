package com.example.demo.controller;


import com.example.demo.model.Calendar;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/calendar/{userID}")
    public ResponseEntity createNewCalendar(@PathVariable Long userID, @RequestBody Calendar calendar){
        try {
            return ResponseEntity.ok(calendarService.createCalendar(userID, calendar));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/update-calendar/{calendarId}")
    public ResponseEntity<String> updateCalendar(@PathVariable Long calendarId, @RequestBody Calendar calendar) {
        try {
            calendarService.updateCalendar(calendarId, calendar);
            return ResponseEntity.ok("Calendario aggiornato con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante l'aggiornamento del calendario: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-calendar/{calendarId}")
    public ResponseEntity<String> deleteCalendar(@PathVariable Long calendarId) {
        try {
            calendarService.deleteCalendar(calendarId);
            return ResponseEntity.ok("Calendario eliminato con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante l'eliminazione del calendario: " + e.getMessage());
        }
    }






}
