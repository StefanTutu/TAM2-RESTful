package org.app.service.ejb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.app.service.ejb.FeaturesService;
import org.app.service.ejb.FeaturesServiceEJB;
import org.app.service.ejb.FutureReleasesService;
import org.app.service.ejb.FutureReleasesServiceEJB;
import org.app.service.ejb.ProductReleaseService;
import org.app.service.ejb.ProductReleaseServiceEJB;
import org.app.service.ejb.SoftwareProductService;
import org.app.service.ejb.SoftwareProductServiceEJB;
import org.app.service.ejb.TeamMembersService;
import org.app.service.ejb.TeamMembersServiceEJB;
import org.app.service.ejb.TeamService;
import org.app.service.ejb.TeamServiceEJB;
import org.app.service.ejb.Update_patchService;
import org.app.service.ejb.Update_patchServiceEJB;
import org.app.service.entities.DevelopmentStage;
import org.app.service.entities.Features;
import org.app.service.entities.FeaturesCategory;
import org.app.service.entities.Fields;
import org.app.service.entities.FutureReleases;
import org.app.service.entities.ProductRelease;
import org.app.service.entities.ReleaseType;
import org.app.service.entities.Role;
import org.app.service.entities.SoftwareProduct;
import org.app.service.entities.Team;
import org.app.service.entities.TeamMembers;
import org.app.service.entities.Update_patch;
import org.app.service.rest.test.RESTfullResource;
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
public class TestAllInOneEJBArq {
	private static Logger logger=Logger.getLogger(TestAllInOneEJBArq.class.getName());
	
	@EJB
	private static FeaturesService service;
	@EJB
	private static TeamMembersService service2;
	@EJB
	private static TeamService service3;
	@EJB
	private static SoftwareProductService service4;
	@EJB
	private static ProductReleaseService service5;
	@EJB
	private static FutureReleasesService service6;
	@EJB
	private static Update_patchService service7;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class,"SAM2-test.war")
				.addPackage(Features.class.getPackage())
				.addClass(FeaturesService.class)
				.addClass(FeaturesServiceEJB.class)
				.addClass(TeamMembersService.class)
				.addClass(TeamMembersServiceEJB.class)
				.addClass(TeamService.class)
				.addClass(TeamServiceEJB.class)
				.addClass(SoftwareProductService.class)
				.addClass(SoftwareProductServiceEJB.class)
				.addClass(ProductReleaseService.class)
				.addClass(ProductReleaseServiceEJB.class)
				.addClass(FutureReleasesService.class)
				.addClass(FutureReleasesServiceEJB.class)
				.addClass(Update_patchService.class)
				.addClass(Update_patchServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
				
		
	}
	
	// get message tests for all classes
	
	@Test
	public void test1_1_Feature_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	@Test
	public void test1_2_TeamMembers_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service2.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	

	@Test
	public void test1_3_Team_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service3.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	@Test
	public void test1_4_SoftwareProduct_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service4.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	@Test
	public void test1_5_ProductRelease_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service5.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	@Test
	public void test1_6_FutureRelease_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service6.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	@Test
	public void test1_7_Update_patch_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage...");
		String response = service7.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." +response);
	}
	
	// delete tests for all classes
	
	@Test
	public void test2_7_Feature_DeleteFeature(){
		logger.info("DEBUG: Junit TESTING: deleteFeatures...");
		Collection<Features> features = service.getFeatures();
		for(Features f:features)
			service.removeFeatures(f);
		Collection<Features> featuresAfterDelete = service.getFeatures();
		assertTrue("Fail to read features!", featuresAfterDelete.size()==0);
	}
	
	@Test
	public void test2_3_TeamMembers_DeleteTeamMembers(){
		logger.info("DEBUG: Junit TESTING: deleteTeamMembers...");
		Collection<TeamMembers> teamMembers = service2.getTeamMembers();
		for(TeamMembers tm:teamMembers)
			service2.removeTeamMembers(tm);
		Collection<TeamMembers> teamMembersAfterDelete = service2.getTeamMembers();
		assertTrue("Fail to read team members!", teamMembersAfterDelete.size()==0);
	}
	
	@Test
	public void test2_2_Team_DeleteTeam(){
		logger.info("DEBUG: Junit TESTING: deleteTeam...");
		Collection<Team> team = service3.getTeam();
		for(Team t:team)
			service3.removeTeam(t);
		Collection<Team> teamAfterDelete = service3.getTeam();
		assertTrue("Fail to read team!", teamAfterDelete.size()==0);
	}
	
	@Test
	public void test2_1_SoftwareProduct_Delete(){
		logger.info("DEBUG: Junit TESTING: deleteSoftwareProduct...");
		Collection<SoftwareProduct> softwareProduct = service4.getSoftwareProduct();
		for(SoftwareProduct sp:softwareProduct)
			service4.removeSoftwareProduct(sp);
		Collection<SoftwareProduct> softwareProductAfterDelete = service4.getSoftwareProduct();
		assertTrue("Fail to read SoftwareProduct!", softwareProductAfterDelete.size()==0);
	}
	
	@Test
	public void test2_4_ProductRelease_Delete(){
		logger.info("DEBUG: Junit TESTING: deleteProductRelease...");
		Collection<ProductRelease> productRelease = service5.getProductRelease();
		for(ProductRelease pr:productRelease)
			service5.removeProductRelease(pr);
		Collection<ProductRelease> productReleaseAfterDelete = service5.getProductRelease();
		assertTrue("Fail to read ProductRelease!", productReleaseAfterDelete.size()==0);
	}
	
	@Test
	public void test2_0a_FutureReleases_Delete(){
		logger.info("DEBUG: Junit TESTING: deleteFutureReleases...");
		Collection<FutureReleases> futureReleases = service6.getFutureReleases();
		for(FutureReleases fr:futureReleases)
			service6.removeFutureReleases(fr);
		Collection<FutureReleases> futureReleasesAfterDelete = service6.getFutureReleases();
		assertTrue("Fail to read FutureReleases!", futureReleasesAfterDelete.size()==0);
	}
	
	@Test
	public void test2_0_Update_patch_Delete(){
		logger.info("DEBUG: Junit TESTING: deleteUpdate_patch...");
		Collection<Update_patch> update_patch = service7.getUpdate_patch();
		for(Update_patch up:update_patch)
			service7.removeUpdate_patch(up);
		Collection<Update_patch> update_patchAfterDelete = service7.getUpdate_patch();
		assertTrue("Fail to read Update_patch!", update_patchAfterDelete.size()==0);
	}
	// adding tests for all classes
	
	@Test
	public void test3_1_Feature_AddFeature(){
		logger.info("DEBUG: Junit TESTING: testAddFeatures...");
		
		Integer featuresToAdd=4;
		for(int i=1; i<=featuresToAdd;i++){
			service.addFeatures(new Features(i,"Feature no. "+i,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));
		}
		Collection<Features> features=service.getFeatures();
		assertTrue("Fail to add features!",features.size()==featuresToAdd);
	}
	
	@Test
	public void test3_2_TeamMembers_AddTeamMembers(){
		logger.info("DEBUG: Junit TESTING: testAddTeamMembers...");
		
		Integer teamMembersToAdd=9;
		for(int i=1; i<=teamMembersToAdd;i++){
			int l=1000+i;
			service2.addTeamMembers(new TeamMembers(l,"Team member no. "+l, Role.getRandomRole()));
		}
		Collection<TeamMembers> teamMembers=service2.getTeamMembers();
		assertTrue("Fail to add team members!",teamMembers.size()==teamMembersToAdd);
	}
	
	@Test
	public void test3_3_Team_AddTeam(){
		logger.info("DEBUG: Junit TESTING: testAddTeam...");
		Integer teamToAdd=2;
		Integer membersPerTeam=4;
		Collection<TeamMembers> teamMembers=service2.getTeamMembers();
		List<TeamMembers> bla=new ArrayList<>();
		bla.addAll(teamMembers);
		//int tracking=0; / asta merge cu varianta 1
		for(int i=1; i<=teamToAdd;i++){
			Team a=new Team();
			a.setTeamId(i);
			a.setTeamName("Team no. "+i);
			a.setCompetence("Something not so important! But it must exists for testing!");
			a.setDomain(Fields.getRandomFields());
			
			for(int j=1; j<=membersPerTeam;j++){	
				/* //varianta nr 1 care merge...dar nu-mi place pentru ca trebuie neaparat sa creez eu membrii echipei...nu sa-i selectez direct din cei existenti
				if(i==1){
					int x=100+j;
					a.addTeamMember(new TeamMembers(x,"Team member no. "+x, Role.getRandomRole()));}
				else if(i==2){
					int x=100+j+membersPerTeam;
					a.addTeamMember(new TeamMembers(x,"Team member no. "+x, Role.getRandomRole()));}
				else{
					int x=100+j+(tracking*membersPerTeam);
					a.addTeamMember(new TeamMembers(x,"Team member no. "+x, Role.getRandomRole()));} */	
				
				//varianta 2...in cazul in care vrei a adaugi la o echipa nou creata membri deja existenti
				Collections.shuffle(bla);
				TeamMembers x=bla.get(0);
				bla.remove(x);
				a.addTeamMember(service2.getTeamMembersByTeamMembersID(x.getMemberId()));
				service2.removeTeamMembers(x);
			}
			//tracking++; //asta merge cu varianta 1
			service3.addTeam(a);
		}
		Collection<Team> team=service3.getTeam();
		assertTrue("Fail to add team members!",team.size()==teamToAdd);
	}
	
	@Test
	public void test3_4_AddSoftwareProduct(){
		logger.info("DEBUG: Junit TESTING: testAddSoftwareProduct...");
		Integer featuresPerProduct=4;
		Integer releasesPerProduct=2;
		Integer softwareProductToAdd=2;
		int tracking=0; //asta merge cu varianta 1
		for(int i=1; i<=softwareProductToAdd;i++){ // a intrat cu valoarea 1
			
			SoftwareProduct sp=new SoftwareProduct();
			sp.setApplicationId(i);
			sp.setApplicationName("SoftwareProduct no. "+i);
			sp.setDomain("Just for testing!");
			sp.setType("To be inserted!");
			sp.setReleaseDate(new Date());
			sp.setRequirements("Some random requirements for proper working of the system. To be inserted!");
			sp.setDevStage(DevelopmentStage.getRandomDevelopmentStage());
			
			for(int j=1; j<=featuresPerProduct;j++){	
				if(i==1){
					int x=100+j;
					sp.addFeaturesPerProduct(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else if(i==2){
					int x=100+j+featuresPerProduct;
					sp.addFeaturesPerProduct(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else{
					int x=100+j+(tracking*featuresPerProduct);
					sp.addFeaturesPerProduct(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}

			}
			for(int j=1; j<=releasesPerProduct;j++){	
				if(i==1){
					int x=1000+j;
					ProductRelease pr=new ProductRelease();
					pr.setProductId(x);
					pr.setReleaseDate(new Date());
					pr.setVersion("Version number "+x);
					pr.setProductState("In development!");
					sp.addReleasesPerProduct(pr);
				}
					
				else if(i==2){
					int x=10000+j;
					ProductRelease pr=new ProductRelease();
					pr.setProductId(x);
					pr.setReleaseDate(new Date());
					pr.setVersion("Version number "+x);
					pr.setProductState("In development!");
					sp.addReleasesPerProduct(pr);}
				else{
					int x=1000000+j;
					ProductRelease pr=new ProductRelease();
					pr.setProductId(x);
					pr.setReleaseDate(new Date());
					pr.setVersion("Version number "+x);
					pr.setProductState("In development!");
					sp.addReleasesPerProduct(pr);}
			}
			
			tracking++; //asta merge cu varianta 1
			service4.addSoftwareProduct(sp);
		}
		Collection<SoftwareProduct> softwareProduct=service4.getSoftwareProduct();
		assertTrue("Fail to add software products!",softwareProduct.size()==softwareProductToAdd);
	}
	
	@Test
	public void test3_5_AddProductRelease(){
		logger.info("DEBUG: Junit TESTING: testAddProductRelease...");
		Integer featuresPerProductRelease=5;
		Integer productReleaseToAdd=3;
		int tracking=0; //asta merge cu varianta 1
		for(int i=1; i<=productReleaseToAdd;i++){
			
			ProductRelease pr=new ProductRelease();
			pr.setProductId(i);
			pr.setReleaseDate(new Date());
			pr.setVersion("Version number "+i);
			pr.setProductState("In development!");
			
			for(int j=1; j<=featuresPerProductRelease;j++){	
				if(i==1){
					int x=100000+j;
					pr.addFeaturesPerProductRelease(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else if(i==2){
					int x=100000+j+featuresPerProductRelease;
					pr.addFeaturesPerProductRelease(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else{
					int x=100000+j+(tracking*featuresPerProductRelease);
					pr.addFeaturesPerProductRelease(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}

			}
			tracking++; //asta merge cu varianta 1
			service5.addProductRelease(pr);
		}
		Collection<ProductRelease> productRelease=service5.getProductRelease();
		assertTrue("Fail to add product releases!",productRelease.size()>=productReleaseToAdd);
	}
	
	@Test
	public void test3_6_AddFutureRelease(){
		logger.info("DEBUG: Junit TESTING: testAddFutureRelease...");
		Integer featuresPerFutureReleases=4;
		Integer futureReleasesToAdd=2;
		int tracking=0; //asta merge cu varianta 1
		for(int i=1; i<=futureReleasesToAdd;i++){
			
			FutureReleases fr=new FutureReleases();
			fr.setReleaseId(i);
			fr.setEstimatedDate(new Date(new Date().getTime()+(100*i)+1));
			fr.setType(ReleaseType.getRandomReleaseTypes());
			fr.setSoftware(new SoftwareProduct(i+100,"SoftwareProduct no. "+(i+100),"Just for testing!","To be inserted!",new Date(),"Some random requirements for proper working of the system. To be inserted!",DevelopmentStage.getRandomDevelopmentStage()));
			
			for(int j=1; j<=featuresPerFutureReleases;j++){	
				if(i==1){
					int x=12000+j;
					fr.addFeaturesPerFutureRelease (new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else if(i==2){
					int x=12000+j+featuresPerFutureReleases;
					fr.addFeaturesPerFutureRelease(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else{
					int x=12000+j+(tracking*featuresPerFutureReleases);
					fr.addFeaturesPerFutureRelease(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}

			}
			tracking++; //asta merge cu varianta 1
			service6.addFutureReleases(fr);
		}
		Collection<ProductRelease> productRelease=service5.getProductRelease();
		assertTrue("Fail to add product releases!",productRelease.size()>=futureReleasesToAdd);
	}
	
	@Test
	public void test3_7_AddUpdate_Patch(){
		logger.info("DEBUG: Junit TESTING: testAddUpdate_patch...");
		Integer featuresPerUpdate_patch=4;
		Integer update_patchToAdd=2;
		int tracking=0; //asta merge cu varianta 1
		for(int i=1; i<=update_patchToAdd;i++){
			
			Update_patch up=new Update_patch();
			up.setPatchId(i);
			up.setPatchVersion("v"+i);
			up.setPatchNotes("Bla bla bla...Super important note.");
			up.setPatchDate(new Date(new Date().getTime()+(20*i)+1));
			up.setProductNo(new ProductRelease(i+1500,new Date(),"Version number "+i,"In development!", null));
			up.setSoftwareNo(new SoftwareProduct(i+1500,"SoftwareProduct no. "+(i+1500),"Just for testing!","To be inserted!",new Date(),"Some random requirements for proper working of the system. To be inserted!",DevelopmentStage.getRandomDevelopmentStage()));
			
			for(int j=1; j<=featuresPerUpdate_patch;j++){	
				if(i==1){
					int x=15000+j;
					up.addFeaturesPerPatch (new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else if(i==2){
					int x=15000+j+featuresPerUpdate_patch;
					up.addFeaturesPerPatch(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}
				else{
					int x=15000+j+(tracking*featuresPerUpdate_patch);
					up.addFeaturesPerPatch(new Features(x,"Feature no. "+x,"Some random text for this feature.",FeaturesCategory.getRandomCategory()));}

			}
			tracking++; //asta merge cu varianta 1
			service7.addUpdate_patch(up);
		}
		Collection<Update_patch> update_patch=service7.getUpdate_patch();
		assertTrue("Fail to add Update patches!",update_patch.size()>=update_patchToAdd);
	}
	
	// get something tests for all classes
	
	@Test
	public void test4_1__getFeatures(){
		logger.info("DEBUG: Junit TESTING: testGetFeatures...");
		Collection<Features> features=service.getFeatures();
		assertTrue("Fail to read features!",features.size()>0);
	}
	
	@Test
	public void test4_2__getTeamMembers(){
		logger.info("DEBUG: Junit TESTING: testGetTeamMembers...");
		Collection<TeamMembers> teamMembers=service2.getTeamMembers();
		assertTrue("Fail to read team members!",teamMembers.size()>0);
	}
	
	@Test
	public void test4_3__getTeam(){
		logger.info("DEBUG: Junit TESTING: testGetTeam...");
		Collection<Team> team=service3.getTeam();
		assertTrue("Fail to read teams!",team.size()>0);
	}
	
	@Test
	public void test4_4__getSoftwareProduct(){
		logger.info("DEBUG: Junit TESTING: testGetSoftwareProduct...");
		Collection<SoftwareProduct> softwareProduct=service4.getSoftwareProduct();
		assertTrue("Fail to read software products!",softwareProduct.size()>0);
	}
	
	@Test
	public void test4_5__getProductRelease(){
		logger.info("DEBUG: Junit TESTING: testGetProductRelease...");
		Collection<ProductRelease> productRelease=service5.getProductRelease();
		assertTrue("Fail to read product releases!",productRelease.size()>0);
	}
	
	@Test
	public void test4_6__getFutureReleases(){
		logger.info("DEBUG: Junit TESTING: testGetProductRelease...");
		Collection<FutureReleases> futureReleases=service6.getFutureReleases();
		assertTrue("Fail to read future releases!",futureReleases.size()>0);
	}
	
	@Test
	public void test4_7__getUpdate_patch(){
		logger.info("DEBUG: Junit TESTING: testGetUpdate_patch...");
		Collection<Update_patch> update_patch=service7.getUpdate_patch();
		assertTrue("Fail to read Update patches!",update_patch.size()>0);
	}
	

	
}
