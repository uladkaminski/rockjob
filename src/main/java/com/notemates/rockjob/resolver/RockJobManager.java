package com.notemates.rockjob.resolver;

import com.notemates.rockjob.exception.NoSuchJobException;
import com.notemates.rockjob.exception.RockJobException;
import com.notemates.rockjob.job.RockJobRunnable;

import java.util.Map;
import java.util.Map.Entry;

public class RockJobManager {
    private Map<String, Entry<Boolean, RockJobRunnable>> jobs;

    public RockJobManager(Map<String, Entry<Boolean, RockJobRunnable>> jobs) {
        this.jobs = jobs;
    }

    public void runJob(String jobId) throws Exception {
        if (!jobs.containsKey(jobId)) {
            throw new NoSuchJobException("Job with id " + jobId + " does not exist");
        }
        Entry<Boolean, RockJobRunnable> jobEntry = jobs.get(jobId);
        if (jobEntry.getKey()) {
            new Thread(() -> {
                try {
                    jobEntry.getValue().runJob();
                } catch (Exception e) {
                    throw new RockJobException("Exception during running job", e);
                }
            }).run();
        } else {
            jobEntry.getValue().runJob();
        }


    }
}
