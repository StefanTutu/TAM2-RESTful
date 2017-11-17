package org.app.service.entities;

public enum DevelopmentStage {

		Alpha,
		Beta,
		Final;
		
		public static DevelopmentStage getRandomDevelopmentStage() {
	        return values()[(int) (Math.random() * values().length)];
	    }
}
