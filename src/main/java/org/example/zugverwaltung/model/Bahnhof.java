package org.example.zugverwaltung.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bahnhoefe")
public class Bahnhof {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = "bezeichnung")
    private String bezeichnung;

    @DatabaseField(foreign = true, columnName = "linie_id")
    @JsonIgnore
    private Linie linie;

    public Bahnhof() {
    }

    public Bahnhof(String bezeichnung) {
        this.bezeichnung = bezeichnung;
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

    public Linie getLinie() {
        return linie;
    }

    public void setLinie(Linie linie) {
        this.linie = linie;
    }

    @Override
    public String toString() {
        return "Bahnhof{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", linie=" + linie +
                '}';
    }
}
