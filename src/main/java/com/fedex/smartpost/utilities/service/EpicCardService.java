package com.fedex.smartpost.utilities.service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fedex.smartpost.utilities.model.TaskModel;
import com.fedex.smartpost.utilities.report.EpicTaskCreator;
import com.fedex.smartpost.utilities.report.TaskReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class EpicCardService extends ExcelCardService {
	private static final Log log = LogFactory.getLog(EpicCardService.class);
	private static final Color[] COLORS =
			{Color.YELLOW, Color.ORANGE, new Color(102, 255, 102),
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
				TaskModel taskModel = getEpic(row);
				cards.add(taskModel);
				addToMap(colorSet, taskModel.getEpic());
				setTaskColor(taskModel, colorSet);
			}
		}
		Collections.sort(cards);
		int usCount = cards.size();
		log.info("Total User Stories: " + usCount);
		TaskReport taskReport = new EpicTaskCreator();
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

	private void addToMap(Map<String, Integer> colorSet, String epic) {
		if (epic == null) {
			epic = "";
		}
		if (colorSet.get(epic.trim()) != null) {
			return;
		}
		colorSet.put(epic.trim(), colorSet.size());
	}

	private void setTaskColor(TaskModel taskModel, Map<String, Integer> colorSet) {
		String epic = taskModel.getEpic();
		if (epic == null) {
			epic = "";
		}
		int pntr = colorSet.get(epic.trim());
		if (pntr < COLORS.length) {
			taskModel.setColor(COLORS[pntr]);
		}
		else {
			taskModel.setColor(Color.WHITE);
		}
	}

	private TaskModel getEpic(Row row) {
		TaskModel taskModel = new TaskModel();
		int ptr;
		for (Cell cell : row) {
			String cellValue = getStringValue(cell);
			if (cellValue != null) {
				cellValue = cellValue.replaceAll("\\n", " ");
			}
			ptr = cell.getColumnIndex();
			if ("Theme".equals(columnNames[ptr].trim())) {
				taskModel.setEpic(cellValue);
			}
			if ("Release".equals(columnNames[ptr].trim())) {
				taskModel.setRelease(cellValue);
			}
			if ("Feature".equals(columnNames[ptr].trim())) {
				taskModel.setFeature(cellValue);
			}
			if ("Assigned To".equals(columnNames[ptr].trim())) {
				taskModel.setAssignedTo(cellValue);
			}
			if ("Name".equals(columnNames[ptr].trim())) {
				taskModel.setUserStoryTitle(cellValue);
			}
			if ("Description".equals(columnNames[ptr].trim())) {
				taskModel.setDescription(cellValue);
			}
			if ("ID".equals(columnNames[ptr].trim())) {
				taskModel.setUserStoryId(trimPoint(cellValue));
			}
			if ("Story Points".equals(columnNames[ptr].trim())) {
				taskModel.setStoryPoints(trimPoint(cellValue));
			}
		}
		return taskModel;
	}
}
