package com.dedsec.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dedsec_product_details")
public class Product {

	@Id
	@GeneratedValue
	private long productId;

	@Column(name = "txt_product_name")
	private String productName;

	@Column(name = "txt_product_desc")
	private String productDesc;

	@Column(name = "txt_product_code")
	private String productCode;

	@Column(name = "txt_product_group")
	private String productGroup;

	@Column(name = "txt_img_file_name")
	private String imageFileName;

	@OneToMany(mappedBy = "product")
	private List<Associates> listAssociates;

	@Transient
	private String errorCode;
	@Transient
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public List<Associates> getListAssociates() {
		return listAssociates;
	}

	public void setListAssociates(List<Associates> listAssociates) {
		this.listAssociates = listAssociates;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productCode=" + productCode + ", productGroup=" + productGroup + ", imageFileName=" + imageFileName
				+ ", listAssociates=" + listAssociates + "]";
	}

}
