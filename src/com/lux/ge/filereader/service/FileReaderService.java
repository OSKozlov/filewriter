package com.lux.ge.filereader.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lux.ge.filewriter.model.TimeseriesData;


public class FileReaderService {

	private Logger logger = Logger.getLogger(FileReader.class.getName());
	
	public Queue<TimeseriesData> readFile(String fileName) throws IOException {
		Queue<TimeseriesData> queue = new LinkedList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "files" + File.separator + fileName));
			String line = bufferedReader.readLine();
			while (line != null) {
				line = bufferedReader.readLine();
				if (line != null) {
					String[] splitline = line.split(",");
					TimeseriesData timeseriesData = new TimeseriesData();
					timeseriesData.setGuid(Integer.valueOf(splitline[0]));
					timeseriesData.setTimestamp(Timestamp.valueOf(splitline[1]));
					timeseriesData.setType(splitline[2]);
					timeseriesData.setValue(Float.valueOf(splitline[3]));
					timeseriesData.setFileName(fileName);
					queue.add(timeseriesData);
				}
			}
			return queue;
		} catch (IOException e) {
			logger.log(Level.WARNING, "Error occured while reading folder.", e);
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return null;
	}
}
