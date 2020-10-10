package com.dedsec.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "dedsec_image_details")
@Component
public class ImageModel {
	@Id
	@GeneratedValue
	private long id;
	private String imageFileName = "";
	@Column(name = "imageDetails", length = 1073741824)
	private byte[] imageFile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

}
