package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
import org.app.service.entities.Team;
@Path("teams")
@Stateless @LocalBean
public class TeamServiceEJB implements TeamService {
	private static Logger logger = Logger.getLogger(TeamServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public TeamServiceEJB(){
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
	public Team addTeam(Team teamToAdd){
		em.merge(teamToAdd);
		em.flush();
		return teamToAdd;
		
	}
	
	//Create or Update
	@PUT 
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Team addTeam1(Team teamToAdd){
		em.merge(teamToAdd);
		em.flush();
		return teamToAdd;
			
		}
		
	@POST @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Team addTeam3(Team teamToAdd){
		em.persist(teamToAdd);
		em.flush();
		return teamToAdd;
		
	}
	
	@POST 
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Team addTeam2(Team teamToAdd){
		em.persist(teamToAdd);
		em.flush();
		return teamToAdd;
			
		}
	
	//Read
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Team getTeamByTeamID(@PathParam("id")Integer teamId){
		return em.find(Team.class, teamId);
	}
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Team> getTeam(){
		List<Team> team = em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
		return team;
	}
	
	//Remove
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeTeam(Team teamToDelete){
		teamToDelete = em.merge(teamToDelete);
		em.remove(teamToDelete);
		em.flush();
		return "True";
	}
	
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeTeam(@PathParam("id")Integer teamToDelete) throws Exception{
		String msg="";
		try {
		Team tm=em.find(Team.class, teamToDelete);
			em.remove(tm);
			em.flush();
			msg="Team removed.";
		}
		catch(Exception e){
			msg="No Team with the given id has been found. " + "   Error given:   "+e;
		}
		return msg;
	}
	
	/*//Custom READ :custom query
	@GET @Path("/{teamName}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Team getTeamByName(@PathParam("teamName")String teamName){
		return em.createQuery("SELECT t FROM Team t WHERE t.teamName= :teamName",Team.class).setParameter("teamName",teamName).getSingleResult();
	}
	*/
	
	//others
	public String sayRest(){
		return "Team Service is On... ";
	}
}
