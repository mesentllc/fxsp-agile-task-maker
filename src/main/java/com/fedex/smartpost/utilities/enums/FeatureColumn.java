package com.fedex.smartpost.utilities.enums;

public enum FeatureColumn {
	TITLE("Title"),
	ID("ID"),
	TYPE("Type"),
	TEAM("Team"),
	POINT_ESTIMATE("Estimate Pts."),
	PROJECT("Project"),
	SPRINT("Sprint"),
	STATUS("Status"),
	ITG("ITG # (Epic)"),
	DESCRIPTION("Description"),
	SUCCESS_CRITERIA("Success Criteria"),
	ACCEPTANCE("Acceptance Criteria"),
	NOTES("Notes"),
	PLANNED_END("Planned End"),
	HOURS("Allocated To Do Hrs.");

	private String columnHeading;

	FeatureColumn(String columnHeading) {
		this.columnHeading = columnHeading;
	}
}
