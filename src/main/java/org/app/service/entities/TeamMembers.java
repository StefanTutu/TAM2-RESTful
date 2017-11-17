package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="teammembers")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class TeamMembers implements Serializable {

	private static final long serialVersionUID = 796596608171004394L;
		@Id
		private Integer memberId;
		private String memberName;
		@Enumerated(EnumType.STRING)
		private Role role;
		
		
		@XmlElement
		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}
		@XmlElement
		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		@XmlElement
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public TeamMembers(Integer memberId, String memberName, Role role) {
			super();
			this.memberId = memberId;
			this.memberName = memberName;
			this.role = role;
		}

		public TeamMembers() {
			super();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((memberId == null) ? 0 : memberId.hashCode());
			result = prime * result
					+ ((memberName == null) ? 0 : memberName.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TeamMembers other = (TeamMembers) obj;
			if (memberId == null) {
				if (other.memberId != null)
					return false;
			} else if (!memberId.equals(other.memberId))
				return false;
			if (memberName == null) {
				if (other.memberName != null)
					return false;
			} else if (!memberName.equals(other.memberName))
				return false;
			if (role != other.role)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TeamMembers [memberId=" + memberId + ", memberName="
					+ memberName + ", role=" + role + "]";
		}
		
		public static String BASE_URLtm = Features.BASE+"teammembers/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URLtm + this.getMemberId();
		return new AtomLink(restUrl,"get-teammembers");	
		}
		
		public void setLink(AtomLink link){}
		
}
