package com.example.demo.service;

import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    CalendarRepository calendarRepository;



    public void insertEvent(Event event) {eventRepository.save(event);}

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

    public void associateEventToCalendar(Long calendarId, Event event) throws Exception {
        // Verifica che il calendario esista
        Optional<Calendar> calendarOptional = calendarRepository.findById(calendarId);
        if (!calendarOptional.isPresent()) {
            throw new Exception("Calendario non trovato con ID " + calendarId);
        }

        Calendar calendar = calendarOptional.get(); //ottenere oggetto calendario
        event.setCalendar(calendar); // imposta il calendario dell'evento sull'oggetto evento fornito come parametro.

        // Salva l'evento nel database
        eventRepository.save(event);
    }

    public List<Event> getEvents() {return eventRepository.findAll();}

    public void modifyEvent(Event updatedEvent) {
       eventRepository.save(updatedEvent);
   }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

}
