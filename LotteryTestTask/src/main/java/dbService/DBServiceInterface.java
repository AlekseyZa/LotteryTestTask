package dbService;

import dbService.dao.ParticipantsDAO;
import dbService.dao.ParticipantsDAOInterface;
import dbService.dao.WinnersDAO;
import dbService.dao.WinnersDAOInterface;
import dbService.dataSets.ParticipantDataSet;
import dbService.dataSets.WinnerDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public interface DBServiceInterface {
    Configuration getPostgressConfiguration();

    void insertParticipant(ParticipantDataSet participantDataSet);

    void insertWinner(WinnerDataSet winnerDataSet);

    public List selectParticipantsAll();

    public List selectWinnersAll();

    public void dropParticipantsTable();

    static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}