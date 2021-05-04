package com.cinedix.server.app.models.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	protected final Log logger = LogFactory.getLog(this.getClass());

	public final static String UPLOADS_FOLDER = Paths.get("../uploads/").toAbsolutePath().toString();

	@Override
	public String copy(MultipartFile file) throws IOException {
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

		Path rootPath = getPath(uniqueFilename);

		logger.info("rootPath: " + rootPath);

		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		
		if (archivo.exists() && archivo.canRead()) {
			if(archivo.delete()) {
				return true;
			}
			
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}
	
	@Override
	public void deleteAllCarpetaImagenes() {
		
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
		
	}

	@Override
	public void init() throws IOException {
		
		if(Files.notExists(Paths.get(UPLOADS_FOLDER))) {
			Files.createDirectory(Paths.get(UPLOADS_FOLDER));
			logger.info("No existe la carpeta "+ UPLOADS_FOLDER + " y la vamos a crear");
		}else {
			logger.info("La carpeta " + UPLOADS_FOLDER + " ya existe, no se creara de nuevo");
		}
	}

	

}
