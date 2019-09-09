package com.lux.ge.filewriter.app;

import java.io.IOException;
import java.util.Queue;

import com.lux.ge.filereader.service.FileReaderService;
import com.lux.ge.filereader.service.FileWriterService;
import com.lux.ge.filewriter.model.TimeseriesData;

public class FileWriterApp {

	public static void main(String[] args) {

		FileWriterService fileWriterService = new FileWriterService();
		fileWriterService.writeFile();

		FileReaderService fileReaderService = new FileReaderService();
		try {
			Queue<TimeseriesData> data = fileReaderService.readFile("ge.csv");
			for (TimeseriesData tdata : data) {
				System.err.println("guid=" + tdata.getGuid() + " time=" + tdata.getTimestamp() + " type="
						+ tdata.getType() + " value=" + tdata.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
