package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long user_id;
    private String username;
    private String email;
    private String password;

    //public User(String username, String email, String password) {
    //    this.username = username;
    //    this.email = email;
    //    this.password = password;
    //}

    public User() {

    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Calendar> calendars;


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Calendar> getCalendarList() {
        return calendars;
    }

    public void setCalendarList(List<Calendar> calendars) {
        this.calendars = calendars;
    }
}
