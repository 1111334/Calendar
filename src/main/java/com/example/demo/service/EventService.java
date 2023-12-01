package com.example.demo.service;

import ch.qos.logback.core.boolex.EventEvaluator;
import com.example.demo.Util.RecurrenceManager;
import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.model.User;
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

    public Event createEvent(Long calendarID, Event event, LocalDateTime startTime, int duration) throws Exception {

        RecurrenceManager recurrenceManager = new RecurrenceManager();

        if (calendarRepository.findById(calendarID).isPresent()) {
            Calendar calendar = calendarRepository.findById(calendarID).orElse(null);

            event.setCalendar(calendar);

            recurrenceManager.setRecurrenceTimeAndDuration(event, startTime, duration);
        } else {
            throw new Exception(String.format("Calendar with ID %s not found", calendarID));
        }

        return eventRepository.save(event);
    }

    @Transactional
    public void associateEventToCalendar(Long calendarId, Event event) throws Exception {
        Optional<Calendar> optionalCalendar = calendarRepository.findById(calendarId);

        if (optionalCalendar.isPresent()) {
            Calendar calendar = optionalCalendar.get();

            // Aggiorna il lato degli eventi
            event.setCalendar(calendar);
            eventRepository.save(event);

            // Aggiorna il lato del calendario
            List<Event> events = calendar.getEvents();
            events.add(event);
            calendar.setEvents(events);

            // Stampa a console per debugging
            System.out.println("Calendario prima del salvataggio: " + calendar);

            // Salva il calendario
            calendarRepository.save(calendar);
        } else {
            throw new Exception("Calendario non trovato con ID " + calendarId);
        }
    }

    public List<Event> viewEventToCalendar(Long calendarID) throws Exception {

        if (calendarRepository.findById(calendarID).isPresent()) {
            Calendar calendar = calendarRepository.findById(calendarID).orElse(null);

            List<Event> events;
            events = calendar.getEvents();

            return events;

        } else {
            throw new Exception(String.format("Calendar with ID %s not exist", calendarID));
        }
    }

    @Transactional
    public String inviteUser(Long eventID, Long userID) throws Exception {

        Event event = eventRepository.findById(eventID).get();
        User user = userRepository.findById(userID).get();

        if (event == null || user == null) {

            throw new Exception("Event or user not found");
        } else {
            event.getUsers().add(user);
            user.getEvents().add(event);
            eventRepository.save(event);
            userRepository.save(user);
            return "User successfully invited ";
        }

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
