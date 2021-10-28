package com.ravi.springbatch.batchjob;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class APICallJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before job : " + jobExecution.getJobInstance().getJobName());
        System.out.println("Before job : " + jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("Name","Ravi");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After job : " + jobExecution.getExecutionContext());
    }
}
