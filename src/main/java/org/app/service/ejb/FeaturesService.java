package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Features;

@Remote
public interface FeaturesService {
	
	Features addFeatures(Features featuresToAdd);
	
	String removeFeatures(Features featuresToDelete);
	
	Features getFeaturesByFeaturesID(Integer featureId);
	
	Collection<Features> getFeatures();
	
	//Features getFeaturesByName(String name);
	
	String sayRest();

	Features addFeatures1(Features featuresToAdd);

	Features addFeatures2(Features featuresToAdd);

	Features addFeatures11(Features featuresToAdd);
}
