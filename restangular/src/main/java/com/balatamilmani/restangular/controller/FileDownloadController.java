package com.balatamilmani.restangular.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {

	@RequestMapping(method = RequestMethod.GET, value = "/downloadFile/{fileId}", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void downloadFile(@PathVariable String fileId, HttpServletResponse response) throws Exception{

		if("1".equals(fileId )){
			throw new Exception();
		}
		InputStream inputStream = null;
		OutputStream outputStream = null;
		Path path = Paths.get("/home/btamilma/Documents/Traffic_Violations.csv");
		
			try {
				//http://stackoverflow.com/questions/6293893/how-to-force-files-to-open-in-browser-instead-of-download-pdf
				//Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				inputStream = Files.newInputStream(path);
				outputStream = response.getOutputStream();
				response.reset();
				response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				//response.setContentType("application/pdf");
				//To indicate to the browser that the file should be viewed in the browser:
				//Content-Disposition: inline; filename="filename.pdf"
				//To have the file downloaded rather than viewed:
				//Content-Disposition: attachment; filename="filename.pdf"
				response.setHeader("Content-Disposition","attachment;filename=\"HerndonTennisRegn.pdf\"");
				IOUtils.copyLarge(inputStream, outputStream);
				outputStream.flush();
			} catch (IOException|RuntimeException e) {
				System.out.println(e.getMessage());
				throw new Exception();
			} finally {
				if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(outputStream != null){
					try {
						outputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
}
