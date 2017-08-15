package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class TransactionalEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@NotNull
	protected String referenceId = UUID.randomUUID().toString();

	@Version
	protected Integer version;


	protected String createdBy;

	@NotNull
	protected Date CreatedAt;

	protected String updatedBy;

	protected Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		if (this.getId() == null) {
			return -1;
		}
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}

		if (this.getClass().equals(that.getClass())) {
			TransactionalEntity thatTE = (TransactionalEntity) that;
			if (thatTE.getId() == null || this.getId() == null) {
				return false;
			}
			if (thatTE.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

	@PrePersist
	public void beforePersist() {
		this.setCreatedAt(new Date());
	}

	@PreUpdate
	public void beforeUpdate() {
		this.setUpdatedAt(new Date());
	}

}
