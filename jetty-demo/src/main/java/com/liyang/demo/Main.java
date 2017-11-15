package com.liyang.demo;

import org.eclipse.jetty.server.Server;

public class Main {
	public static void main(String[] args) throws Exception
	{
	  Server server = new Server(8080);
	  server.setHandler(new HelloHandler());
	  
//	  ResourceHandler resourceHandler = new ResourceHandler();
//	  resourceHandler.setResourceBase("D:/test");

//	  server.setHandler(resourceHandler);
	  
	  server.start();
	}
}
