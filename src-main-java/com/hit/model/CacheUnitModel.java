package com.hit.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;

import javax.swing.plaf.synth.SynthScrollPaneUI;

public class CacheUnitModel extends Observable implements Model {

	private CacheUnitClient cacheUnitClient;

	public CacheUnitModel() {
		this.cacheUnitClient = new CacheUnitClient();
	}

	@Override
	public <T> void updateModelData(T t) {

		System.out.println("in model - updateModelData");

		String request = null;
		String response = null;
		String messageToUI = null;
		try {
			if (t.equals("LoadRequest")) {
				request = this.loadRequestFromFile();
			} else if (t.equals("ShowStatistics")) {
				request = "{\"headers\":{\"action\":\"STATISTICS\"},\"body\":[]}\n";
			}

			System.out.println(request);

			response = this.cacheUnitClient.send(request);

			if (response != null) {
				System.out.println("Response from the server: " + response);

				if (t.equals("LoadRequest")) {
					messageToUI = "Success";
				} else if (t.equals("ShowStatistics")) {
					messageToUI = response;
				}

			} else {
				messageToUI = "Failed";
			}

		} catch (Exception e) {
			e.printStackTrace();
			messageToUI = "Failed";
		} finally {
			this.setChanged();
			this.notifyObservers(messageToUI);
		}
	}

	private String loadRequestFromFile() throws Exception {
		String filePath = "request.txt";
		String fileContent = null;
		FileInputStream fis = null;
		ObjectInputStream is = null;
		System.out.println("in model - loadRequestFromFile");

		try {
			byte[] fileByteArr = Files.readAllBytes(Paths.get(filePath));

			fileContent = new String(fileByteArr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (is != null) {
				is.close();
			}
		}
		if (fileContent == null) {
			throw new Exception("request file read failed");
		}
		return fileContent;
	}

}
