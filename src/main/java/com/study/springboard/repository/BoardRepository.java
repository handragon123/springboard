package com.study.springboard.repository;

import com.study.springboard.entity.springboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<springboard, Integer> {
}
