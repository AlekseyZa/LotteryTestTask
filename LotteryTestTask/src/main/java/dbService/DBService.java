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
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.logging.Logger;

public class DBService implements DBServiceInterface{

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";
    private final SessionFactory sessionFactory;

    public DBService(){
       Configuration configuration = getPostgressConfiguration();
        sessionFactory = createSessionFactory(configuration);
        Logger.getGlobal().info("DB started");;
    }

    public void insertParticipant(ParticipantDataSet participantDataSet) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ParticipantsDAOInterface dao = new ParticipantsDAO(session);
            dao.insertParticipant(participantDataSet);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void insertWinner(WinnerDataSet winnerDataSet) throws HibernateException{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            WinnersDAOInterface dao = new WinnersDAO(session);
            dao.insertWinner(winnerDataSet);
            transaction.commit();
            session.close();
    }

    public List selectParticipantsAll() throws HibernateException{
            Session session = sessionFactory.openSession();
            //Transaction transaction = session.beginTransaction();
            ParticipantsDAOInterface dao = new ParticipantsDAO(session);
            List list = dao.selectParticipantsAll();
            session.close();
            return list;
    }

    public List selectWinnersAll() throws HibernateException{
        Session session = sessionFactory.openSession();
       // Transaction transaction = session.beginTransaction();
        WinnersDAOInterface dao = new WinnersDAO(session);
        List list = dao.selectWinnersAll();
        session.close();
        return list;
    }

    public Configuration getPostgressConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(ParticipantDataSet.class);
        configuration.addAnnotatedClass(WinnerDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    public void dropParticipantsTable() {
        Session session = sessionFactory.openSession();;
        session.beginTransaction();
        session.createQuery("DELETE FROM ParticipantDataSet").executeUpdate();
        session.getTransaction().commit();
    }

    private static SessionFactory createSessionFactory(Configuration configuration){
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
