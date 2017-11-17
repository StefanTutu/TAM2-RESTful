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
import org.app.service.entities.FutureReleases;
@Path("futurereleases")
@Stateless @LocalBean
public class FutureReleasesServiceEJB implements FutureReleasesService {
	private static Logger logger = Logger.getLogger(FutureReleasesServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public FutureReleasesServiceEJB(){
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
	public FutureReleases addFutureReleases(FutureReleases futureReleasesToAdd){
		em.merge(futureReleasesToAdd);
		em.flush();
		return futureReleasesToAdd;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public FutureReleases addFutureReleases1(FutureReleases futureReleasesToAdd){
		em.merge(futureReleasesToAdd);
		em.flush();
		return futureReleasesToAdd;
	}
	
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public FutureReleases addFutureReleases2(FutureReleases futureReleasesToAdd){
		em.persist(futureReleasesToAdd);
		em.flush();
		return futureReleasesToAdd;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public FutureReleases addFutureReleases3(FutureReleases futureReleasesToAdd){
		em.persist(futureReleasesToAdd);
		em.flush();
		return futureReleasesToAdd;
	}
	
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public FutureReleases getFutureReleasesByFutureReleasesID(@PathParam("id")Integer releaseId){
		return em.find(FutureReleases.class, releaseId);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<FutureReleases> getFutureReleases(){
		List<FutureReleases> futureReleases = em.createQuery("SELECT fr FROM FutureReleases fr", FutureReleases.class).getResultList();
		return futureReleases;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeFutureReleases(FutureReleases futureReleasesToDelete){
		futureReleasesToDelete = em.merge(futureReleasesToDelete);
		em.remove(futureReleasesToDelete);
		em.flush();
		return "True";
	}
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeFutureReleases(@PathParam("id")Integer futureReleasesToDelete) throws Exception{
		String msg="";
		try {
			FutureReleases ftR=em.find(FutureReleases.class, futureReleasesToDelete);
			em.remove(ftR);
			em.flush();
			msg="Future release removed.";
		}
		catch(Exception e){
			msg="No future release with the given id has been found. " + "   Error given:   "+e;
		}
		return msg;
	}
	
	//others
	public String sayRest(){
		return "FutureReleases Service is On... ";
	}
}
