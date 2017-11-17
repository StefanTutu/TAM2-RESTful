package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.FutureReleases;

@Remote
public interface FutureReleasesService {
	
	FutureReleases addFutureReleases(FutureReleases futureReleasesToAdd);
	
	String removeFutureReleases(FutureReleases futureReleasesToDelete);
	
	FutureReleases getFutureReleasesByFutureReleasesID(Integer releaseId);
	
	Collection<FutureReleases> getFutureReleases();
	
	String sayRest();

	FutureReleases addFutureReleases1(FutureReleases futureReleasesToAdd);

	FutureReleases addFutureReleases2(FutureReleases futureReleasesToAdd);

	FutureReleases addFutureReleases3(FutureReleases futureReleasesToAdd);
}
