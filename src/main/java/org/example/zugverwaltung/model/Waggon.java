package org.example.zugverwaltung.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "waggons")
public class Waggon {
    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField(foreign = true, columnName = "zug_id")
    @JsonIgnore
    private Zug zug;

    @DatabaseField(columnName = "bezeichnung")
    private String bezeichnung;

    @DatabaseField(columnName = "plaetze")
    private int plaetze;

    public Waggon() {
    }

    public Waggon(String bezeichnung, int plaetze) {
        this.bezeichnung = bezeichnung;
        this.plaetze = plaetze;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Zug getZug() {
        return zug;
    }

    public void setZug(Zug zug) {
        this.zug = zug;
    }

    @Override
    public String toString() {
        return "Waggon{" +
                "id=" + id +
                ", zug=" + zug +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", plaetze=" + plaetze +
                '}';
    }
}
