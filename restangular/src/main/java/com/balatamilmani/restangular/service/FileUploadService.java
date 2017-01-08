package com.balatamilmani.restangular.service;

import org.springframework.stereotype.Component;

@Component
public class FileUploadService {

	public void saveFile(byte bytes[]){
		System.out.println(bytes.length);
	}
}
