package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@XmlRootElement(name="futurereleases")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class FutureReleases implements Serializable {

	private static final long serialVersionUID = -1014949984388432909L;

		@Id
		private Integer releaseId;
		
		@Temporal(TemporalType.DATE)
		private Date estimatedDate;
		
		@Enumerated(EnumType.STRING)
		private ReleaseType type;
		
		@ManyToOne(cascade = {CascadeType.ALL})
		private SoftwareProduct software;
		
		@OneToMany(cascade=ALL,orphanRemoval = true,fetch = FetchType.EAGER)
		@Fetch(FetchMode.SELECT)
		private List<Features> features=new ArrayList<>();
		
		
		@XmlElement
		public Integer getReleaseId() {
			return releaseId;
		}
		public void setReleaseId(Integer releaseId) {
			this.releaseId = releaseId;
		}
		@XmlElement
		public Date getEstimatedDate() {
			return estimatedDate;
		}
		public void setEstimatedDate(Date estimatedDate) {
			this.estimatedDate = estimatedDate;
		}
		@XmlElement
		public ReleaseType getType() {
			return type;
		}
		public void setType(ReleaseType type) {
			this.type = type;
		}
		
		public void addFeaturesPerFutureRelease(Features features){
			this.features.add(features);
		}
		public FutureReleases() {
			super();
		}
		public FutureReleases(Integer releaseId, Date estimatedDate,
				ReleaseType type, SoftwareProduct software) {
			super();
			this.releaseId = releaseId;
			this.estimatedDate = estimatedDate;
			this.type = type;
			this.software = software;
		}
		@XmlElement
		public SoftwareProduct getSoftware() {
			return software;
		}
		public void setSoftware(SoftwareProduct software) {
			this.software = software;
		}
		@XmlElementWrapper(name="features")@XmlElement(name="feature")
		public List<Features> getFeatures() {
			return features;
		}
		public void setFeatures(List<Features> features) {
			this.features = features;
		}
		
		public static String BASE_URLfr = Features.BASE+"futurereleases/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URLfr + this.getReleaseId();
		return new AtomLink(restUrl,"get-futurereleases");	
		}
		
		public void setLink(AtomLink link){}
}
