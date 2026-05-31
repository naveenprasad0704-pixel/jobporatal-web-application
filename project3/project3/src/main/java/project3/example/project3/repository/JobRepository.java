package project3.example.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.example.project3.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    // JpaRepository provides findAll() and save() methods by default
}