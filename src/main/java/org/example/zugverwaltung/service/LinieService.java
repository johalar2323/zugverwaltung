package org.example.zugverwaltung.service;

import com.j256.ormlite.dao.Dao;
import org.example.zugverwaltung.model.Linie;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LinieService {
    private final Dao<Linie, Long> linieDao;

    public LinieService(Dao<Linie, Long> linieDao) {
        this.linieDao = linieDao;
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

    public Linie linieById(Long id) throws SQLException {
        return linieDao.queryForId(id);
    }
}
