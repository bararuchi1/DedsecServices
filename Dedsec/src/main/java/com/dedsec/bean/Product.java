package com.dedsec.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dedsec_product_details")
public class Product {

	@GeneratedValue
	private long productId;

	@Column(name = "txt_product_name")
	private String productName;

	@Column(name = "txt_product_desc")
	private String productDesc;

	@Id
	@Column(name = "txt_product_code", unique = true, nullable = false)
	private String productCode;

	@Column(name = "txt_product_group")
	private String productGroup;

	@Column(name = "txt_img_file_name")
	private String imageFileName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private List<Associates> associateDetails;

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

	public List<Associates> getAssociateDetails() {
		return associateDetails;
	}

	public void setAssociateDetails(List<Associates> associateDetails) {
		this.associateDetails = associateDetails;
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
				+ ", associateDetails=" + associateDetails + ", errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + "]";
	}

}
