package com.fedex.smartpost.utilities.report;

import com.fedex.smartpost.utilities.model.TaskModel;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

public class V1TaskCreator extends TaskReport {
	private ComponentBuilder<?, ?> buildAcceptance(String acceptanceCriteria) {
		StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point());
		Integer sectionHeight = cardHeight * 2 / 4;
		return cmp.verticalList(cmp.verticalGap(5),
				cmp.text("Acceptance Criteria:\n" + getString(acceptanceCriteria)).setStyle(Templates.printStyle))
				.setStyle(style).setFixedHeight(sectionHeight);
	}

	@Override
	protected ComponentBuilder<?, ?> buildDecription(String itg, String description) {
		Integer sectionHeight = cardHeight / 4;
		StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point());
		return cmp.verticalList(cmp.verticalGap(5),
				cmp.text("ITG: " + getString(itg) + "\n" + getString(description)).setStyle(Templates.printStyle))
				.setStyle(style).setFixedHeight(sectionHeight);
	}

	@Override
	protected ComponentBuilder<?, ?> buildCard(TaskModel taskModel) {
		if (taskModel == null) {
			StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point())
					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
			return cmp.text("Blank Card").setStyle(style);
		}
		StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point());
		return cmp.verticalList(buildCardHeader(taskModel),
				buildDecription(taskModel.getItg(), taskModel.getDescription()),
				buildAcceptance(taskModel.getAcceptanceCriteria())).setFixedHeight(cardHeight).setFixedWidth(cardWidth).setStyle(style);
	}
}
