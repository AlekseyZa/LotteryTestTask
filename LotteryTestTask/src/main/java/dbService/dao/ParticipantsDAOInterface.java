package dbService.dao;

import dbService.dataSets.ParticipantDataSet;
import org.hibernate.HibernateException;


import java.util.List;

public interface ParticipantsDAOInterface {

    void insertParticipant (ParticipantDataSet participantDataSet) throws HibernateException;

    List selectParticipantsAll() throws HibernateException;
}
