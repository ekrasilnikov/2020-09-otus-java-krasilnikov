package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.Account;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public class DbServiceAccountImpl implements DBServiceAccount {

    private final JdbcMapper<Account> jdbcMapper;
    private final SessionManager sessionManager;

    private static final Logger logger = LoggerFactory.getLogger(DbServiceAccountImpl.class);

    public DbServiceAccountImpl(JdbcMapper<Account> jdbcMapper, SessionManager sessionManager) {
        this.jdbcMapper = jdbcMapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public String saveAccount(Account account) {
        sessionManager.beginSession();
        try {
            jdbcMapper.insertOrUpdate(account);
            sessionManager.commitSession();

            logger.info("created account: {}", account);
            return account.getNo();
        } catch (Exception e) {
            sessionManager.rollbackSession();
            throw new DbServiceException(e);
        }
    }

    @Override
    public Optional<Account> getAccount(String id) {
        sessionManager.beginSession();
        try {
            Account account = jdbcMapper.findById(id, Account.class);

            logger.info("get account: {}", account);
            return Optional.ofNullable(account);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            sessionManager.rollbackSession();
        }
        return Optional.empty();
    }
}
