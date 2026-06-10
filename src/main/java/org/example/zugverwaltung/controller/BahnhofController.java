package org.example.zugverwaltung.controller;

import org.example.zugverwaltung.model.Bahnhof;
import org.example.zugverwaltung.model.Linie;
import org.example.zugverwaltung.service.BhfService;
import org.example.zugverwaltung.service.LinieService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/bhf")
public class BahnhofController {
    private final BhfService bhfService;
    private final LinieService linieService;

    public BahnhofController(BhfService bhfService, LinieService linieService) {
        this.bhfService = bhfService;
        this.linieService = linieService;
    }

    // GET /api/bhf - alle Bahnhöfe
    @GetMapping
    public List<Bahnhof> getAllBhf() throws SQLException {
        return bhfService.alleBahnhoefe();
    }

    // GET /api/bhf/{id} - Bahnhof mit {id}
    @GetMapping("/{id}")
    public Bahnhof getBhfById(@PathVariable Long id) throws SQLException {
        return bhfService.bahnhofById(id);
    }

    // POST /api/bhf - neunen Bahnhof anlegen
    @PostMapping
    public String bhfAnlegen(@RequestBody Bahnhof bhf) throws SQLException {
        bhfService.bahnhofAnlegen(bhf);
        return "Bahnhof \"" + bhf.getBezeichnung() + "\" erfolgreich angelegt!";
    }

    // DELETE /api/bhf/{id} - Bahnhof löschen
    @DeleteMapping("/{id}")
    public String bhfLoeschen(@PathVariable Long id) throws SQLException {
        bhfService.bahnhofLoeschen(id);
        return "Bahnhof mit ID " + id + " erfolgreich gelöscht!";
    }

    // PUT /api/bhf/{bhfId}/linie/{linieId} - Linie zu einem Bahnhof hinzufeugen
    @PutMapping("/{bhfId}/linie/{linieId}")
    public String addLinie(@PathVariable Long bhfId, @PathVariable Long linieId) throws SQLException {
        Bahnhof bhf = bhfService.bahnhofById(bhfId);
        Linie linie = linieService.linieById(linieId);
        if (bhf == null) return "Fehler: Bahnhof nicht gefunden";
        if (linie == null) return "Fehler: Linie nicht gefunden";
        bhfService.linieHinzufuegen(bhf, linie);
        return "Linie \"" + linie.getBezeichnung() + "\" zu Bahnhof \"" + bhf.getBezeichnung() + "\" hinzugefügt!";
    }
}