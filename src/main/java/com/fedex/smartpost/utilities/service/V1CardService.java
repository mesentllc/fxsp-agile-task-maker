package com.fedex.smartpost.utilities.service;

import com.fedex.smartpost.utilities.model.TaskModel;
import com.fedex.smartpost.utilities.report.TaskReport;
import com.fedex.smartpost.utilities.report.V1TaskCreator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class V1CardService extends CardService {
	private static final Logger logger = Logger.getLogger(V1CardService.class);

	@Override
	public void process(String inFile, String outFile) throws IOException, InvalidFormatException, DRException {
		List<TaskModel> tasks = new ArrayList<>();
		TaskModel taskModel = null;
		Workbook wb;
		wb = WorkbookFactory.create(new File(inFile));
		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			if (columnNames == null) {
				columnNames = getColumnName(row);
			}
			else {
				taskModel = getTask(taskModel, row);
				tasks.add(taskModel);
			}
		}
		Collections.sort(tasks);
		logger.info("Total Tasks: " + tasks.size());
		TaskReport taskReport = new V1TaskCreator();
		JasperReportBuilder report = taskReport.buildCards(tasks);
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

	private TaskModel getTask(TaskModel inModel, Row row) {
		int ptr;
		TaskModel taskModel = cloneModel(inModel);

		for (Cell cell : row) {
			String cellValue = getStringValue(cell);
			ptr = cell.getColumnIndex();

			if ("Title".equals(columnNames[ptr])) {
				taskModel.setUserStoryTitle(cellValue);
			}
			if ("ID".equals(columnNames[ptr])) {
				if (cellValue.startsWith("B-")) {
					taskModel = new TaskModel(taskModel.getUserStoryTitle());
					taskModel.setUserStoryId(cellValue);
				}
				else {
					taskModel.setTaskId(cellValue);
					taskModel.setTask(taskModel.getUserStoryTitle());
				}
			}
			if ("To Do Hrs.".equals(columnNames[ptr])) {
				taskModel.setHours(cellValue);
			}
			if ("Description".equals(columnNames[ptr])) {
				taskModel.setDescription(cellValue);
			}
			if ("Acceptance Criteria".equals(columnNames[ptr])) {
				taskModel.setAcceptanceCriteria(cellValue);
			}
			if ("ITG #".equals(columnNames[ptr])) {
				taskModel.setItg(cellValue);
			}
			if ("Estimate Pts.".equals(columnNames[ptr])) {
				taskModel.setStoryPoints(cellValue);
			}
		}
		return taskModel;
	}

	private TaskModel cloneModel(TaskModel inModel) {
		if (inModel == null) {
			return new TaskModel();
		}
		TaskModel taskModel = new TaskModel();
		taskModel.setAcceptanceCriteria(inModel.getAcceptanceCriteria());
		taskModel.setUserStoryTitle(inModel.getUserStoryTitle());
		taskModel.setUserStoryId(inModel.getUserStoryId());
		taskModel.setDescription(inModel.getDescription());
		taskModel.setTask(inModel.getTask());
		taskModel.setTaskId(inModel.getTaskId());
		taskModel.setItg(inModel.getItg());
		taskModel.setStoryPoints(inModel.getStoryPoints());
		return taskModel;
	}
}
