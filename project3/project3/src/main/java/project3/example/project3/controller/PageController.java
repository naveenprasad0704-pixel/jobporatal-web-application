package project3.example.project3.controller;

import project3.example.project3.model.Job;
import project3.example.project3.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class PageController {

    private final JobRepository jobRepository;

    public PageController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // ================= LOGIN =================
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin() {
        return "redirect:/home";
    }

    // ================= HOME =================
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // ================= JOBS =================
    @GetMapping("/jobs")
    public String jobs(Model model) {

        List<Job> jobs = jobRepository.findAll();

        if (jobs.isEmpty()) {
            jobs = getDefaultJobs();
            jobRepository.saveAll(jobs);
        }

        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    // ================= DETAILS =================
    @GetMapping("/job/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {

        Job job = jobRepository.findById(id).orElse(null);
        model.addAttribute("job", job);

        return "job-details";
    }

    // ================= UPLOAD =================
    @GetMapping("/upload")
    public String upload(@RequestParam Long jobId, Model model) {

        Job job = jobRepository.findById(jobId).orElse(null);

        model.addAttribute("job", job);
        model.addAttribute("jobId", jobId);

        return "upload";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("resume") MultipartFile file,
                               @RequestParam Long jobId) {

        if (!file.isEmpty()) {
            System.out.println("Uploaded: " + file.getOriginalFilename());
        }

        return "redirect:/success";
    }

    // ================= SUCCESS =================
    @GetMapping("/success")
    public String success() {
        return "success";
    }

    // ================= DEFAULT JOBS =================
    private List<Job> getDefaultJobs() {

        List<Job> jobs = new ArrayList<>();

        jobs.add(createJob("Java Developer", "TCS", "Spring Boot + Microservices"));
        jobs.add(createJob("Frontend Developer", "Infosys", "React / Angular"));
        jobs.add(createJob("Backend Developer", "Wipro", "REST APIs"));
        jobs.add(createJob("Full Stack Developer", "Amazon", "Java + React"));
        jobs.add(createJob("DevOps Engineer", "IBM", "CI/CD"));

        return jobs;
    }

    private Job createJob(String title, String company, String desc) {
        Job j = new Job();
        j.setTitle(title);
        j.setCompany(company);
        j.setDescription(desc);
        return j;
    }
}