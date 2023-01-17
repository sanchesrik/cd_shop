package com.example.demo.repositories;

import com.example.demo.domain.Person_List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<Person_List, Integer> {
}
