package com.btamilmani.job.tasklet;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListenerExample implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("from before job");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("from after job");
		
	}

}
