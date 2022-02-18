package com.wolfpack.assignment.repositories;

import com.wolfpack.assignment.model.Wolf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WolfRepository extends JpaRepository<Wolf, Long> {
    Optional<Wolf> findWolfByName(String name);
}
