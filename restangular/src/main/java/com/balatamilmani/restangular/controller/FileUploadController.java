package com.balatamilmani.restangular.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.balatamilmani.restangular.service.FileUploadService;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;
	
	private final String ROOT = "/home/btamilma/stsWorkspace/";
	public FileUploadController() {
		// TODO Auto-generated constructor stub
	}
	
	//http://stackoverflow.com/questions/1264709/convert-inputstream-to-byte-array-in-java
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte bytes[] = null;
		String fileUploadResponse = null;
		if (!file.isEmpty()) {
			try {
				//Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				IOUtils.copy(file.getInputStream(), buffer);
				bytes = buffer.toByteArray();
				fileUploadService.saveFile(bytes);
				fileUploadResponse  = "file uploaded successfully";
			} catch (IOException|RuntimeException e) {
			}
		} else {
			fileUploadResponse  = "file upload failed";
		}

		return fileUploadResponse;
	}

}
