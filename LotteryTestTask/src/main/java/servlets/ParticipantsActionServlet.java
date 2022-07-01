package servlets;

import lotteryService.ParticipantsHandler;
import org.json.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParticipantsActionServlet extends HttpServlet {

    private final ParticipantsHandler participantsHandler;

    public ParticipantsActionServlet(ParticipantsHandler participantsHandler) {
        this.participantsHandler = participantsHandler;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(participantsHandler.getParticipants());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> participant = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader=null;
               try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        JSONObject jObj = new JSONObject(stringBuilder.toString());
        Iterator<String> it = jObj.keys();
        while(it.hasNext()){
            String key = it.next();
            Object o = jObj.get(key);
            participant.put(key, o.toString());
        }
        participantsHandler.addParticipant(participant);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(participant);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}

