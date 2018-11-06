package com.fedex.smartpost.utilities.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.awt.Color;

import com.fedex.smartpost.utilities.model.TaskModel;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

public class EpicTaskCreator extends TaskReport {
	private static final StyleBuilder lineStyle = styleBuilders.style(styleBuilders.pen1Point()).bold().setFontSize(7);
	private static final Integer headerWidth = cardWidth * 6 / 10;

	private StyleBuilder norm20Style(Color color) {
		if (color == null) {
			color = Color.BLACK;
		}
		return DynamicReports.stl.style(styleBuilders.style()).setFontSize(20)
		                         .setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.TOP).setPadding(2).setForegroundColor(color);
	}

	private ComponentBuilder<?, ?> buildLine1(TaskModel taskModel) {
		return cmp.horizontalList(cmp.text(getString(taskModel.getEpic())).setStyle(bold7Style).setFixedWidth(headerWidth),
				cmp.horizontalList(cmp.text("Release:\n" + getString(taskModel.getRelease())).setStyle(bold7Style),
						cmp.text("Sprint:\n").setStyle(lineStyle)).setStyle(lineStyle))
				.setStyle(lineStyle);
	}

	private ComponentBuilder<?, ?> buildLine2(TaskModel taskModel) {
		return cmp.horizontalList(cmp.text("Feature:\n" + " " + getString(taskModel.getFeature())).setStyle(bold7Style)
						.setFixedWidth(headerWidth).setStyle(lineStyle),
				cmp.verticalList(cmp.text("Assigned To:\n" + getString(taskModel.getAssignedTo())).setStyle(bold7Style))
						.setStyle(bold7Style)).setStyle(lineStyle);
	}

	private ComponentBuilder<?, ?> buildLine3(TaskModel taskModel) {
		return cmp.horizontalList(cmp.text("User Story: " + getString(taskModel.getUserStoryTitle())).setStyle(bold7Style)
						.setStyle(lineStyle).setFixedWidth(headerWidth),
				cmp.horizontalList(cmp.text("Id:\n" + getString(taskModel.getUserStoryId())).setStyle(bold7Style),
						cmp.verticalList(cmp.text("Points:").setStyle(bold7Style), cmp.text(taskModel.getStoryPoints())
						                                                              .setStyle(norm20Style(taskModel.getColor())))
						   .setStyle(lineStyle))).setStyle(lineStyle);
	}

/*
	private ComponentBuilder<?, ?> enumerateChecklist() {
		int width = cardWidth / 16;
		return cmp.verticalList(cmp.horizontalList(cmp.text("1.  Review Data Model").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("2.  Map Source Target").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("3.  Review Objects").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("4.  File Format").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("5.  Config Setup").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("6.  Coding").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("7.  Testing").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("8.  Paperwork").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("9.  Code Review").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("10. Deploy").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("11. Checkout").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)),
				cmp.horizontalList(cmp.text("12. Review Data Model").setStyle(noline8style), cmp.text(" ").setStyle(line8style).setFixedWidth(width)));
	}
*/

	protected ComponentBuilder<?, ?> buildCardHeader(TaskModel taskModel) {
		Integer headerHeight = cardHeight / 3;
		return cmp.verticalList(buildLine1(taskModel), buildLine2(taskModel), buildLine3(taskModel)).setFixedHeight(headerHeight);
	}

/*
	private ComponentBuilder<?, ?> buildDecription(TaskModel taskModel) {
		return cmp.horizontalList(cmp.text("Description:\n" + getString(taskModel.getDescription())).setStyle(Templates.printStyle).setFixedWidth(headerWidth),
				enumerateChecklist().setStyle(lineStyle)).setStyle(lineStyle);
	}
*/

	private ComponentBuilder<?, ?> buildDecription(TaskModel taskModel) {
		return cmp.verticalList(cmp.text("Description:\n" + getString(taskModel.getDescription())).setStyle(Templates.printStyle).setFixedWidth(cardWidth))
		          .setStyle(lineStyle);
	}

	@Override
	protected ComponentBuilder<?, ?> buildCard(TaskModel taskModel) {
		if (taskModel == null) {
			StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point()).setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
			return cmp.text("Blank Card").setStyle(style);
		}
		return cmp.verticalList(buildCardHeader(taskModel), buildDecription(taskModel)).setFixedWidth(cardWidth).setFixedHeight(cardHeight).setStyle(lineStyle);
	}
}
