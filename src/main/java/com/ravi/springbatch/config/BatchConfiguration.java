package com.ravi.springbatch.config;

import com.ravi.springbatch.batchjob.*;
import com.ravi.springbatch.domain.NameList;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    @Autowired
    APICallJobExecutionListener apiCallJobExecutionListener;

    @Autowired
    APICallStepExecutionListener apiCallStepExecutionListener;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step stepOne() {
        return steps.get("stepOne")
                .listener(apiCallStepExecutionListener)
                .tasklet(executeStepOne())
                .build();
    }

    @Bean
    public Step stepTwo() {
        return steps.get("stepTwo").<NameList, List<String>>
                chunk(0)
                .reader(new APIReader())
                .processor(new APIProcessor())
                .writer(new APIWriter())
                .build();
    }

    private Tasklet executeStepOne() {
        return (stepContribution, chunkContext) -> {
            System.out.println("Step One done : " + stepContribution + " " + chunkContext);
            return RepeatStatus.FINISHED;
        };
    }


    @Bean
    public Job triggerInitialLoad() {
        return jobs.get("triggerInitialLoad")
                .listener(apiCallJobExecutionListener)
                .start(stepOne())
                .next(stepTwo())
                .build();
    }
}
