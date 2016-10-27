package com.btamilmani.job.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ChildJobFirstTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		System.out.println("<<<<<<<<<From ChildJobFirstTasklet>>>>>>>>>>>>>>>>");
		//String jobParam =   chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().getString("deciderParam");
		String Job2Param1 =  (String) chunkContext.getStepContext().getJobParameters().get("Job2Param1");
		String Job2Param2 =  (String) chunkContext.getStepContext().getJobParameters().get("Job2Param2");
		
		System.out.println(Job2Param1+", "+Job2Param2);
		
		return RepeatStatus.FINISHED;
	}

}
