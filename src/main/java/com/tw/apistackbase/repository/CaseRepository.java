package com.tw.apistackbase.repository;


import com.tw.apistackbase.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Integer> {

    List<Case> findByOrderByTimeDesc();

    @Query(value = "select * from case where case_name=:caseName",nativeQuery = true)
    List<Case> findAllByName(@Param("caseName")String caseName );
}
