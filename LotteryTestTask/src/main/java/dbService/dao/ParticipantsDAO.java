package dbService.dao;

import dbService.dataSets.ParticipantDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ParticipantsDAO implements ParticipantsDAOInterface {

    private Session session;

    public ParticipantsDAO(Session session) {
        this.session = session;
    }

    public void insertParticipant(ParticipantDataSet participantDataSet) throws HibernateException {
        session.save(participantDataSet);
    }

    public List selectParticipantsAll() throws HibernateException {
        Query query = session.createQuery("from ParticipantDataSet");
        return query.list();
    }
}
