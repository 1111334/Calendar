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

    public void insertCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    // EventService.java





}
