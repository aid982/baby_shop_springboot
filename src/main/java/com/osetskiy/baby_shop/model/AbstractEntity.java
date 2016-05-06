package com.osetskiy.baby_shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	@Id	
	private String id;
	
	
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractEntity that = (AbstractEntity) obj;

		return this.id.equals(that.getId());
	}

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
