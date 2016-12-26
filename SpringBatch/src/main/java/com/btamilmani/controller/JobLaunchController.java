package com.btamilmani.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLaunchController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	JobRegistry jobRegistry;
	
	@Autowired
	@Qualifier("inputChannel")
	MessageChannel inputChannel;
	
	@Autowired
	@Qualifier("controlChannel")
	MessageChannel controlChannel;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="launchJob", method=RequestMethod.GET)
	public void launchJob(HttpServletRequest request) {
		JobParametersBuilder builder = new JobParametersBuilder();
		Enumeration<String> reqParams = request.getParameterNames();
		while(reqParams.hasMoreElements()){
			String paramName = reqParams.nextElement();
			if(!"job".equals(paramName)){
				builder.addString(paramName, request.getParameter(paramName));
			}
			
		}
		try {
			jobLauncher.run(jobRegistry.getJob(request.getParameter("job")), builder.toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException | NoSuchJobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="launchTobacoDataLoad", method=RequestMethod.GET)
	public void launchTobacoDataLoad(HttpServletRequest request) {
		inputChannel.send(MessageBuilder.withPayload(request.getParameter("fileName")).build());
		
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="launchFilePoller", method=RequestMethod.GET)
	public void launchFilePoller() {
		controlChannel.send(MessageBuilder.withPayload("@'filesInChannelAdapter.adapter'.start()").build());
		
	}
}
