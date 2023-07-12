package com.isteer.module;

public class ImageModule {
	
	private int imageId;
	private byte[] imageData;
	private String UUID;
	public ImageModule(int imageId, byte[] imageData, String uUID) {
		super();
		this.imageId = imageId;
		this.imageData = imageData;
		UUID = uUID;
	}
	public ImageModule(byte[] imageData, String uUID) {
		super();
		this.imageData = imageData;
		UUID = uUID;
	}
	public ImageModule() {
		super();
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
}
