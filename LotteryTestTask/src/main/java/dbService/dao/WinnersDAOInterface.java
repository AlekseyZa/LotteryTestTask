package dbService.dao;

import dbService.dataSets.WinnerDataSet;
import org.hibernate.HibernateException;

import java.util.List;

public interface WinnersDAOInterface {

    void insertWinner(WinnerDataSet winnerDataSet) throws HibernateException;

    List selectWinnersAll() throws HibernateException;
}
