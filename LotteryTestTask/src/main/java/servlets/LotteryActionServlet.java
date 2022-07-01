package servlets;

import lotteryService.ParticipantsHandler;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LotteryActionServlet extends HttpServlet {

    private final ParticipantsHandler participantsHandler;

    public LotteryActionServlet(ParticipantsHandler participantsHandler) {
        this.participantsHandler = participantsHandler;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> participantsList = participantsHandler.getParticipants();
        Map<String, String> winner = new HashMap<>();
        int participantsListSize = participantsList.size();
        if (participantsListSize<2){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Random rand = new Random();
        int randomAward = rand.nextInt(1000);
        String randomParticipant = participantsList.get(rand.nextInt(participantsListSize));
        JSONObject jObj = new JSONObject(randomParticipant);
        Iterator<String> it = jObj.keys();
        while(it.hasNext()){
            String key = it.next();
            Object o = jObj.get(key);
            winner.put(key, o.toString());
        }
        participantsHandler.addWinner(winner, randomAward);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(randomParticipant+" "+"Reward = " + randomAward);
        participantsHandler.clearParticipants();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}