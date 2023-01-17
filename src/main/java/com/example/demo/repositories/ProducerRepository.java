package com.example.demo.repositories;

import com.example.demo.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {
  @Query("select a from Producer a where a.id in :ids")
  List<Producer> findById(@Param("id") final List<Integer> ids);

}
