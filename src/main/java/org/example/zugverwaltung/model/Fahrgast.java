package org.example.zugverwaltung.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fahrgaeste")
public class Fahrgast {
    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "waggon_id")
    private Waggon waggon;

    public Fahrgast() {
    }

    public Fahrgast(String name, Waggon waggon) {
        this.name = name;
        this.waggon = waggon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Waggon getWaggon() {
        return waggon;
    }

    public void setWaggon(Waggon waggon) {
        this.waggon = waggon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fahrgast{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", waggon=" + waggon +
                '}';
    }
}