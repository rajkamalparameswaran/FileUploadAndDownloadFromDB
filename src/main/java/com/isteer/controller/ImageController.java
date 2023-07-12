package com.isteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isteer.module.FileResponse;
import com.isteer.service.ImageService;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/uploadFilesInDB")
	public ResponseEntity<FileResponse> addImage(@RequestParam("file") List<MultipartFile> files) throws Exception
	{
		return new ResponseEntity<FileResponse>(imageService.addImageToDB(files),HttpStatus.CREATED);
	}
	
	@GetMapping("/fileFromDB/viewImage/{imageUUID}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imageUUID)
	{

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageService.getImageFromDB(imageUUID));
	
	}
	
	@GetMapping("/getAllImagesFromDB")
	public ResponseEntity<List<String>> getAllImages() {
		
		return new ResponseEntity<List<String>>(imageService.getAllImagesFromDB(),HttpStatus.OK);
	}

}
