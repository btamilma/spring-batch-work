package com.balatamilmani;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainCaller {

	public static void main(String[] args) {
		JobExecution jobExecution = null;
		Job job = null;	
		JobLauncher jobLauncher = null;
		JobRegistry jobRegistry = null;
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")){

			jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
			jobRegistry = (JobRegistry) context.getBean(JobRegistry.class);
			/*job = jobRegistry.getJob("loadTobacoData");
			JobParameters jobParameters = new JobParametersBuilder().addLong("jobId", 121L).
					addString("outputFile", "tobaco-report.csv").toJobParameters();*/
			
			//Launch job within job
			/*job = jobRegistry.getJob("parentJob");
			JobParameters jobParameters = new JobParametersBuilder().addLong("jobId", 121L).
					addString("Job1Param1", "Job1Param11").
					addString("Job2Param1", "Job2Param11").
					addString("Job2Param2", "Job2Param12").toJobParameters();*/
			
			//generate Report
			/*job = jobRegistry.getJob("generateTobacoReport");
			JobParameters jobParameters = new JobParametersBuilder().addLong("jobId", 121L).
					addString("JobId", "222").
					addString("outputFile", "tobaco-report.csv").toJobParameters();*/
			
			//Load Data into Table
			job = jobRegistry.getJob("batchCodeJob");
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("param1", args[0]).toJobParameters();
			
			jobExecution = jobLauncher.run(job, jobParameters);
			while(jobExecution.isRunning()){
				System.out.println("still running");
				Thread.sleep(1000);
			}
			System.out.println(jobExecution.getExitStatus());
			
		} catch (NoSuchJobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
