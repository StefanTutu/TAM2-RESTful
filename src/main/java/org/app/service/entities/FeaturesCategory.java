package org.app.service.entities;

public enum FeaturesCategory {

	Front_end,
	Back_end,
	Visual_improvements,
	Run_optimization,
	Network_issues,
	Other_issues;
	
	public static FeaturesCategory getRandomCategory() {
        return values()[(int) (Math.random() * values().length)];
    }
}
