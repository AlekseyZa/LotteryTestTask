package serviceManagerInit;


import dbService.DBServiceInterface;
import lotteryService.ParticipantsHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.LotteryActionServlet;
import servlets.ParticipantsActionServlet;
import servlets.WinnerActionServlet;

import java.util.logging.Logger;

public class JettyServerManager {

    public void JettyServerInitial(DBServiceInterface dbService) throws  Exception{
        ParticipantsHandler participantsHandler = new ParticipantsHandler(dbService);
        ServletContextHandler context = new ServletContextHandler((ServletContextHandler.SESSIONS));
        context.addServlet(new ServletHolder(new ParticipantsActionServlet(participantsHandler)), "/lottery/participant");
        context.addServlet(new ServletHolder(new LotteryActionServlet(participantsHandler)), "/lottery/start");
        context.addServlet(new ServletHolder(new WinnerActionServlet(participantsHandler)), "/lottery/winners");
        ResourceHandler resource_Handler = new ResourceHandler();
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_Handler, context});
        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        Logger.getGlobal().info("Server started");
        server.join();

    }
}
