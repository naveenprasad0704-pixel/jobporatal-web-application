package project3.example.project3.controller;

import project3.example.project3.model.Job;
import project3.example.project3.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // GET ALL JOBS
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // GET JOB BY ID
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    // ADD NEW JOB
    @PostMapping
    public Job addJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    // DELETE JOB
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return "Job deleted successfully with id: " + id;
    }
}