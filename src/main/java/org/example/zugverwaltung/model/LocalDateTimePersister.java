package org.example.zugverwaltung.model;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * ORMLite DataPersister für den Java-Typ LocalDateTime.
 * Konvertiert LocalDateTime <-> String (ISO-Format) für die Datenbankspeicherung.
 * Muss als Singleton implementiert sein, da ORMLite getSingleton() voraussetzt.
 */

public class LocalDateTimePersister extends BaseDataType {

    private static final LocalDateTimePersister instance = new LocalDateTimePersister();

    // Der Konstruktor sagt ORMLite speichere den Wert in einem String
    private LocalDateTimePersister() {
        super(SqlType.STRING, new Class<?>[]{LocalDateTime.class});
    }

    public static LocalDateTimePersister getSingleton() {
        return instance;
    }

    // Konvertiert das LocalDateTime Objekt in einem String damit es die DB speichern kann (INSERT/UPDATE)
    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
        // LocalDateTime -> String (für die DB)
        LocalDateTime dt = (LocalDateTime) javaObject;
        return dt.toString(); // ISO-Format: "2024-01-15T10:30:00"
    }

    // Parst den String aus der DB in ein LocalDateTime Objekt (SELECT)
    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        // String -> LocalDateTime (aus der DB)
        return LocalDateTime.parse((String) sqlArg);
    }

    // Wenn ein Defaultwert als String angegeben wird, wird er in ein LocalDateTime Objekt geparst
    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) {
        return LocalDateTime.parse(defaultStr);
    }

    // Lest den rohen Wert aus dem DB-Ergebnis
    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }
}
