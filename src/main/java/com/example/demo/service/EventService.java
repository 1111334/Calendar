package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void insertEvent(Event event) {
        eventRepository.save(event);
    }

    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId).get();
    }
    // public List<Event> viewEvent() {
  //     return eventRepository.findAll();
  // }

  // public void putEvent(Event updatedEvent) {
  //     eventRepository.save(updatedEvent);
  // }

  // public void deleteEvent(Event event) {
  //     eventRepository.delete(event);
  // }


    // public EventService(EventRepository eventRepository) {
    //     this.eventRepository = eventRepository;
    // }
}
