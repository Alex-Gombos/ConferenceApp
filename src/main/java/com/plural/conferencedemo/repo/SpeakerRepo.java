package com.plural.conferencedemo.repo;

import com.plural.conferencedemo.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepo extends JpaRepository <Speaker, Long> {
}
