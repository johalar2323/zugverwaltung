package org.example.zugverwaltung.service;

import com.j256.ormlite.dao.Dao;
import org.example.zugverwaltung.model.Bahnhof;
import org.example.zugverwaltung.model.Linie;

import java.sql.SQLException;
import java.util.List;

public class LinieService {
    private final Dao<Linie, Long> linieDao;
    private final Dao<Bahnhof, Long> bhfDao;

    public LinieService(Dao<Linie, Long> linieDao, Dao<Bahnhof, Long> bhfDao) {
        this.linieDao = linieDao;
        this.bhfDao = bhfDao;
    }

    public void linieAnlegen(Linie linie) throws SQLException {
        linieDao.create(linie);
    }

    public void linieLoeschen(Long id) throws SQLException {
        linieDao.deleteById(id);
    }

    public List<Linie> alleLinien() throws SQLException {
        return linieDao.queryForAll();
    }

    public void linieById(Long id) throws SQLException {
        linieDao.queryForId(id);
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

    public void bahnhofById(Long id) throws SQLException {
        bhfDao.queryForId(id);
    }
}
