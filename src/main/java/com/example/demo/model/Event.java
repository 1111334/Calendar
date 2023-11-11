package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private long eventId;
    private String title;
    private String date;
    private String location;
    @OneToMany
    private List<User> attendees;

    public Event(String title, String date, String location, List<User> attendees) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.attendees = attendees;
    }


    public Event() {

    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void addAttendee(User user) {
        this.attendees.add(user);
    }


}

