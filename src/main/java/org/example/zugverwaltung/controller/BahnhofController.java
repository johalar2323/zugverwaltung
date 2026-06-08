package org.example.zugverwaltung.controller;

import org.example.zugverwaltung.model.Bahnhof;
import org.example.zugverwaltung.service.BhfService;
import org.example.zugverwaltung.service.LinieService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/bhf")
public class BahnhofController {
    private BhfService bhfService;

    public BahnhofController(LinieService linieService) {
        this.bhfService = bhfService;
    }

    // GET /api/bhf - alle Bahnhöfe
    @GetMapping
    public List<Bahnhof> getAllBhf() throws SQLException {
        return bhfService.alleBahnhoefe();
    }

    // GET /api/bhf/{id} - Bahnhof mit {id}
    @GetMapping("/{id}")
    public Bahnhof getBhfById(@RequestBody Long id) throws SQLException {
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
    public String bhfLoeschen(@RequestBody Long id) throws SQLException {
        bhfService.bahnhofLoeschen(id);
        return "Bahnhof \"" + id + "\" erfolgreich gelöscht!";
    }
}
