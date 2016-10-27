package com.btamilmani.job.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SecondTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("<<<<<<<<<From SecondTasklet>>>>>>>>>>>>>>>>");
		//String param2 = (String)  chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("param2");
		String param2 =  (String) chunkContext.getStepContext().getJobParameters().get("param2");
		if(!"2".equals(param2)){
			throw new Exception("Thrown deliberately from SecondTasklet");
		}
		return RepeatStatus.FINISHED;
	}

}
