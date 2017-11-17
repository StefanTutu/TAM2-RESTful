package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.SoftwareProduct;

@Remote
public interface SoftwareProductService {
	
	SoftwareProduct addSoftwareProduct(SoftwareProduct softwareProductToAdd);
	
	String removeSoftwareProduct(SoftwareProduct softwareProductToDelete);
	
	SoftwareProduct getSoftwareProductBySoftwareProductID(Integer applicationId);
	
	Collection<SoftwareProduct> getSoftwareProduct();
	
	//SoftwareProduct getSoftwareProductByName(String applicationName);
	
	String sayRest();

	SoftwareProduct addSoftwareProduct1(SoftwareProduct softwareProductToAdd);

	SoftwareProduct addSoftwareProduct2(SoftwareProduct softwareProductToAdd);

	SoftwareProduct addSoftwareProduct3(SoftwareProduct softwareProductToAdd);
}
