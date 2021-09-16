package com.company;
import com.sun.net.httpserver.HttpServer;
import java.net.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(9991), 0);
		server.createContext("/test", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
	}
}
