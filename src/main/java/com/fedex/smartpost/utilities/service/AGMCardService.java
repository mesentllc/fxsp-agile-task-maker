package com.fedex.smartpost.utilities.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;

import com.fedex.smartpost.utilities.model.TaskModel;
import com.fedex.smartpost.utilities.report.AGMTaskCreator;
import com.fedex.smartpost.utilities.report.TaskReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class AGMCardService extends CardService {
	private static final Logger logger = Logger.getLogger(AGMCardService.class);
	private JFrame parent;

	public AGMCardService(JFrame parent) {
		this.parent = parent;
	}

	@Override
	public void process(String inFile, String outFile) throws IOException, InvalidFormatException, DRException {
		List<TaskModel> cards = new ArrayList<>();
		Workbook wb;
		wb = WorkbookFactory.create(new File(inFile));
		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			if (columnNames == null) {
				columnNames = getColumnName(row);
			}
			else {
				TaskModel taskModel = getUserStory(row);
				if (isValid(taskModel)) {
					cards.add(taskModel);
				}
			}
		}
		int usCount = cards.size();
		logger.info("Total User Stories: " + usCount);
		if (wb.getNumberOfSheets() != 1) {
			sheet = wb.getSheetAt(1);
			columnNames = null;
			for (Row row : sheet) {
				if (columnNames == null) {
					columnNames = getColumnName(row);
				}
				else {
					TaskModel taskModel = getTask(row);
					if (isValid(taskModel)) {
						cards.add(taskModel);
					}
				}
			}
			logger.info("Total Tasks: " + (cards.size() - usCount));
		}
		Collections.sort(cards);
		TaskReport taskReport = new AGMTaskCreator();
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

	private TaskModel getUserStory(Row row) {
		TaskModel taskModel = new TaskModel();
		int ptr;

		for (Cell cell : row) {
			String cellValue = getStringValue(cell);
			ptr = cell.getColumnIndex();
			switch (columnNames[ptr]) {
				case "Name":
					taskModel.setUserStoryTitle(cellValue);
					break;
				case "Story Points":
					taskModel.setStoryPoints(trimPoint(cellValue));
					break;
				case "ID":
					taskModel.setUserStoryId(trimPoint(cellValue));
					break;
				case "Planned (Hours)":
					taskModel.setHours(cellValue);
					break;
				case "User Reference":
					taskModel.setItg(cellValue);
					break;
				case "Description":
					taskModel.setDescription(cellValue);
//					break;
//				case "Feature":
//					taskModel.setFeature(cellValue);
			}
		}
		return taskModel;
	}

	private TaskModel getTask(Row row) {
		TaskModel taskModel = new TaskModel();
		int ptr;

		for (Cell cell : row) {
			String cellValue = getStringValue(cell);
			ptr = cell.getColumnIndex();
			switch (columnNames[ptr]) {
				case "Backlog Item Name":
					taskModel.setUserStoryTitle(cellValue);
					break;
				case "Backlog Item ID":
					taskModel.setUserStoryId(trimPoint(cellValue));
					break;
				case "Task ID":
					taskModel.setTaskId(trimPoint(cellValue));
					break;
				case "Task Estimated Time(Hours)":
					taskModel.setHours(cellValue);
					break;
//				case "Feature":
//					taskModel.setFeature(cellValue);
//					break;
				case "Task Name":
					taskModel.setDescription(cellValue);
					try {
						taskModel.setTask(cellValue.substring(0, cellValue.indexOf(' ')));
					}
					catch (Exception e) {
						taskModel.setTask(cellValue);
					}
			}
		}
		return taskModel;
	}
}
