package org.misha.yaml;

import org.apache.log4j.Logger;
import org.misha.YamlConfigRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements Factory<Connection> {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
    private final String dbPassword;
    private final String dbUrl;
    private final String dbUser;

    public ConnectionFactory() throws IOException {
        final Configuration conf = loadConfig();
        log.info(conf.toString());
        dbUrl = conf.getConnection().getUrl();
        dbPassword = conf.getConnection().getPassword();
        dbUser = conf.getConnection().getUser();
    }

    @Override
    public Connection get() {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Connection is not created " + e.getMessage());
            throw new IllegalStateException("Connection is not created", e);
        }
    }

    private Configuration loadConfig() throws IOException {
        return YamlConfigRunner.run();
    }
}
