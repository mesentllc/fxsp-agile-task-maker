package com.fedex.smartpost.utilities.service;

import com.fedex.smartpost.utilities.model.TaskModel;
import com.fedex.smartpost.utilities.report.FeatureTaskCreator;
import com.fedex.smartpost.utilities.report.TaskReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureCardService extends CardService {
	private static final Logger logger = Logger.getLogger(FeatureCardService.class);
	private static final Color[] COLORS = {Color.YELLOW, Color.ORANGE, new Color(102, 255, 102),
	                                       new Color(102,178,255), new Color(192,192,192), new Color(178,102,255), Color.WHITE,
	                                       Color.RED, Color.BLACK, new Color(0,102,0), new Color(102, 51, 0),
	                                       new Color(204,0,204), new Color(153,0,76)};

	@Override
	public void process(String inFile, String outFile) throws IOException, InvalidFormatException, DRException {
		List<TaskModel> cards = new ArrayList<>();
		Map<String, Integer> colorSet = new HashMap<>();

		Workbook wb;
		wb = WorkbookFactory.create(new File(inFile));
		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			if (columnNames == null) {
				columnNames = getColumnName(row);
			}
			else {
				TaskModel taskModel = getFeature(row);
				if (isValid(taskModel)) {
					cards.add(taskModel);
					setTaskColor(taskModel, colorSet);
				}
			}
		}
		int usCount = cards.size();
		Collections.sort(cards);
		logger.info("Total Features: " + usCount);
		TaskReport taskReport = new FeatureTaskCreator();
		JasperReportBuilder report = taskReport.buildCards(cards);
		if (outFile.length() > 0) {
			if (!outFile.toLowerCase().endsWith(".pdf")) {
				outFile += ".pdf";
			}
			report.toPdf(new FileOutputStream(outFile));
		}
		else {
			report.print();
		}
	}

	@Override
	protected boolean isValid(TaskModel taskModel) {
		return StringUtils.isNotEmpty(taskModel.getUserStoryId())
				&& StringUtils.isNumeric(taskModel.getUserStoryId());
	}

	private void setTaskColor(TaskModel taskModel, Map<String, Integer> colorSet) {
		String feature = taskModel.getUserStoryTitle();
		if (feature == null) {
			feature = "";
		}
		int pntr = colorSet.get(feature.trim());
		if (pntr < COLORS.length) {
			taskModel.setColor(COLORS[pntr]);
		}
		else {
			taskModel.setColor(Color.WHITE);
		}
	}

	private TaskModel getFeature(Row row) {
		TaskModel taskModel = new TaskModel();
		int ptr;
		for (Cell cell : row) {
			String cellValue = getStringValue(cell);
			ptr = cell.getColumnIndex();
			if ("Name".equals(columnNames[ptr])) {
				taskModel.setUserStoryTitle(cellValue);
			}
			if ("Description".equals(columnNames[ptr])) {
				taskModel.setDescription(cellValue);
			}
			if ("Agg. Story Points".equals(columnNames[ptr])) {
				taskModel.setStoryPoints(trimPoint(cellValue));
			}
			if ("ID".equals(columnNames[ptr])) {
				taskModel.setUserStoryId(trimPoint(cellValue));
			}
			if ("Priority".equals(columnNames[ptr])) {
				taskModel.setPriority(cellValue);
			}
			if ("User Reference".equals(columnNames[ptr])) {
				taskModel.setItg(cellValue);
			}
			if ("Success Criteria".equals(columnNames[ptr])) {
				taskModel.setAcceptanceCriteria(cellValue);
			}
		}
		return taskModel;
	}
}
