package com.dedsec.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="dedsec_associates_details")
public class Associates {
	@Id
	@GeneratedValue
	private long associateId;
	
	@Column(name="txt_associate_name")
	private String name;
	
	@Column(name="dat_date_of_birth")
	private Date dateOfBirth;

	@ManyToOne
	private Product product;

	public long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(long associateId) {
		this.associateId = associateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Associates [associateId=" + associateId + ", name=" + name + ", dateOfBirth=" + dateOfBirth
				+ ", product=" + product + "]";
	}
	
	
	
}
