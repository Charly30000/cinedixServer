package com.cinedix.server.app.models.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public String copy(MultipartFile file) throws IOException;
	
	public boolean delete(String filename);
	
	public void deleteAllCarpetaImagenes();
	
	public void init() throws IOException;
}
