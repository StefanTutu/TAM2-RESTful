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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.entities.Features;
import org.app.service.entities.TeamMembers;
@Path("teammembers")
@Stateless @LocalBean
public class TeamMembersServiceEJB implements TeamMembersService {
	private static Logger logger = Logger.getLogger(TeamMembersServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public TeamMembersServiceEJB(){
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
	public TeamMembers addTeamMembers(TeamMembers teamMembersToAdd){
		em.merge(teamMembersToAdd);
		em.flush();
		return teamMembersToAdd;
	}
	
	@PUT 
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public TeamMembers addTeamMembers1(TeamMembers teamMembersToAdd){
		em.merge(teamMembersToAdd);
		em.flush();
		return teamMembersToAdd;
	}
	
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public TeamMembers addTeamMembers11(TeamMembers teamMembersToAdd){
		em.persist(teamMembersToAdd);
		em.flush();
		return teamMembersToAdd;
	}
	
	@POST 
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public TeamMembers addTeamMembers2(TeamMembers teamMembersToAdd){
		em.persist(teamMembersToAdd);
		em.flush();
		return teamMembersToAdd;
	}
	
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public TeamMembers getTeamMembersByTeamMembersID(@PathParam("id")Integer memberId){
		return em.find(TeamMembers.class, memberId);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<TeamMembers> getTeamMembers(){
		List<TeamMembers> teamMembers = em.createQuery("SELECT tm FROM TeamMembers tm", TeamMembers.class).getResultList();
		return teamMembers;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeTeamMembers(TeamMembers teamMembersToDelete){
		teamMembersToDelete = em.merge(teamMembersToDelete);
		em.remove(teamMembersToDelete);
		em.flush();
		return "True";
	}
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeMembers(@PathParam("id")Integer membersToDelete) throws Exception{
		String msg="";
		try {
			TeamMembers ftR=em.find(TeamMembers.class, membersToDelete);
			em.remove(ftR);
			em.flush();
			msg="Member removed.";
		}
		catch(Exception e){
			msg="No member with the given id has been found. " + "   Error given:   "+e;
		}
		return msg;
	}
	
	/*//Custom READ :custom query
	@GET @Path("/{memberName}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public TeamMembers getTeamMembersByName(@PathParam("memberName")String memberName){
		return em.createQuery("SELECT tm FROM TeamMembers tm WHERE tm.memberName= :memberName",TeamMembers.class).setParameter("memberName",memberName).getSingleResult();
	}
	*/
	//others
	public String sayRest(){
		return "TeamMembers Service is On... ";
	}
}
