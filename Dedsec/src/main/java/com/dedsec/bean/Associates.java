package com.dedsec.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dedsec_associates_details")
public class Associates {
	@Id
	@GeneratedValue
	private long associateId;

	@Column(name = "txt_associate_name")
	private String associateName;

	@Column(name = "dat_date_of_birth")
	private Date associateDateOfBirth;

	@Column(name = "txt_aadhar_number")
	private String associateAdhaarNo;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)

	@JoinColumn(name = "txt_product_code")
	@JsonIgnoreProperties("associateDetails")
	private Product product;

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public Date getAssociateDateOfBirth() {
		return associateDateOfBirth;
	}

	public void setAssociateDateOfBirth(Date associateDateOfBirth) {
		this.associateDateOfBirth = associateDateOfBirth;
	}

	public String getAssociateAdhaarNo() {
		return associateAdhaarNo;
	}

	public void setAssociateAdhaarNo(String associateAdhaarNo) {
		this.associateAdhaarNo = associateAdhaarNo;
	}

	public long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(long associateId) {
		this.associateId = associateId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Associates [associateId=" + associateId + ", associateName=" + associateName + ", associateDateOfBirth="
				+ associateDateOfBirth + ", associateAdhaarNo=" + associateAdhaarNo + ", product=" + product + "]";
	}

}
