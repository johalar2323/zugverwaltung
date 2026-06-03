package org.example.zugverwaltung.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "zuege")
public class Zug {
    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField(columnName = "name")
    private String name;

    @ForeignCollectionField(eager = true)
    @JsonIgnore
    private ForeignCollection<Waggon> waggons;

    public Zug() {
    }

    public Zug(String name, ForeignCollection<Waggon> waggons) {
        this.name = name;
        this.waggons = waggons;
    }

    public Zug(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Waggon> getWaggons() {
        return waggons;
    }

    public void setWaggons(ForeignCollection<Waggon> waggons) {
        this.waggons = waggons;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Zug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", waggons=" + waggons +
                '}';
    }
}