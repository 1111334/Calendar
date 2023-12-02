package com.example.demo.controller;


import com.example.demo.model.Event;
import com.example.demo.service.CalendarService;
import com.example.demo.service.EventService;
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


    @Autowired
    CalendarService calendarService;
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


    @PostMapping("/calendars/{calendarID}/events/{startTime}/{duration}")
    public ResponseEntity createNewEvent(@PathVariable Long calendarID,
                                         @RequestBody Event event,
                                         @PathVariable LocalDateTime startTime,
                                         @PathVariable int duration) {

        try {
            return ResponseEntity.ok(eventService.createEvent(calendarID, event, startTime, duration));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PostMapping("/calendars/{calendarId}/events")
    public ResponseEntity<String> associateEventToCalendar(@PathVariable Long calendarId, @RequestBody Event event) {
        try {
            //associare l'evento al calendario
            eventService.associateEventToCalendar(calendarId, event);
            return ResponseEntity.ok("Evento associato al calendario con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante l'associazione dell'evento al calendario: " + e.getMessage());
        }
    }


    @GetMapping(value = "/get-events")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{calendarID}")
    public ResponseEntity viewAllEventsToCalendar(@PathVariable Long calendarID) {

        try {
            return ResponseEntity.ok(eventService.viewEventToCalendar(calendarID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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


    @PutMapping("/{eventID}/user/{userID}")
    public ResponseEntity invitedUser(@PathVariable Long eventID, @PathVariable Long userID) {

        try {
            return ResponseEntity.ok(eventService.inviteUser(eventID, userID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
