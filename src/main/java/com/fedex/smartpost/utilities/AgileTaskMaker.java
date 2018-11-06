package com.fedex.smartpost.utilities;

import com.fedex.smartpost.utilities.service.V1CardService;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class AgileTaskMaker {
	private static final Logger logger = Logger.getLogger(AgileTaskMaker.class);

	private static void printHelp() {
		logger.error("Usage: makeCards <excel spreadsheet> [<output pdf filename>]");
		System.err.println("Usage: makeCards <excel spreadsheet> [<output pdf filename>]");
		System.exit(-1);
	}

	public static void main(String[] args) throws IOException, InvalidFormatException, DRException {
		if (args.length == 0) {
			printHelp();
		}
		else {
			String inFile = args[0];
			String outFile = "";
			if (args.length > 1) {
				outFile = args[1];
			}
			V1CardService v1CardService = new V1CardService();
//			v1CardService.process("/Release 1 Sprint 1.2 and 1.3 user stories.xlsx", "");
			v1CardService.process(inFile, outFile);
			System.exit(0);
		}
	}
}
