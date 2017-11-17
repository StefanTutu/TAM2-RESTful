package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
@XmlRootElement(name="releases")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class ProductRelease implements Serializable {
	
	private static final long serialVersionUID = -8452965345451440172L;

		@Id
		private Integer productId;
		
		@Temporal(TemporalType.DATE)
		private Date releaseDate;
		
		private String version;
		private String productState;
			
		@OneToMany(cascade=ALL,orphanRemoval = true,fetch = FetchType.EAGER)
		@Fetch(FetchMode.SELECT)
		private List<Features> features=new ArrayList<>();

		@XmlElement
		public Integer getProductId() {
			return productId;
		}

		public void setProductId(Integer productId) {
			this.productId = productId;
		}
		@XmlElement
		public Date getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(Date releaseDate) {
			this.releaseDate = releaseDate;
		}
		@XmlElement
		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
		@XmlElement
		public String getProductState() {
			return productState;
		}

		public void setProductState(String productState) {
			this.productState = productState;
		}


		public ProductRelease(Integer productId, Date releaseDate,
				String version, String productState, SoftwareProduct software) {
			super();
			this.productId = productId;
			this.releaseDate = releaseDate;
			this.version = version;
			this.productState = productState;
		}

		public ProductRelease() {
			super();
		}
		
		public void addFeaturesPerProductRelease(Features features){
			this.features.add(features);
		}
		@XmlElementWrapper(name="features")@XmlElement(name="feature")
		public List<Features> getFeatures() {
			return features;
		}

		public void setFeatures(List<Features> features) {
			this.features = features;
		}
		public static String BASE_URLpr= Features.BASE+"releases/";
		@XmlElement(name="link")
		public AtomLink getLink() throws  Exception{
			String restUrl=BASE_URLpr + this.getProductId();
		return new AtomLink(restUrl,"get-releases");	
		}
		
		public void setLink(AtomLink link){}
		
}
