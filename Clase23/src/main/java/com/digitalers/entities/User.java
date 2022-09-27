package com.digitalers.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import com.digitalers.commons.text.StringUtils;
import com.digitalers.exceptions.GenericRuntimeException;

public class User extends GenericEntity {
	private String email;
	private String key;
	private boolean active;

	public User() {
		super();
	}

	public User(Long id, LocalDateTime creationDate, String email, String key, boolean active) {
		super(id, creationDate);
		setEmail(email);
		this.key = key;
		this.active = active;
	}

	public int hashCode() {
		return Objects.hash(active, email, key);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return active == other.active && Objects.equals(email, other.email) && Objects.equals(key, other.key);
	}

	public String toString() {
		return "User [id=" + id + ", creationDate=" + creationDate + ", email=" + email + ", key=" + key + ", active="
				+ active + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!StringUtils.isMail(email)) {
			throw new GenericRuntimeException("debe ser un correo valido");
		}
		this.email = email;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
