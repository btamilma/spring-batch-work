package com.btamilmani.batch.core;

import java.io.File;
import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

public class FileMessageToJobRequest {
    private Job job;
    private String fileParameterName;

    public void setFileParameterName(String fileParameterName) {
        this.fileParameterName = fileParameterName;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Transformer
    public JobLaunchRequest toRequest(Message<Object> message) {

    	String fileName = null;
    	JobParametersBuilder jobParametersBuilder =
                new JobParametersBuilder();
    	
    	if(message.getPayload().getClass().isAssignableFrom(File.class)){
    		System.out.println("file message");
    		File file = (File)message.getPayload();
    		System.out.println(file.getAbsolutePath());
    		System.out.println(file.getName());
    		fileName = file.getName();
    	} else if(message.getClass().isAssignableFrom(String.class)){
    		fileName =  message.getPayload().toString();
    	}

        jobParametersBuilder.addString("fileName", fileName
               ).addLong("jobId", new Random().nextLong());

       return new JobLaunchRequest(job, jobParametersBuilder.toJobParameters());
    }
    
    //@Transformer
    /*public JobLaunchRequest toRequest(Message<String> message) {

    	JobParametersBuilder jobParametersBuilder =
            new JobParametersBuilder();



        return new JobLaunchRequest(job, jobParametersBuilder.toJobParameters());
    }*/
}
