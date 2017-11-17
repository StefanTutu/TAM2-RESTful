package org.app.service.entities;

public enum ReleaseType {
	
		Update_patch,
		New_product;
		
		public static ReleaseType getRandomReleaseTypes() {
	        return values()[(int) (Math.random() * values().length)];
	    }
}
