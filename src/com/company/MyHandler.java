package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange t) throws IOException {
		String methodName = t.getRequestMethod();
		StringBuilder requestBody = requestBode(t.getRequestBody());

		// response is what we are returning when the http call finish
		// we can return anything here
		String response = "Action type: " + methodName + "\nRequest body: " + requestBody + "\nStatus: " + 200;
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	StringBuilder requestBode(InputStream requestBody) throws IOException {
		InputStreamReader isr = new InputStreamReader(requestBody, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isr);
		int b;
		StringBuilder buf = new StringBuilder(512);
		while ((b = br.read()) != -1) {
			buf.append((char) b);
		}
		br.close();
		isr.close();
		return buf;
	}
}
