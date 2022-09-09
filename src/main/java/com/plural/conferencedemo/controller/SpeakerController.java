package com.plural.conferencedemo.controller;

import com.plural.conferencedemo.model.Session;
import com.plural.conferencedemo.model.Speaker;
import com.plural.conferencedemo.repo.SpeakerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
    // autowiring to get access to CRUD operations to the dbs table and data
    @Autowired
    private SpeakerRepo speakerRepo;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepo.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Optional<Speaker> get(@PathVariable Long id){
        return speakerRepo.findById(id);
    }

    @PostMapping
    public Speaker create (@RequestBody final Speaker speaker){
        //TO DO: read about @RequestBody
        return speakerRepo.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        // TO DO: check for children records before deleting
        speakerRepo.deleteById(id);
    }
    @Deprecated
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepo.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "session_id");
        return speakerRepo.saveAndFlush(existingSpeaker);
    }

    public SpeakerRepo getSpeakerRepo() {
        return speakerRepo;
    }

    @Autowired
    public void setSpeakerRepo(SpeakerRepo speakerRepo) {
        this.speakerRepo = speakerRepo;
    }
}
