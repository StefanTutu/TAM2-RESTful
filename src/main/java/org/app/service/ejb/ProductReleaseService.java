package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.ProductRelease;

@Remote
public interface ProductReleaseService {
	
	ProductRelease addProductRelease(ProductRelease productReleaseToAdd);
	
	String removeProductRelease(ProductRelease productReleaseToDelete);
	
	ProductRelease getProductReleaseByProductReleaseID(Integer productId);
	
	Collection<ProductRelease> getProductRelease();
	
	String sayRest();

	ProductRelease addProductRelease1(ProductRelease productReleaseToAdd);

	ProductRelease addProductRelease2(ProductRelease productReleaseToAdd);

	ProductRelease addProductRelease3(ProductRelease productReleaseToAdd);
}
