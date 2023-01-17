package com.plural.conferencedemo.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "sessions")
public class Session {
    // attributes do not respect camel case notations because they
    // need to match table notations in order to auto bind without annotations
    // otherwise that is done with @Column annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int session_id;
    private String session_name;
    private String session_description;
    private int session_length;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany()
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    public List<Speaker> speakers;

    public Session() {
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public int getSession_length() {
        return session_length;
    }

    public void setSession_length(int session_length) {
        this.session_length = session_length;
    }
}
