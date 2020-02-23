package com.example.demo.batch1.repo1;

import com.example.demo.batch1.domain.Batch1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch1, Long> {


}
