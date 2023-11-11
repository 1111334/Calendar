package com.example.demo.controller;


import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/get-event")
    public List<Event> getEvent() {
     return eventService.getEvent();
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

    @DeleteMapping(value = "/delete-event")
    public ResponseEntity<String> deleteEvent(@RequestBody Event event) {
        try {
            eventService.deleteEvent(event);
            return ResponseEntity.ok("Event deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
