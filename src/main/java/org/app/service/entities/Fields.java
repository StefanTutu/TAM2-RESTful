package org.app.service.entities;

public enum Fields {

		Back_End,
		Front_end,
		Database_and_server_administration;
		
		public static Fields getRandomFields() {
	        return values()[(int) (Math.random() * values().length)];
	    }
		
}
