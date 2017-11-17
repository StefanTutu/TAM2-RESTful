package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Features;

@Path("features")
@Stateless @LocalBean
public class FeaturesServiceEJB implements FeaturesService {
	private static Logger logger = Logger.getLogger(FeaturesServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public FeaturesServiceEJB(){
	}
	
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " +this.em);
	}
	
	//Create or Update
	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features addFeatures(Features featuresToAdd){
		em.merge(featuresToAdd);
		em.flush();
		return featuresToAdd;
	}

	@PUT 
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features addFeatures2(Features featuresToAdd){
		em.merge(featuresToAdd);
		em.flush();
		return featuresToAdd;
	}
	
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features addFeatures1(Features featuresToAdd){
		em.persist(featuresToAdd);
		em.flush();
		return featuresToAdd;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features addFeatures11(Features featuresToAdd){
		em.persist(featuresToAdd);
		em.flush();
		return featuresToAdd;
	}
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features getFeaturesByFeaturesID(@PathParam("id")Integer featureId){
		return em.find(Features.class, featureId);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Features> getFeatures(){
		List<Features> features = em.createQuery("SELECT f FROM Features f", Features.class).getResultList();
		return features;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeFeatures(Features featuresToDelete){
		featuresToDelete = em.merge(featuresToDelete);
		em.remove(featuresToDelete);
		em.flush();
		return "True";
	}
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeFeature(@PathParam("id")Integer featuresToDelete) throws Exception{
		String msg="";
		try {
		Features ftR=em.find(Features.class, featuresToDelete);
			em.remove(ftR);
			em.flush();
			msg="Feature removed.";
		}
		catch(Exception e){
			msg="No feature with the given id has been found. " + "   Error given:   "+e;
		}
		return msg;
	}
	
	
	
	/*//Custom READ :custom query
    @GET @Path("/{name}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features getFeaturesByName(@PathParam("name")String name){
		return em.createQuery("SELECT f FROM Features f WHERE f.name= :name",Features.class).setParameter("name",name).getSingleResult();
	}
	*/

	//others
	public String sayRest(){
		return "Features Service is On... ";
	}
}
