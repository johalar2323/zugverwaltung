package org.example.zugverwaltung.service;

import org.example.zugverwaltung.model.Fahrgast;
import org.example.zugverwaltung.model.Fahrkarte;
import org.example.zugverwaltung.model.Waggon;
import org.example.zugverwaltung.model.Zug;
import com.j256.ormlite.dao.Dao;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FahrkartenService {
    private final Dao<Fahrkarte, Long> fahrkartenDao;
    private final Dao<Waggon, Long> waggonDao;

    public FahrkartenService(Dao<Fahrkarte, Long> fahrkartenDao, Dao<Waggon, Long> waggonDao) {
        this.fahrkartenDao = fahrkartenDao;
        this.waggonDao = waggonDao;
    }

    public void fahrkarteKaufen(int anzahl, Zug zug, Waggon waggon, Fahrgast fahrgast, LocalDateTime abfahrtszeit) throws SQLException {

        if (waggon.getZug() == null || waggon.getZug().getId() != zug.getId()) {
            throw new SQLException("Waggon \"" + waggon.getBezeichnung()
                    + "\" gehört nicht zu Zug \"" + zug.getName() + "\"!");
        }

        if (waggon.getPlaetze() < anzahl) {
            throw new SQLException("Nicht genügend Plätze im Waggon: " + waggon.getBezeichnung()
                    + " (verfügbar: " + waggon.getPlaetze() + ")");
        }

        Fahrkarte fahrkarte = new Fahrkarte(anzahl, zug, waggon, fahrgast, abfahrtszeit);
        fahrkartenDao.create(fahrkarte);

        waggon.setPlaetze(waggon.getPlaetze() - anzahl);
        waggonDao.update(waggon);
    }

    public List<Fahrkarte> alleFahrkarten() throws SQLException {
        return fahrkartenDao.queryForAll();
    }
}