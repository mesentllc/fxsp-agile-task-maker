package com.fedex.smartpost.utilities.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fedex.smartpost.utilities.model.TaskModel;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

public abstract class CardService {
	protected String[] columnNames = null;

	public abstract void process(String inFile, String outFile) throws IOException, InvalidFormatException, DRException;

	protected boolean isValid(TaskModel taskModel) {
		if (StringUtils.isEmpty(taskModel.getUserStoryId()) && StringUtils.isEmpty(taskModel.getTaskId())) {
			return false;
		}
		if (StringUtils.isEmpty(taskModel.getUserStoryId())) {
			return StringUtils.isNumeric(taskModel.getTaskId());
		}
		if (StringUtils.isEmpty(taskModel.getTaskId())) {
			return StringUtils.isNumeric(taskModel.getUserStoryId());
		}
		return StringUtils.isNumeric(taskModel.getTaskId()) && StringUtils.isNumeric(taskModel.getUserStoryId());
	}

	protected String getStringValue(Cell cell) {
		String returnValue = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				returnValue = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					returnValue = cell.getDateCellValue().toString();
				}
				else {
					returnValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				returnValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				returnValue = cell.getCellFormula();
		}
		if (returnValue != null) {
			returnValue = cleanUp(returnValue);
		}
		return returnValue;
	}

	private String cleanUp(String string) {
		if (StringUtils.isEmpty(string)) {
			return string;
		}
		StringBuilder sb = new StringBuilder();
		String[] splitString = string.split("\n");
		for (String s : splitString) {
			if (StringUtils.isNotEmpty(s.trim())) {
				sb.append(s).append("\n");
			}
		}
		return sb.toString();
	}

	protected String[] getColumnName(Row row) {
		List<String> headerList = new ArrayList<>();
		for (Cell cell : row) {
			headerList.add(cell.getRichStringCellValue().getString());
		}
		return headerList.toArray(new String[headerList.size()]);
	}

	protected String trimPoint(String cellValue) {
		if (cellValue == null) {
			return null;
		}
		if (cellValue.indexOf('.') == -1) {
			return cellValue;
		}
		return cellValue.substring(0, cellValue.indexOf('.'));
	}
}
