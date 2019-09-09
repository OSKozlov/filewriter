package com.lux.ge.filereader.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lux.ge.filewriter.model.TimeseriesType;

public class FileWriterService {

	public void writeFile() {
		System.out.println("Test !!!");

		Path file = Paths.get(System.getProperty("user.dir") + File.separator + "files" + File.separator + "ge.csv");

		List<String> lines = new ArrayList<>();
		int i = 1;
		while (i < 101) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.toString(i)).append(",").append(timestamp.toString()).append(",")
					.append(TimeseriesType.TEMPERATURE.getValue()).append(",").append(rnd(40, 55));
			lines.add(sb.toString());
			i++;
			timestamp = new Timestamp(System.currentTimeMillis());
			sb = new StringBuilder();
			sb.append(Integer.toString(i)).append(",").append(timestamp.toString()).append(",")
					.append(TimeseriesType.VOLTAGE.getValue()).append(",").append(rnd(950, 1050));
			lines.add(sb.toString());
			i++;
		}

		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private int rnd(int min, int max) {
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}

}
