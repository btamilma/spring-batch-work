package com.balatamilmani.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FirstTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		System.out.println("<<<<<<<<<From FirstTasklet>>>>>>>>>>>>>>>>");
		String param1 =  (String) chunkContext.getStepContext().getJobParameters().get("param1");
		System.out.println("The Job Param: "+param1);
		return RepeatStatus.FINISHED;
	}

}
