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
import org.app.service.entities.SoftwareProduct;
@Path("softwares")
@Stateless @LocalBean
public class SoftwareProductServiceEJB implements SoftwareProductService {
	private static Logger logger = Logger.getLogger(SoftwareProductServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public SoftwareProductServiceEJB(){
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
	public SoftwareProduct addSoftwareProduct(SoftwareProduct softwareProductToAdd){
		em.merge(softwareProductToAdd);
		em.flush();
		return softwareProductToAdd;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public SoftwareProduct addSoftwareProduct1(SoftwareProduct softwareProductToAdd){
		em.merge(softwareProductToAdd);
		em.flush();
		return softwareProductToAdd;
	}
	
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public SoftwareProduct addSoftwareProduct2(SoftwareProduct softwareProductToAdd){
		em.persist(softwareProductToAdd);
		em.flush();
		return softwareProductToAdd;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public SoftwareProduct addSoftwareProduct3(SoftwareProduct softwareProductToAdd){
		em.persist(softwareProductToAdd);
		em.flush();
		return softwareProductToAdd;
	}
	
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public SoftwareProduct getSoftwareProductBySoftwareProductID(@PathParam("id")Integer applicationId){
		return em.find(SoftwareProduct.class, applicationId);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<SoftwareProduct> getSoftwareProduct(){
		List<SoftwareProduct> softwareProduct = em.createQuery("SELECT sp FROM SoftwareProduct sp", SoftwareProduct.class).getResultList();
		return softwareProduct;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeSoftwareProduct(SoftwareProduct softwareProductToDelete){
		softwareProductToDelete = em.merge(softwareProductToDelete);
		em.remove(softwareProductToDelete);
		em.flush();
		return "True";
	}
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeSoftware(@PathParam("id")Integer softwareToDelete) throws Exception{
		String msg="";
		try {
		SoftwareProduct sftR=em.find(SoftwareProduct.class, softwareToDelete);
			em.remove(sftR);
			em.flush();
			msg="Product removed.";
		}
		catch(Exception e){
			msg="No product with the given id has been found. " + "   Error given:   "+e;
		}
		return msg;
	}
	
	
	/*//Custom READ :custom query
    @GET @Path("/{applicationName}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public SoftwareProduct getSoftwareProductByName(@PathParam("applicationName")String applicationName){
		return em.createQuery("SELECT sp FROM SoftwareProduct sp WHERE sp.applicationName= :applicationName",SoftwareProduct.class).setParameter("applicationName",applicationName).getSingleResult();
	}
	*/
	
	//others
	public String sayRest(){
		return "SoftwareProduct Service is On... ";
	}
}
