package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import static javax.persistence.CascadeType.ALL;
@XmlRootElement(name="teams")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Team implements Serializable {
	
	private static final long serialVersionUID = -2804236345741115397L;
		@Id
		private Integer teamId;
		private String teamName;
		private String competence;
		
		@Enumerated(EnumType.STRING)
		private Fields domain;
		
		@OneToMany(cascade=ALL,orphanRemoval = true,fetch = FetchType.EAGER)
		@Fetch(FetchMode.SELECT)
		private List<TeamMembers> teamMembers= new ArrayList<>();
		
		
		@XmlElement
		public Integer getTeamId() {
			return teamId;
		}

		public void setTeamId(Integer teamId) {
			this.teamId = teamId;
		}
		@XmlElement
		public String getTeamName() {
			return teamName;
		}

		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}
		@XmlElement
		public String getCompetence() {
			return competence;
		}

		public void setCompetence(String competence) {
			this.competence = competence;
		}
		@XmlElement
		public Fields getDomain() {
			return domain;
		}

		public void setDomain(Fields domain) {
			this.domain = domain;
		}
		
		public void addTeamMember(TeamMembers member){
			this.teamMembers.add(member);
		}

		public Team() {
			super();
		}

		public Team(Integer teamId, String teamName, String competence,
				Fields domain) {
			super();
			this.teamId = teamId;
			this.teamName = teamName;
			this.competence = competence;
			this.domain = domain;
		}

		@XmlElementWrapper(name="teamMembers")@XmlElement(name="member")
		public List<TeamMembers> getTeamMembers() {
			return teamMembers;
		}

		public void setTeamMembers(List<TeamMembers> teamMembers) {
			this.teamMembers = teamMembers;
		}
		
		public static String BASE_URLt = Features.BASE+"teams/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URLt + this.getTeamId();
		return new AtomLink(restUrl,"get-teams");	
		}
		
		public void setLink(AtomLink link){}
		
}
