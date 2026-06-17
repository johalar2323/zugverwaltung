package org.example.zugverwaltung.controller;

import com.j256.ormlite.dao.Dao;
import org.example.zugverwaltung.model.*;
import org.example.zugverwaltung.service.BhfService;
import org.example.zugverwaltung.service.FahrkartenService;
import org.example.zugverwaltung.service.LinieService;
import org.example.zugverwaltung.service.ZugService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dev")
public class DevController {

    private final BhfService bhfService;
    private final LinieService linieService;
    private final ZugService zugService;
    private final FahrkartenService fahrkartenService;
    private final Dao<Fahrgast, Long> fahrgastDao;

    public DevController(BhfService bhfService, LinieService linieService, ZugService zugService, FahrkartenService fahrkartenService,
                         Dao<Fahrgast, Long> fahrgastDao) {
        this.bhfService = bhfService;
        this.linieService = linieService;
        this.zugService = zugService;
        this.fahrkartenService = fahrkartenService;
        this.fahrgastDao = fahrgastDao;
    }

    @PostMapping("/seed")
    public ResponseEntity<String> seed() throws Exception {
        if (!bhfService.alleBahnhoefe().isEmpty()) {
            return ResponseEntity.ok("Datenbank bereits befüllt – nichts geändert.");
        }

        // ── Bahnhöfe ────────────────────────────────────────────────────
        Bahnhof wien = new Bahnhof();
        wien.setBezeichnung("Wien Hauptbahnhof");
        Bahnhof salzburg = new Bahnhof();
        salzburg.setBezeichnung("Salzburg Hauptbahnhof");
        Bahnhof innsbruck = new Bahnhof();
        innsbruck.setBezeichnung("Innsbruck Hauptbahnhof");
        Bahnhof graz = new Bahnhof();
        graz.setBezeichnung("Graz Hauptbahnhof");
        Bahnhof linz = new Bahnhof();
        linz.setBezeichnung("Linz Hauptbahnhof");
        bhfService.bahnhofAnlegen(wien);
        bhfService.bahnhofAnlegen(salzburg);
        bhfService.bahnhofAnlegen(innsbruck);
        bhfService.bahnhofAnlegen(graz);
        bhfService.bahnhofAnlegen(linz);

        // ── Linien ──────────────────────────────────────────────────────
        Linie railjetWest = new Linie();
        railjetWest.setBezeichnung("RJ 660");
        Linie railjetSued = new Linie();
        railjetSued.setBezeichnung("RJ 720");
        Linie intercity = new Linie();
        intercity.setBezeichnung("IC 540");
        linieService.linieAnlegen(railjetWest);
        linieService.linieAnlegen(railjetSued);
        linieService.linieAnlegen(intercity);

        bhfService.linieHinzufuegen(wien, railjetWest);
        bhfService.linieHinzufuegen(wien, railjetSued);
        bhfService.linieHinzufuegen(wien, intercity);

        // ── Züge ────────────────────────────────────────────────────────
        Zug rj1 = new Zug();
        rj1.setName("RailJet-001");
        Zug rj2 = new Zug();
        rj2.setName("RailJet-002");
        Zug ic1 = new Zug();
        ic1.setName("InterCity-01");
        Zug rn1 = new Zug();
        rn1.setName("RegioNET-01");
        zugService.zugAnlegen(rj1);
        zugService.zugAnlegen(rj2);
        zugService.zugAnlegen(ic1);
        zugService.zugAnlegen(rn1);

        zugService.linieHinzufuegen(rj1, railjetWest);
        zugService.linieHinzufuegen(rj2, railjetSued);
        zugService.linieHinzufuegen(ic1, intercity);

        // ── Waggons ─────────────────────────────────────────────────────
        Waggon w1 = waggon("W-001 Erste Klasse", 64);
        Waggon w2 = waggon("W-002 Zweite Klasse", 80);
        Waggon w3 = waggon("W-003 Bistro", 0);
        Waggon w4 = waggon("W-004 Erste Klasse", 64);
        Waggon w5 = waggon("W-005 Zweite Klasse", 80);
        Waggon w6 = waggon("W-006 Zweite Klasse", 80);
        Waggon w7 = waggon("W-007 Zweite Klasse", 80);
        Waggon w8 = waggon("W-008 Zweite Klasse", 72);
        zugService.waggonAnlegen(w1);
        zugService.waggonHinzufuegen(rj1, w1);
        zugService.waggonAnlegen(w2);
        zugService.waggonHinzufuegen(rj1, w2);
        zugService.waggonAnlegen(w3);
        zugService.waggonHinzufuegen(rj1, w3);
        zugService.waggonAnlegen(w4);
        zugService.waggonHinzufuegen(rj2, w4);
        zugService.waggonAnlegen(w5);
        zugService.waggonHinzufuegen(rj2, w5);
        zugService.waggonAnlegen(w6);
        zugService.waggonHinzufuegen(ic1, w6);
        zugService.waggonAnlegen(w7);
        zugService.waggonHinzufuegen(ic1, w7);
        zugService.waggonAnlegen(w8);
        zugService.waggonHinzufuegen(rn1, w8);

        // ── Fahrgäste ───────────────────────────────────────────────────
        Fahrgast anna = fahrgast("Anna Müller");
        Fahrgast ben = fahrgast("Ben Gruber");
        Fahrgast clara = fahrgast("Clara Huber");
        Fahrgast david = fahrgast("David Maier");
        Fahrgast eva = fahrgast("Eva Bauer");

        // ── Fahrkarten ──────────────────────────────────────────────────
        // LocalDateTime wird via LocalDateTimePersister als ISO-String gespeichert
        LocalDateTime abfahrt = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);
        fahrkartenService.fahrkarteKaufen(1, rj1, w2, anna, abfahrt);
        fahrkartenService.fahrkarteKaufen(2, rj1, w2, ben, abfahrt);
        fahrkartenService.fahrkarteKaufen(1, rj2, w5, clara, abfahrt.plusHours(2));
        fahrkartenService.fahrkarteKaufen(1, ic1, w6, david, abfahrt.plusHours(1));
        fahrkartenService.fahrkarteKaufen(1, rj1, w1, eva, abfahrt);
        fahrkartenService.fahrkarteKaufen(1, ic1, w7, anna, abfahrt.plusHours(3));

        return ResponseEntity.ok("Testdaten erfolgreich angelegt.");
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> reset() throws Exception {
        // Fahrkarten + Waggons werden durch zugLoeschen() kaskadierend gelöscht
        for (Zug zug : zugService.alleZuege()) {
            zugService.zugLoeschen(zug.getId());
        }
        for (Linie linie : linieService.alleLinien()) {
            linieService.linieLoeschen(linie.getId());
        }
        fahrgastDao.delete(fahrgastDao.queryForAll());
        for (Bahnhof bhf : bhfService.alleBahnhoefe()) {
            bhfService.bahnhofLoeschen(bhf.getId());
        }
        return ResponseEntity.ok("Alle Testdaten gelöscht.");
    }

    // ── Hilfsmethoden ────────────────────────────────────────────────────

    private Waggon waggon(String bezeichnung, int plaetze) {
        Waggon w = new Waggon();
        w.setBezeichnung(bezeichnung);
        w.setPlaetze(plaetze);
        return w;
    }

    private Fahrgast fahrgast(String name) throws Exception {
        Fahrgast f = new Fahrgast();
        f.setName(name);
        fahrgastDao.create(f);
        return f;
    }
}