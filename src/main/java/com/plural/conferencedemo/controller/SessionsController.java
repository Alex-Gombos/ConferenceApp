package com.plural.conferencedemo.controller;

import com.plural.conferencedemo.model.Session;
import com.plural.conferencedemo.model.Speaker;
import com.plural.conferencedemo.repo.SessionRepo;
import com.plural.conferencedemo.repo.SpeakerRepo;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController{
    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private SpeakerRepo speakerRepo;

    @GetMapping
    public List<Session> list(){
        return sessionRepo.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    Optional<Session> get(@PathVariable int id){
        return sessionRepo.findById(id);
    }

    @PostMapping
    public Session create (@RequestBody final Session session){
        //TO DO: read about @RequestBody
        return sessionRepo.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)

    public void delete(@PathVariable int id){
        sessionRepo.deleteById(id);
        Optional<Session> session = sessionRepo.findById(id);
        List<Speaker> speakerList = speakerRepo.findAll();
        for(Speaker speaker: speakerList){
            speaker.sessions.remove(session);
        }
    }
    @Deprecated
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable int id, @RequestBody Session session){
        Session existingSession = sessionRepo.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepo.saveAndFlush(existingSession);
    }

    public SessionRepo getSessionRepo() {
        return sessionRepo;
    }
    @Autowired
    public void setSessionRepo(SessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

//    //TO DO: change the above delete request to use @DeleteMapping
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    @DeleteMapping
//    public void delete(@PathVariable Long id){
//        sessionRepo.deleteById(id);
//    }

}
