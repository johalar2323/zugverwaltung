package org.example.zugverwaltung.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "linien")
public class Linie {

    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField(columnName = "bezeichnung")
    private String bezeichnung;

    @ForeignCollectionField(eager = true)
    @JsonIgnore
    private ForeignCollection<Zug> zuege;

    @ForeignCollectionField(eager = true)
    @JsonIgnore
    private ForeignCollection<Bahnhof> bahnhoefe;

    public Linie() {
    }

    public Linie(String bezeichnung, ForeignCollection<Zug> zuege, ForeignCollection<Bahnhof> bahnhoefe) {
        this.bezeichnung = bezeichnung;
        this.zuege = zuege;
        this.bahnhoefe = bahnhoefe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public ForeignCollection<Zug> getZuege() {
        return zuege;
    }

    public void setZuege(ForeignCollection<Zug> zuege) {
        this.zuege = zuege;
    }

    public ForeignCollection<Bahnhof> getBahnhoefe() {
        return bahnhoefe;
    }

    public void setBahnhoefe(ForeignCollection<Bahnhof> bahnhoefe) {
        this.bahnhoefe = bahnhoefe;
    }

    @Override
    public String toString() {
        return "Linie{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", zuege=" + zuege +
                ", bahnhoefe=" + bahnhoefe +
                '}';
    }
}
