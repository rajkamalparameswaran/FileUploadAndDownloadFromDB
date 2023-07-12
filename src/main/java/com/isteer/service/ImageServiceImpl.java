package com.isteer.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.uuid.Generators;
import com.isteer.dao.ImageDao;
import com.isteer.exception.ImageNameNotFoundException;
import com.isteer.module.FileResponse;
import com.isteer.module.ImageModule;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageDao dao;

	@Override
	public FileResponse addImageToDB(List<MultipartFile> files) {
		try {
			
			
			List<String> resourceUrls=new ArrayList<String>();
			List<ImageModule> modules=files.stream().map(file->{
				String imageUUID=Generators.timeBasedGenerator().generate().toString();
				String resourceUrl=StringUtils.cleanPath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/fileFromDB/viewImage/").path(imageUUID).toUriString());
				resourceUrls.add(resourceUrl);
					try {
						return new ImageModule(file.getBytes(),imageUUID);
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}		
			}).filter(data-> data!=null).collect(Collectors.toList());
				dao.addImageToDB(modules);
				return new FileResponse(1, "sucessfully uploaded image to DB", resourceUrls);	
		} catch (Exception e) {			
			throw e;
		}
			
			
			
	}

	@Override
	public byte[] getImageFromDB(String imageUUID) {
		byte[] data=dao.getImageFromDB(imageUUID);
		if(data==null)
		{
			throw new ImageNameNotFoundException(0, "data download failed", "Id not found");
		}
		
		return data;
	}

	@Override
	public List<String> getAllImagesFromDB() {
		
		List<String> allImagesUUID=dao.getAllImagesFromDB().stream().map(UUID->ServletUriComponentsBuilder.fromCurrentContextPath().path("/fileFromDB/viewImage/") .path(UUID).toUriString()).collect(Collectors.toList());
		
		if(allImagesUUID.isEmpty())
		{
			throw new ImageNameNotFoundException(0, "data download failed", "No data found");
		}
		
		return allImagesUUID;
	}

}
