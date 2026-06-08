package org.example.zugverwaltung.controller;

import org.example.zugverwaltung.model.Waggon;
import org.example.zugverwaltung.model.Zug;
import org.example.zugverwaltung.service.ZugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zuege")
public class ZugController {

    private final ZugService zugService;

    public ZugController(ZugService zugService) {
        this.zugService = zugService;
    }

    // GET /api/zuege – alle Züge
    @GetMapping
    public List<Zug> getAllZuege() throws Exception {
        return zugService.alleZuege();
    }

    // POST /api/zuege – neuen Zug anlegen
    @PostMapping
    public String createZug(@RequestBody Zug zug) throws Exception {
        zugService.zugAnlegen(zug);
        return "Zug \"" + zug.getName() + "\" erfolgreich angelegt!";
    }

    // GET /api/zuege/waggons – alle Waggons
    @GetMapping("/waggons")
    public List<Waggon> getAllWaggons() throws Exception {
        return zugService.alleWaggons();
    }

    // POST /api/zuege/waggons/{zugId} – Waggon zu Zug hinzufügen
    @PostMapping("waggons/{zugId}")
    public String addWaggon(@PathVariable Long zugId, @RequestBody Waggon waggon) throws Exception {
        Zug zug = zugService.zugById(zugId);
        if (zug == null) {
            return "Fehler: Zug mit ID " + zugId + " nicht gefunden!";
        }
        zugService.waggonHinzufuegen(zug, waggon);
        return "Waggon \"" + waggon.getBezeichnung() + "\" zu Zug \"" + zug.getName() + "\" hinzugefügt!";
    }

    // DELETE /api/zuege/{zugId} – Zug löschen
    @DeleteMapping("/{zugId}")
    public String deleteZug(@PathVariable Long zugId) {
        try {
            zugService.zugLoeschen(zugId);
            return "Zug mit ID " + zugId + " wurde erfolgreich entfernt.";
        } catch (Exception e) {
            return "Fehler beim Löschen des Zuges: " + e.getMessage();
        }
    }

    // DELETE /api/zuege/waggons/{waggonId} – Waggon löschen
    @DeleteMapping("waggons/{waggonId}")
    public String deleteWaggon(@PathVariable Long waggonId) {
        try {
            zugService.waggonLoeschen(waggonId);
            return "Waggon mit ID" + waggonId + " wurde erfolgreich entfernt";
        } catch (Exception e) {
            return "Fehler beim Löschen des Waggons: " + e.getMessage();
        }
    }
}