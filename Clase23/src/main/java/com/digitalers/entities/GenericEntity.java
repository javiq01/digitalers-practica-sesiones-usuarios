package com.digitalers.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class GenericEntity {
	protected Long id;
	protected LocalDateTime creationDate;

	public GenericEntity() {
		super();
	}

	public GenericEntity(Long id, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.creationDate = creationDate;
	}

	public int hashCode() {
		return Objects.hash(creationDate, id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericEntity other = (GenericEntity) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

}
