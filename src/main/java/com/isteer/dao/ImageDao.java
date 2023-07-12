package com.isteer.dao;

import java.util.List;

import com.isteer.module.ImageModule;

public interface ImageDao {
	
	public void addImageToDB(List<ImageModule> imageModules);
	
	public byte[] getImageFromDB(String imageUUID);
	
	public List<String> getAllImagesFromDB();
	
	

}
