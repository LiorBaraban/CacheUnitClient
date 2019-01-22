package com.hit.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class CacheUnitClient {

	String ipAddress;
	int port;

	public CacheUnitClient() {
		this.ipAddress = "127.0.0.1";
		this.port = 12345;
	}

	public String send(String request) throws IOException {
		Socket socket = null;
		PrintWriter socketWriter = null;
		BufferedReader socketReader = null;
		String messageFromServer = null;
		try {
			socket = new Socket(this.ipAddress, this.port);
			socketWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketWriter.write(request);
			socketWriter.flush();
			messageFromServer = socketReader.readLine();
			System.out.println("message from server " + messageFromServer);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
			if (socketWriter != null) {
				socketWriter.close();
			}
			if (socketReader != null) {
				socketReader.close();
			}
		}
		return messageFromServer;
	}
}
