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

import org.app.service.entities.ProductRelease;
@Path("releases")
@Stateless @LocalBean
public class ProductReleaseServiceEJB implements ProductReleaseService {
	private static Logger logger = Logger.getLogger(ProductReleaseServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public ProductReleaseServiceEJB(){
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
	public ProductRelease addProductRelease(ProductRelease productReleaseToAdd){
		em.merge(productReleaseToAdd);
		em.flush();
		return productReleaseToAdd;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public ProductRelease addProductRelease1(ProductRelease productReleaseToAdd){
		em.merge(productReleaseToAdd);
		em.flush();
		return productReleaseToAdd;
	}
	
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public ProductRelease addProductRelease2(ProductRelease productReleaseToAdd){
		em.persist(productReleaseToAdd);
		em.flush();
		return productReleaseToAdd;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public ProductRelease addProductRelease3(ProductRelease productReleaseToAdd){
		em.persist(productReleaseToAdd);
		em.flush();
		return productReleaseToAdd;
	}
	
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public ProductRelease getProductReleaseByProductReleaseID(@PathParam("id")Integer productId){
		return em.find(ProductRelease.class, productId);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<ProductRelease> getProductRelease(){
		List<ProductRelease> productRelease = em.createQuery("SELECT pr FROM ProductRelease pr", ProductRelease.class).getResultList();
		return productRelease;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeProductRelease(ProductRelease productReleaseToDelete){
		productReleaseToDelete = em.merge(productReleaseToDelete);
		em.remove(productReleaseToDelete);
		em.flush();
	return "";
	}
	
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeRelease(@PathParam("id")Integer productReleaseToDelete) throws Exception{
		ProductRelease prR=em.find(ProductRelease.class, productReleaseToDelete);
		String msg="";
		try{
			em.remove(prR);
			em.flush();
			msg="True";
		}
		catch(Exception e){
			msg="False. Cannot delete this entry because it is a child for another class.";
		}	
		return msg;
	}
	
	
	//others
	public String sayRest(){
		return "ProductRelease Service is On... ";
	}
}
