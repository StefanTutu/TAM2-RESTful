package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import org.app.service.entities.Features;
import org.app.service.entities.Team;
import org.app.service.entities.TeamMembers;
import org.app.service.entities.Update_patch;
import org.jboss.logging.Logger;
import org.junit.Test;

public class OptionalTests {
		private static Logger logger=Logger.getLogger(OptionalTests.class.getName());
	
	/*@Test
	public void test_no1_getFeatures() throws Exception{
		RESTfullResource<Features> features=new RESTfullResource<Features>(
				"http://localhost:8080/SAM2/ress/features/1",Features.class,"application/xml");
		Features feats=features.get();
		assertNotNull("Reource[/features/1] not returned",feats);
		logger.info("-----The feature selected is: "+feats);
		
	} */
	
	

	/*@Test
	public void test_no2_deleteFeature() throws Exception{
		RESTfullResource<Features> features=new RESTfullResource<Features>(
				"http://localhost:8080/SAM2/ress/features/2",Features.class,"application/xml");
		
		Features feats=features.get();
		assertNotNull("Reource[/features/1] not returned",feats);
		logger.info("-----The feature deleted is: "+feats);
		features.delete(null);
	} */
	
	@Test
	public void test_no3_put_update_Feature() throws Exception{
		RESTfullResource<Team> team=new RESTfullResource<Team>(
				"http://localhost:8080/SAM2/ress/teams/4",Team.class,"application/xml");
		//Team teams= team.get();
		Team teams= new Team();
		teams.setTeamId(4);
		teams.setTeamName("Team no. 4 First try");
		Team result=team.post(teams); // a doua oara da eroare..pentru ca e persist..gaseste cheie duplicata
		
		assertNotNull("Reource[/teams/1] not returned",teams);
		logger.info("-----The team updated is: "+teams);
		
	} 
	
	/*@Test
	public void test_no4_post_Add_Feature() throws Exception{
		RESTfullResource<Features> features=new RESTfullResource<Features>(
				"http://localhost:8080/SAM2/ress/features/543",Features.class,"application/xml");
		
		Features feats= new Features();
		feats.setFeatureId(543);
		feats.setName("Feature no. 543 posted");
		features.post(feats);
		
		logger.info("-----The feature posted is: "+feats);
		
	} */
}
