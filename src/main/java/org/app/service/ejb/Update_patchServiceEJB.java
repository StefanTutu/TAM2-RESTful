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
import org.app.service.entities.Update_patch;

@Path("patches")
@Stateless @LocalBean
public class Update_patchServiceEJB implements Update_patchService {
	private static Logger logger = Logger.getLogger(Update_patchServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public Update_patchServiceEJB(){
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
	public Update_patch addUpdate_patch(Update_patch update_patchToAdd){
		em.merge(update_patchToAdd);
		em.flush();
		return update_patchToAdd;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Update_patch addUpdate_patch1(Update_patch update_patchToAdd){
		em.merge(update_patchToAdd);
		em.flush();
		return update_patchToAdd;
	}
	
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Update_patch addUpdate_patch11(Update_patch update_patchToAdd){
		em.persist(update_patchToAdd);
		em.flush();
		return update_patchToAdd;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Update_patch addUpdate_patch111(Update_patch update_patchToAdd){
		em.persist(update_patchToAdd);
		em.flush();
		return update_patchToAdd;
	}
	
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Update_patch getUpdate_patchByUpdate_patchID(@PathParam("id")Integer patchId){
		return em.find(Update_patch.class, patchId);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Update_patch> getUpdate_patch(){
		List<Update_patch> update_patch = em.createQuery("SELECT up FROM Update_patch up", Update_patch.class).getResultList();
		return update_patch;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeUpdate_patch(Update_patch update_patchToDelete){
		update_patchToDelete = em.merge(update_patchToDelete);
		em.remove(update_patchToDelete);
		em.flush();
		return "True";
	}
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeUpdate(@PathParam("id")Integer updateToDelete) throws Exception{
		String msg="";
		try {
			Update_patch upd=em.find(Update_patch.class, updateToDelete);
			em.remove(upd);
			em.flush();
			msg="Update patch removed.";
		}
		catch(Exception e){
			msg="No update patch with the given id has been found. " + "   Error given:   "+e;
		}
		return msg;
	}
	
	//others
	public String sayRest(){
		return "Update_patch Service is On... ";
	}
}
