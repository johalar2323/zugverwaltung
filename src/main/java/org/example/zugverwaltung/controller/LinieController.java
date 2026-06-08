package org.example.zugverwaltung.controller;

import org.example.zugverwaltung.model.Linie;
import org.example.zugverwaltung.service.LinieService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/linien")
public class LinieController {

    private final LinieService linieService;

    public LinieController(LinieService linieService) {
        this.linieService = linieService;
    }

    // GET /api/linien - alle Linien
    @GetMapping
    public List<Linie> getAllLinien() throws SQLException {
        return linieService.alleLinien();
    }

    // GET /api/linien/{id} - Linie mit {id}
    @GetMapping("/{id}")
    public Linie getLinieById(@RequestBody Long id) throws SQLException {
        return linieService.linieById(id);
    }

    // POST /api/linien - Linie anlegen
    @PostMapping
    public String createLinie(@RequestBody Linie linie) throws SQLException {
        linieService.linieAnlegen(linie);
        return "Linie \"" + linie.getBezeichnung() + "\" erfolgreich angelegt!";
    }

    // DELETE /api/linien - Linie mit der {id} löschen
    @DeleteMapping("/{id}")
    public String deleteLinie(@RequestBody Long id) throws SQLException {
        linieService.linieLoeschen(id);
        return "Linie \"" + id + "\" erfolgreich gelöscht!";
    }
}
