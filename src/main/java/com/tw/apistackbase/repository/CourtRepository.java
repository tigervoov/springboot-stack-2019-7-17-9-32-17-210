package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court,Integer> {
}
