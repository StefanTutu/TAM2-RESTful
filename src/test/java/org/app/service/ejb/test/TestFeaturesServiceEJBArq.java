package org.app.service.ejb.test;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.FeaturesService;
import org.app.service.ejb.FeaturesServiceEJB;
import org.app.service.entities.Features;
import org.app.service.entities.FeaturesCategory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFeaturesServiceEJBArq {
	private static Logger logger=Logger.getLogger(TestFeaturesServiceEJBArq.class.getName());
	
	@EJB
	private static FeaturesService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class,"sam2-test.war")
				.addPackage(Features.class.getPackage())
				.addClass(FeaturesService.class)
				.addClass(FeaturesServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
				
		
	}
	
	@Test
	public void test1_Feature_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	
	
}
