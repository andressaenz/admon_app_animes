package com.app.admin.animes.admon_app_animes.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class EsquemaDB {

    // Ejecuta el esquema y crea las tablas correspondientes
    public static void crearEsquema(Connection conn){
        try {
            String sql = Files.readString(Paths.get("src/main/resources/sql/esquema.sql"));
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Esquema de base de datos creados o ya existe.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
