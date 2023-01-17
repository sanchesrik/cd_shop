package com.example.demo.repositories;

import com.example.demo.domain.PersonList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<PersonList, Integer> {
}
