package servlets;

import lotteryService.ParticipantsHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WinnerActionServlet extends HttpServlet {
    private final ParticipantsHandler participantsHandler;

    public WinnerActionServlet(ParticipantsHandler participantsHandler) {
        this.participantsHandler = participantsHandler;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(participantsHandler.getWinners());
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
