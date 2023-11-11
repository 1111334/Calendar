package com.example.demo.controller;


import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get-event")
    public ResponseEntity<Event> getEvent() {
        try {
            return ResponseEntity.ok(eventService.getEventById(1L));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
