package org.example.zugverwaltung.service;

import org.example.zugverwaltung.model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ZugService {
    // Database Access Object
    private final Dao<Zug, Long> zugDao;
    private final Dao<Waggon, Long> waggonDao;
    private final Dao<Fahrkarte, Long> fahrkarteDao;

    public ZugService(Dao<Zug, Long> zugDao, Dao<Waggon, Long> waggonDao, Dao<Fahrkarte, Long> fahrkarteDao) {
        this.zugDao = zugDao;
        this.waggonDao = waggonDao;
        this.fahrkarteDao = fahrkarteDao;
    }

    public void zugAnlegen(Zug zug) throws SQLException {
        zugDao.create(zug);
    }

    public void zugLoeschen(Long zugId) throws SQLException {
        List<Waggon> waggons = waggonDao.queryBuilder().where().eq("zug_id", zugId).query();

        for (Waggon waggon : waggons) {
            DeleteBuilder<Fahrkarte, Long> deleteFahrkarten = fahrkarteDao.deleteBuilder();
            deleteFahrkarten.where().eq("waggon_id", waggon.getId());
            deleteFahrkarten.delete();

            waggonDao.deleteById(waggon.getId());
        }
        zugDao.deleteById(zugId);
    }

    public List<Zug> alleZuege() throws SQLException {
        return zugDao.queryForAll();
    }

    public Zug zugById(Long id) throws SQLException {
        return zugDao.queryForId(id);
    }

    public void waggonAnlegen(Waggon waggon) throws SQLException {
        waggonDao.create(waggon);
    }

    public List<Waggon> alleWaggons() throws SQLException {
        return waggonDao.queryForAll();
    }

    public void waggonHinzufuegen(Zug zug, Waggon waggon) throws SQLException {
        waggon.setZug(zug);
        waggonDao.createOrUpdate(waggon);
    }

    public void waggonLoeschen(Long id) throws SQLException {
        waggonDao.deleteById(id);
    }

    public void linieHinzufuegen(Zug zug, Linie linie) throws SQLException {
        zug.setLinie(linie);
        zugDao.update(zug);
    }
}