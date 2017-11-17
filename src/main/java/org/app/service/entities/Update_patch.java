package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@XmlRootElement(name="patches")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Update_patch implements Serializable {

	private static final long serialVersionUID = 2809532437442549372L;
		@Id
		private Integer patchId;
		private String patchVersion;
		private String patchNotes;
		
		@Temporal(TemporalType.DATE)
		private Date patchDate;
		
		@ManyToOne(cascade = {CascadeType.ALL})
		private ProductRelease productNo;
		@ManyToOne(cascade = {CascadeType.ALL})
		private SoftwareProduct softwareNo;
		
		@OneToMany(cascade=ALL,orphanRemoval = true,fetch = FetchType.EAGER)
		@Fetch(FetchMode.SELECT)
		private List<Features> features=new ArrayList<>();
		
		
		@XmlElement
		public Integer getPatchId() {
			return patchId;
		}
		public void setPatchId(Integer patchId) {
			this.patchId = patchId;
		}
		@XmlElement
		public String getPatchVersion() {
			return patchVersion;
		}
		public void setPatchVersion(String patchVersion) {
			this.patchVersion = patchVersion;
		}
		@XmlElement
		public String getPatchNotes() {
			return patchNotes;
		}
		public void setPatchNotes(String patchNotes) {
			this.patchNotes = patchNotes;
		}
		@XmlElement
		public Date getPatchDate() {
			return patchDate;
		}
		public void setPatchDate(Date patchDate) {
			this.patchDate = patchDate;
		}
		
		public void addFeaturesPerPatch(Features features){
			this.features.add(features);
		}
		
		public Update_patch() {
			super();
		}
		
		public Update_patch(Integer patchId, String patchVersion,
				String patchNotes, Date patchDate, ProductRelease productNo,
				SoftwareProduct softwareNo) {
			super();
			this.patchId = patchId;
			this.patchVersion = patchVersion;
			this.patchNotes = patchNotes;
			this.patchDate = patchDate;
			this.productNo = productNo;
			this.softwareNo = softwareNo;
		}
		
		
		public Update_patch(Integer patchId, String patchVersion,
				String patchNotes, Date patchDate) {
			super();
			this.patchId = patchId;
			this.patchVersion = patchVersion;
			this.patchNotes = patchNotes;
			this.patchDate = patchDate;
		}
		@XmlElement(name="release")
		public ProductRelease getProductNo() {
			return productNo;
		}
		public void setProductNo(ProductRelease productNo) {
			this.productNo = productNo;
		}
		@XmlElement(name="product")
		public SoftwareProduct getSoftwareNo() {
			return softwareNo;
		}
		public void setSoftwareNo(SoftwareProduct softwareNo) {
			this.softwareNo = softwareNo;
		}
		@XmlElementWrapper(name="features")@XmlElement(name="feature")
		public List<Features> getFeatures() {
			return features;
		}
		
		public void setFeatures(List<Features> features) {
			this.features = features;
		}
		
		public static String BASE_URLP = Features.BASE+"patches/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URLP + this.getPatchId();
		return new AtomLink(restUrl,"get-patches");	
		}
		
		public void setLink(AtomLink link){}
		
}
