package org.example.zugverwaltung.service;

import com.j256.ormlite.dao.Dao;
import org.example.zugverwaltung.model.Bahnhof;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BhfService {
    private final Dao<Bahnhof, Long> bhfDao;

    public BhfService(Dao<Bahnhof, Long> bhfDao) {
        this.bhfDao = bhfDao;
    }

    public void bahnhofAnlegen(Bahnhof bahnhof) throws SQLException {
        bhfDao.create(bahnhof);
    }

    public void bahnhofLoeschen(Long id) throws SQLException {
        bhfDao.deleteById(id);
    }

    public List<Bahnhof> alleBahnhoefe() throws SQLException {
        return bhfDao.queryForAll();
    }

    public Bahnhof bahnhofById(Long id) throws SQLException {
        return bhfDao.queryForId(id);
    }
}
