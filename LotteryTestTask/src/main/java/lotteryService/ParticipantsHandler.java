package lotteryService;

import com.google.gson.Gson;
import dbService.DBServiceInterface;
import dbService.dataSets.ParticipantDataSet;
import dbService.dataSets.WinnerDataSet;
import org.hibernate.HibernateException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParticipantsHandler {
    private DBServiceInterface dbService;

    public ParticipantsHandler(DBServiceInterface dbService) {
        this.dbService=dbService;
    }

    public void addParticipant(Map<String, String> participant){
        dbService.insertParticipant(new ParticipantDataSet(participant.get("name"), Integer.parseInt(participant.get("age")),participant.get("city")));
    }

    public void addWinner(Map<String, String> participant, int awardValue){
        dbService.insertWinner(new WinnerDataSet(participant.get("partisipant_name"), Integer.parseInt(participant.get("age")),participant.get("city"), awardValue));
    }

    public List<String> getParticipants ()throws HibernateException {
       List objlist = dbService.selectParticipantsAll();
        List<String> jsonList = new LinkedList<>();
       if (objlist.size()<=0) {
           return null;
       }
        Iterator iterator = objlist.iterator();
        while (iterator.hasNext()) {
            Gson gson = new Gson();
            jsonList.add(gson.toJson(iterator.next()));
        }
        return jsonList;
    }

    public List<String> getWinners ()throws HibernateException {
        List objlist = dbService.selectWinnersAll();
        List<String> jsonList = new LinkedList<>();
        if (objlist.size()<=0) {
            return null;
        }
        Iterator iterator = objlist.iterator();
        while (iterator.hasNext()) {
            Gson gson = new Gson();
            jsonList.add(gson.toJson(iterator.next()));
        }
        return jsonList;
    }

    public void clearParticipants(){
        dbService.dropParticipantsTable();
    }
}
