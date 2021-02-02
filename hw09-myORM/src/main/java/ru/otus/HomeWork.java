package ru.otus;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.Account;
import ru.otus.core.model.Client;
import ru.otus.core.service.DbServiceAccountImpl;
import ru.otus.core.service.DbServiceClientImpl;
import ru.otus.jdbc.DataSourceHomeWork;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.util.Optional;


public class HomeWork {

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new DataSourceHomeWork();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Работа с клиентами
        DbExecutorImpl<Client> dbExecutorClient = new DbExecutorImpl<>();
        EntitySQLMetaData entitySQLMetaDataClient = new EntitySQLMetaDataImpl(Client.class);
        EntityClassMetaData<Client> entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class);
        JdbcMapper<Client> clientDao = new JdbcMapperImpl<>(sessionManager, dbExecutorClient, entitySQLMetaDataClient, entityClassMetaDataClient);

// Код дальше должен остаться, т.е. clientDao должен использоваться
        var dbServiceClient = new DbServiceClientImpl(clientDao, sessionManager);
        var idClient = dbServiceClient.saveClient(new Client(1, "HomeWorkTestClientOriginal", 30));
        Optional<Client> clientOptional = dbServiceClient.getClient(idClient);
        clientOptional.ifPresentOrElse(
                client -> log.info("created client, name:{}", client.getName()),
                () -> log.info("client was not created")
        );

        dbServiceClient.saveClient(new Client(idClient, "HomeWorkTestClientEdit", 99));
        clientOptional = dbServiceClient.getClient(idClient);
        clientOptional.ifPresentOrElse(
                client -> log.info("updated client, name:{}", client.getName()),
                () -> log.info("client was not updated")
        );

// Работа со счетом
        DbExecutorImpl<Account> dbExecutorAccount = new DbExecutorImpl<>();
        EntitySQLMetaData entitySQLMetaDataAccount = new EntitySQLMetaDataImpl(Account.class);
        EntityClassMetaData<Account> entityClassMetaDataAccount = new EntityClassMetaDataImpl<>(Account.class);
        JdbcMapper<Account> accountDao = new JdbcMapperImpl<>(sessionManager, dbExecutorAccount, entitySQLMetaDataAccount, entityClassMetaDataAccount);

        var dbServiceAccount = new DbServiceAccountImpl(accountDao, sessionManager);
        String idAccount = "aaa-bbb-cc-dd-e";
        dbServiceAccount.saveAccount(new Account(idAccount, "HomeWorkTestAccountOriginal", 100.99));
        Optional<Account> accountOptional = dbServiceAccount.getAccount(idAccount);
        accountOptional.ifPresentOrElse(
                account -> log.info("created account, type:{}", account.getType()),
                () -> log.info("account was not created")
        );

        dbServiceAccount.saveAccount(new Account(idAccount, "HomeWorkTestAccountEdit", 8888.99));
        accountOptional = dbServiceAccount.getAccount(idAccount);
        accountOptional.ifPresentOrElse(
                account -> log.info("updated account, type:{}", account.getType()),
                () -> log.info("account was not updated")
        );
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
