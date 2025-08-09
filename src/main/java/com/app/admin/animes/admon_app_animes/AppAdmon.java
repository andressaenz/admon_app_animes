package com.app.admin.animes.admon_app_animes;


import com.app.admin.animes.admon_app_animes.util.PostgresConector;

import java.sql.Connection;
import java.sql.SQLException;

public class AppAdmon {
    public static void  main(String[] args){

        try (Connection conn = PostgresConector.obtenerConexion()){
            if (conn != null){
                System.out.println("si");
            } else {
                System.out.println("no");
            }
        } catch(SQLException e){
            System.out.println("Error");
        }

    }

}
