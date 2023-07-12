package com.isteer.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.isteer.module.FileResponse;
import com.isteer.module.ImageModule;

public interface ImageService {

	public FileResponse addImageToDB(List<MultipartFile> files);

	public byte[] getImageFromDB(String imageUUID);
	
	public List<String> getAllImagesFromDB();

}
