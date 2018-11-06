package com.fedex.smartpost.utilities.enums;

public enum UsColumn {
	RANKING("Priority Ranking"),
	TITLE("Title"),
	ID("ID"),
	POINT_ESTIMATE("Estimate Pts."),
	DESCRIPTION("Description"),
	ACCEPTANCE("Acceptance Criteria"),
	PORTFOLIO("Portfolio Item"),
	ITG("ITG #"),
	MTP("MTP / Release"),
	REQUESTED_BY("Requested By");

	private String columnHeading;

	UsColumn(String columnHeading) {
		this.columnHeading = columnHeading;
	}
}
