package main;

import serviceManagerInit.DBServiceManager;
import serviceManagerInit.JettyServerManager;

public class Main {

    public static void main(String [] args) throws Exception{

        DBServiceManager dbServiceManager = new DBServiceManager();
        JettyServerManager jettyServerManager = new JettyServerManager();
        jettyServerManager.JettyServerInitial(dbServiceManager.getDBService());
    }

}
