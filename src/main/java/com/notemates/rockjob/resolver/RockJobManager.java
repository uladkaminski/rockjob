package com.notemates.rockjob.resolver;

import com.notemates.rockjob.job.RockJobRunnable;

import java.util.Map;

public class RockJobManager {
    private Map<String, RockJobRunnable> jobs;

    public RockJobManager(Map<String, RockJobRunnable> jobs) {
        this.jobs = jobs;
    }
}
