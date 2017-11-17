package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.TeamMembers;

@Remote
public interface TeamMembersService {
	
	TeamMembers addTeamMembers(TeamMembers teamMembersToAdd);
	
	String removeTeamMembers(TeamMembers teamMembersToDelete);
	
	TeamMembers getTeamMembersByTeamMembersID(Integer memberId);
	
	Collection<TeamMembers> getTeamMembers();
	
	//TeamMembers getTeamMembersByName(String memberName);
	
	String sayRest();

	TeamMembers addTeamMembers1(TeamMembers teamMembersToAdd);

	TeamMembers addTeamMembers2(TeamMembers teamMembersToAdd);

	TeamMembers addTeamMembers11(TeamMembers teamMembersToAdd);
}
