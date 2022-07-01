package dbService.dao;

import dbService.dataSets.WinnerDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class WinnersDAO implements WinnersDAOInterface{

    private Session session;

    public WinnersDAO(Session session) {
        this.session = session;
    }

    public void insertWinner(WinnerDataSet winnerDataSet) throws HibernateException {
        session.save(winnerDataSet);
    }

    public List selectWinnersAll() throws HibernateException {
        Query query = session.createQuery("from WinnerDataSet");
        return query.list();
    }
}
