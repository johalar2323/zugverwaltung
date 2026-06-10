package org.example.zugverwaltung.controller;

import org.example.zugverwaltung.model.Fahrgast;
import org.example.zugverwaltung.model.Fahrkarte;
import org.example.zugverwaltung.model.Waggon;
import org.example.zugverwaltung.model.Zug;
import org.example.zugverwaltung.service.FahrkartenService;
import org.example.zugverwaltung.service.ZugService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final FahrkartenService fahrkartenService;
    private final ZugService zugService;
    private final Dao<Waggon, Long> waggonDao;
    private final Dao<Fahrgast, Long> fahrgastDao;

    public TicketController(FahrkartenService fahrkartenService, ZugService zugService,
                            Dao<Waggon, Long> waggonDao, Dao<Fahrgast, Long> fahrgastDao) {
        this.fahrkartenService = fahrkartenService;
        this.zugService = zugService;
        this.waggonDao = waggonDao;
        this.fahrgastDao = fahrgastDao;
    }

    // GET /api/tickets/fahrgaeste
    @GetMapping("/fahrgaeste")
    public List<Fahrgast> getAlleFahrgaeste() throws SQLException {
        return fahrgastDao.queryForAll();
    }

    // POST /api/tickets/fahrgaeste
    @PostMapping("/fahrgaeste")
    public String fahrgastAnlegen(@RequestBody Fahrgast fahrgast) throws SQLException {
        fahrgastDao.create(fahrgast);
        return "Fahrgast \"" + fahrgast.getName() + "\" erfolgreich angelegt!";
    }

    // GET /api/tickets/waggons?zugId=1 - Gibt alle Waggons eines bestimmten Zuges zurück.
    @GetMapping("/waggons")
    public List<Waggon> getWaggonsByZug(@RequestParam Long zugId) throws SQLException {
        QueryBuilder<Waggon, Long> qb = waggonDao.queryBuilder();
        qb.where().eq("zug_id", zugId);
        return qb.query();
    }

    // POST /api/tickets/buy
    @PostMapping("/buy")
    public String ticketKaufen(@RequestParam Long zugId, @RequestParam Long waggonId, @RequestParam Long fahrgastId,
                               @RequestParam(defaultValue = "1") int anzahl,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime abfahrtszeit) {
        if (anzahl <= 0) return "Fehler: Anzahl muss größer als 0 sein!";
        try {
            Zug zug = zugService.zugById(zugId);
            Waggon waggon = waggonDao.queryForId(waggonId);
            Fahrgast fahrgast = fahrgastDao.queryForId(fahrgastId);

            if (zug == null) return "Fehler: Zug nicht gefunden!";
            if (waggon == null) return "Fehler: Waggon nicht gefunden!";
            if (fahrgast == null) return "Fehler: Fahrgast nicht gefunden!";

            fahrkartenService.fahrkarteKaufen(anzahl, zug, waggon, fahrgast, abfahrtszeit);

            Waggon aktuell = waggonDao.queryForId(waggonId);

            return "Erfolg! " + anzahl + " Ticket(s) für \"" + fahrgast.getName()
                    + "\" — Zug: " + zug.getName()
                    + ", Waggon: " + waggon.getBezeichnung()
                    + ", Abfahrt: " + abfahrtszeit
                    + ". Verbleibende Plätze: " + aktuell.getPlaetze();

        } catch (SQLException e) {
            return "Fehler: " + e.getMessage();
        }
    }

    // GET /api/tickets — alle gebuchten Fahrkarten
    @GetMapping
    public List<Fahrkarte> alleFahrkarten() throws SQLException {
        return fahrkartenService.alleFahrkarten();
    }

    // DELETE /api/tickets/{id}
    @DeleteMapping("/{id}")
    public String ticketStornieren(@PathVariable Long id) throws SQLException {
        fahrkartenService.deleteFahrekarte(id);
        return "Fahrkarte \"" + id + "\" erfolgreich storniert!";
    }


}