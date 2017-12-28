package com.notemates.rockjob.resolver;

import com.notemates.rockjob.exception.NoSuchJobException;
import com.notemates.rockjob.job.RockJobRunnable;

import java.util.Map;

public class RockJobManager {
    private Map<String, RockJobRunnable> jobs;

    public RockJobManager(Map<String, RockJobRunnable> jobs) {
        this.jobs = jobs;
    }

    public void runJob(String jobId) throws Exception {
        if (!jobs.containsKey(jobId)) {
            throw new NoSuchJobException("Job with id " + jobId + " does not exist");
        }
        jobs.get(jobId).runJob();
    }
}
