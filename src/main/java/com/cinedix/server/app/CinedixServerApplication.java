package com.cinedix.server.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cinedix.server.app.models.service.IUploadFileService;

@SpringBootApplication
public class CinedixServerApplication implements CommandLineRunner {

	@Autowired
	private IUploadFileService uploadFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(CinedixServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creamos la carpeta uploads
		uploadFileService.init();
	}

}
