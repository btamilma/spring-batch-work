package com.btamilmani.job.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FirstTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("<<<<<<<<<From FirstTasklet>>>>>>>>>>>>>>>>");
		//String jobParam =   chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().getString("deciderParam");
		String param1 =  (String) chunkContext.getStepContext().getJobParameters().get("param1");
		
		//adding a value in StepExecutionContext
		//chunkContext.getStepContext().getStepExecution().getExecutionContext().put("step1-value", 10);
		//adding a value in Job Execution Context
		//chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("job-value1", 100);
		
		if(!"1".equals(param1)){
			throw new Exception("Thrown deliberately from FirstTasklet");
		}
		return RepeatStatus.FINISHED;
	}

}
