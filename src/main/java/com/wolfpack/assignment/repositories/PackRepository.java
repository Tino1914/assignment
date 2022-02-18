package com.wolfpack.assignment.repositories;

import com.wolfpack.assignment.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {
    Optional<Pack> findPackByPackName(String packName);
}
