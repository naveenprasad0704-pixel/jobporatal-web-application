package project3.example.project3.service;

import java.util.List;
import project3.example.project3.model.Job;

public interface JobService {
    List<Job> getAllJobs();
    void saveJob(Job job);
}