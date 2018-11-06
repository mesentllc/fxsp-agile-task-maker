package com.fedex.smartpost.utilities.report;

import com.fedex.smartpost.utilities.model.TaskModel;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

public class AGMTaskCreator extends TaskReport {
	@Override
	protected ComponentBuilder<?, ?> buildCard(TaskModel taskModel) {
		if (taskModel == null) {
			StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point())
					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
			return cmp.text("Blank Card").setStyle(style);
		}
		StyleBuilder style = styleBuilders.style(styleBuilders.pen1Point());
		return cmp.verticalList(buildCardHeader(taskModel),
				buildDecription(taskModel.getItg(), taskModel.getDescription()))
				.setFixedHeight(cardHeight).setFixedWidth(cardWidth).setStyle(style);
	}
}
