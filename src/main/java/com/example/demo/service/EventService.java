package com.example.demo.service;

import ch.qos.logback.core.boolex.EventEvaluator;
import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    UserRepository userRepository;

    public void insertEvent(Event event) {
        eventRepository.save(event);
    }

    //public void associateEventToCalendar(Long id, Event event) throws Exception {
    //    // Verifica che il calendario esista
    //    Calendar calendar = event.getCalendar();
    //    if (calendar == null) {
    //        throw new Exception("Calendar not found");
    //    }else {
    //        event.setCalendar(calendar);
    //        eventRepository.save(event);
    //    }

    //}


    @Transactional
    public void associateEventToCalendar(Long calendarId, Event event) throws Exception {
        // Verifica che il calendario esista

        if (calendarRepository.findById(calendarId).isPresent()) {
            Calendar calendar = calendarRepository.findById(calendarId).orElse(null);
            event.setCalendar(calendar);
            List<Event> events;
            events = calendar.getEvents();
            events.add(event);
            calendar.setEvents(events);

            calendarRepository.save(calendar);
            eventRepository.save(event);
        } else {
            throw new Exception("Calendario non trovato con ID " + calendarId);
        }
        // Salva l'evento nel database
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void modifyEvent(Event updatedEvent) {
        eventRepository.save(updatedEvent);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);

    }

}
