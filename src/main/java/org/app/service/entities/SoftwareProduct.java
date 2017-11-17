package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@XmlRootElement(name="softwares")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class SoftwareProduct implements Serializable {
	
	private static final long serialVersionUID = 5277668556277708893L;
		@Id
		private Integer applicationId;
		private String applicationName;
		private String domain;
		private String type;
		
		@Temporal(TemporalType.DATE)
		private Date releaseDate;
		
		private String requirements;
		
		@Enumerated(EnumType.STRING)
		private DevelopmentStage devStage;

		
		@OneToMany(cascade=ALL,orphanRemoval = true,fetch = FetchType.EAGER)
		@Fetch(FetchMode.SELECT)
		private List<Features> features=new ArrayList<>();
		
		@OneToMany(cascade=ALL,orphanRemoval = true,fetch = FetchType.EAGER)
		@Fetch(FetchMode.SELECT)
		private List<ProductRelease> productRelease=new ArrayList<>();
		
		@XmlElement
		public Integer getApplicationId() {
			return applicationId;
		}

		public void setApplicationId(Integer applicationId) {
			this.applicationId = applicationId;
		}
		@XmlElement
		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}
		@XmlElement
		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}
		@XmlElement
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		@XmlElement
		public Date getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(Date releaseDate) {
			this.releaseDate = releaseDate;
		}
		@XmlElement
		public String getRequirements() {
			return requirements;
		}

		public void setRequirements(String requirements) {
			this.requirements = requirements;
		}
		@XmlElement
		public DevelopmentStage getDevStage() {
			return devStage;
		}

		public void setDevStage(DevelopmentStage devStage) {
			this.devStage = devStage;
		}

		public SoftwareProduct(Integer applicationId, String applicationName,
				String domain, String type, Date releaseDate,
				String requirements, DevelopmentStage devStage) {
			super();
			this.applicationId = applicationId;
			this.applicationName = applicationName;
			this.domain = domain;
			this.type = type;
			this.releaseDate = releaseDate;
			this.requirements = requirements;
			this.devStage = devStage;
		}

		public SoftwareProduct() {
			super();
		}
		@XmlElementWrapper(name="features")@XmlElement(name="feature")
		public List<Features> getFeatures() {
			return features;
		}

		public void setFeatures(List<Features> features) {
			this.features = features;
		}
		
		public void addFeaturesPerProduct(Features features){
			this.features.add(features);
		}

		public void addReleasesPerProduct(ProductRelease productRelease){
			this.productRelease.add(productRelease);
		}
		@XmlElementWrapper(name="releases")@XmlElement(name="release")
		public List<ProductRelease> getProductRelease() {
			return productRelease;
		}

		public void setProductRelease(List<ProductRelease> productRelease) {
			this.productRelease = productRelease;
		}
		
		public static String BASE_URLs = Features.BASE+"softwares/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URLs + this.getApplicationId();
		return new AtomLink(restUrl,"get-softwares");	
		}
		
		public void setLink(AtomLink link){}
}
