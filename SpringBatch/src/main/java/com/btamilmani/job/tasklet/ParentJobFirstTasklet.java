package com.btamilmani.job.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ParentJobFirstTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		System.out.println("<<<<<<<<<From ParentJobFirstTasklet>>>>>>>>>>>>>>>>");
		//String jobParam =   chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().getString("deciderParam");
		String Job1Param1 =  (String) chunkContext.getStepContext().getJobParameters().get("Job1Param1");
		String Job2Param1 =  (String) chunkContext.getStepContext().getJobParameters().get("Job2Param1");
		String Job2Param2 =  (String) chunkContext.getStepContext().getJobParameters().get("Job2Param2");
		
		System.out.println(Job1Param1+", "+Job2Param1+", "+Job2Param2);
		//adding a value in StepExecutionContext
		//chunkContext.getStepContext().getStepExecution().getExecutionContext().put("step1-value", 10);
		//adding a value in Job Execution Context
		//chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("job-value1", 100);
		

		return RepeatStatus.FINISHED;
	}

}
