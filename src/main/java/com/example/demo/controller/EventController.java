package com.example.demo.controller;


import com.example.demo.DTO.InvitoDTO;
import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.service.CalendarService;
import com.example.demo.service.EventService;
import com.example.demo.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    //  public EventController(EventService eventService) {
    //      this.eventService = eventService;
    //  }

    @PostMapping(value = "/post-event")
    public ResponseEntity<String> insertEvent(@RequestBody Event event) {
        eventService.insertEvent(event);
        try {
            return ResponseEntity.ok("Event inserted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/calendars/{calendarId}/events") //Funziona
    public ResponseEntity<String> associateEventToCalendar(@PathVariable Long calendarId, @RequestBody Event event) {
        try {
            //associare l'evento al calendario
            eventService.associateEventToCalendar(calendarId, event);
            return ResponseEntity.ok("Evento associato al calendario con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante l'associazione dell'evento al calendario: " + e.getMessage());
        }
    }

    //@PostMapping("/{calendarID}/start/{startTime}/duration/{duration}")
    //public ResponseEntity createNewEvent(@PathVariable int calendarID,
    //                                     @RequestBody Event event,
    //                                     @PathVariable LocalDateTime startTime,
    //                                     @PathVariable int duration){
//
    //    try {
    //        return ResponseEntity.ok(eventService.createEvent(calendarID, event, startTime, duration));
    //    } catch (Exception e){
    //        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //    }
    //}

    @GetMapping(value = "/get-events")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @PutMapping(value = "/update-event")
    public ResponseEntity<String> updateEvent(@RequestBody Event event) {
        try {
            eventService.modifyEvent(event);
            return ResponseEntity.ok("Event updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete-event/{eventId}") // Funziona
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        try {
            eventService.deleteEvent(eventId);
            return ResponseEntity.ok("Event deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
