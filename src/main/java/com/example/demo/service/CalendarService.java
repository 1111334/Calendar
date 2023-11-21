package com.example.demo.service;

import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Calendar> viewCalendar() {
        return ResponseEntity.ok(calendarRepository.findAll()).getBody();
    }

    public void associateCalendarToEvent(Long eventId, Calendar calendar) throws Exception {
        // Verifica che l'evento esista
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            throw new Exception("Calendario non trovato con ID " + eventId);
        }

        Event event = eventOptional.get(); //ottenere oggetto calendario
        calendar.setEvents(List.of(event)); // imposta il calendario dell'evento sull'oggetto evento fornito come parametro.

        // Salva il calendario nel database
        calendarRepository.save(calendar);
    }
    public void insertCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    // EventService.java





}
