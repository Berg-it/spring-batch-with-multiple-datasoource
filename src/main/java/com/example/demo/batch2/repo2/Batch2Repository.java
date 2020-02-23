package com.example.demo.batch2.repo2;

import com.example.demo.batch2.domain2.Batch2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Batch2Repository extends JpaRepository<Batch2, Long> {

}
