package org.app.service.entities;

public enum Role {
		Project_manager,
		Developer,
		Team_leader,
		Tester,
		Analyst;
		
		public static Role getRandomRole() {
	        return values()[(int) (Math.random() * values().length)];
	    }
}
