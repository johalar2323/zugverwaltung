package org.example.zugverwaltung.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDateTime;

@DatabaseTable(tableName = "fahrkarten")
public class Fahrkarte {
    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField(columnName = "plaetze")
    private int plaetze;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "zug_id")
    private Zug zug;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "waggon_id")
    private Waggon waggon;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "fahrgast_id")
    private Fahrgast fahrgast;

    @DatabaseField(columnName = "abfahrtszeit", persisterClass = LocalDateTimePersister.class)
    private LocalDateTime abfahrtszeit;

    public Fahrkarte() {
    }

    public Fahrkarte(int plaetze, Zug zug, Waggon waggon, Fahrgast fahrgast, LocalDateTime abfahrtszeit) {
        this.plaetze = plaetze;
        this.zug = zug;
        this.waggon = waggon;
        this.fahrgast = fahrgast;
        this.abfahrtszeit = abfahrtszeit;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public Zug getZug() {
        return zug;
    }

    public void setZug(Zug zug) {
        this.zug = zug;
    }

    public Waggon getWaggon() {
        return waggon;
    }

    public void setWaggon(Waggon waggon) {
        this.waggon = waggon;
    }

    public Fahrgast getFahrgast() {
        return fahrgast;
    }

    public void setFahrgast(Fahrgast fahrgast) {
        this.fahrgast = fahrgast;
    }

    public LocalDateTime getAbfahrtszeit() {
        return abfahrtszeit;
    }

    public void setAbfahrtszeit(LocalDateTime abfahrtszeit) {
        this.abfahrtszeit = abfahrtszeit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fahrkarte{" +
                "id=" + id +
                ", plaetze=" + plaetze +
                ", zug=" + zug +
                ", waggon=" + waggon +
                ", fahrgast=" + fahrgast +
                ", abfahrtszeit=" + abfahrtszeit +
                '}';
    }
}