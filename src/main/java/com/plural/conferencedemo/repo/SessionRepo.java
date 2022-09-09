package com.plural.conferencedemo.repo;

import com.plural.conferencedemo.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepo extends JpaRepository<Session, Long> {
}
