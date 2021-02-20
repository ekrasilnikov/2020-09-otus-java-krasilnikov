package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.*;
import ru.otus.core.dao.ClientDao;
import ru.otus.core.model.*;
import ru.otus.core.service.*;
import ru.otus.flyway.MigrationsExecutorFlyway;
import org.hibernate.cfg.Configuration;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.ClientDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class HomeWork {

    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
// Общая часть

        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        String dbUrl = configuration.getProperty("hibernate.connection.url");
        String dbUserName = configuration.getProperty("hibernate.connection.username");
        String dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration,
                Client.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        ClientDao clientDao = new ClientDaoHibernate(sessionManager);

        HwCache<Long, Client> cache = new MyCache<>();
        HwListener<Long, Client> listener = new HwListener<Long, Client>() {
            @Override
            public void notify(Long key, Client value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };
        cache.addListener(listener);

        DBServiceClient dbServiceClient = new DbServiceClientImpl(clientDao, cache);

        Client client1 = new Client(0, "Vasya", 35, null, null);
        client1.setAddress(new AddressDataSet("Voronina st", client1));
        List<PhoneDataSet> phoneDataSetClient1 = Arrays.asList(new PhoneDataSet("79008007060", client1),
                new PhoneDataSet("75004003020", client1));
        client1.setPhones(phoneDataSetClient1);

        long id = dbServiceClient.saveClient(client1);
        Optional<Client> mayBeCreatedClient = dbServiceClient.getClient(id);
        mayBeCreatedClient.ifPresentOrElse((client) -> outputClient("Created client", client),
                () -> logger.info("Client not found"));

        id = dbServiceClient.saveClient(new Client(1L, "Petya", 40, null, null));
        Optional<Client> mayBeUpdatedClient = dbServiceClient.getClient(id);
        mayBeUpdatedClient.ifPresentOrElse((client) -> outputClient("Updated client", client),
                () -> logger.info("Client not found"));

        cache.removeListener(listener);
    }

    private static void outputClient(String header, Client client) {
        logger.info("-----------------------------------------------------------");
        logger.info(header);
        logger.info("client:{}", client);
    }
}
