package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Team;

@Remote
public interface TeamService {
	
	Team addTeam(Team teamToAdd);
	
	String removeTeam(Team teamToDelete);
	
	Team getTeamByTeamID(Integer teamId);
	
	Collection<Team> getTeam();
	
	//Team getTeamByName(String teamName);
	
	String sayRest();

	Team addTeam1(Team teamToAdd);


	Team addTeam3(Team teamToAdd);

	Team addTeam2(Team teamToAdd);
}
