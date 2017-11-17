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

@XmlRootElement(name="features")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Features implements Serializable {

	private static final long serialVersionUID = -1729127363760144822L;

		@Id
		private Integer featureId;
		
		private String name;
		private String description;
		
		@Enumerated(EnumType.STRING)
		private FeaturesCategory category;
		
		
		@XmlElement
		public Integer getFeatureId() {
			return featureId;
		}
		public void setFeatureId(Integer featureId) {
			this.featureId = featureId;
		}
		@XmlElement
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@XmlElement
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@XmlElement
		public FeaturesCategory getCategory() {
			return category;
		}
		public void setCategory(FeaturesCategory category) {
			this.category = category;
		}
		public Features(Integer featureId, String name, String description,
				FeaturesCategory category) {
			super();
			this.featureId = featureId;
			this.name = name;
			this.description = description;
			this.category = category;
		}
		public Features() {
			super();
		}
		public static String BASE="http://localhost:8080/TAM2/data/";
		public static String BASE_URL = "http://localhost:8080/TAM2/data/features/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URL + this.getFeatureId();
		return new AtomLink(restUrl,"get-features");	
		}
		
		public void setLink(AtomLink link){}
}

