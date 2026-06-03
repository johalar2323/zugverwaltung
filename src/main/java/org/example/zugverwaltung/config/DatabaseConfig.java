package org.example.zugverwaltung.config;

import org.example.zugverwaltung.model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.db.MysqlDatabaseType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Value("${db.url}")
    private String databaseUrl;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public ConnectionSource connectionSource() throws Exception {
        JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, username, password, new MysqlDatabaseType());
        return connectionSource;
    }

    @Bean
    public Dao<Zug, Long> zugDao(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Zug.class);
        return DaoManager.createDao(connectionSource, Zug.class);
    }

    @Bean
    public Dao<Waggon, Long> waggonDao(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Waggon.class);
        return DaoManager.createDao(connectionSource, Waggon.class);
    }

    @Bean
    public Dao<Fahrgast, Long> fahrgastDao(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Fahrgast.class);
        return DaoManager.createDao(connectionSource, Fahrgast.class);
    }

    @Bean
    public Dao<Fahrkarte, Long> fahrkarteDao(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Fahrkarte.class);
        return DaoManager.createDao(connectionSource, Fahrkarte.class);
//        try {
//            dao.executeRaw("ALTER TABLE fahrkarten ADD COLUMN zug_id BIGINT");
//        } catch (Exception ignored) {
//        }
//        try {
//            dao.executeRaw("ALTER TABLE fahrkarten ADD COLUMN abfahrtszeit VARCHAR(255)");
//        } catch (Exception ignored) {
//        }
//        return dao;
    }

    @Bean
    public Dao<Linie, Long> linieDao(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Linie.class);
        return DaoManager.createDao(connectionSource, Linie.class);
    }

    @Bean
    public Dao<Bahnhof, Long> bahnhofDao(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Bahnhof.class);
        return DaoManager.createDao(connectionSource, Bahnhof.class);
    }
}